package br.com.andrebastos.javamon_online.battle;

import java.lang.reflect.Method;
import java.util.Random;

import br.com.andrebastos.javamon_online.shared.Category;
import br.com.andrebastos.javamon_online.shared.TypeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andrebastos.javamon_online.move.Move;
import br.com.andrebastos.javamon_online.pokemon.Pokemon;
import br.com.andrebastos.javamon_online.pokemon.PokemonRepository;
import br.com.andrebastos.javamon_online.shared.Status;

@Service
public class BattleService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public BattleLogDto startBattle(Long pokemon1Id, Long pokemon2Id) {
        Pokemon pokemon1Entity = pokemonRepository.findById(pokemon1Id)
                .orElseThrow(() -> new IllegalArgumentException("Pokémon com ID " + pokemon1Id + " não encontrado!"));
        Pokemon pokemon2Entity = pokemonRepository.findById(pokemon2Id)
                .orElseThrow(() -> new IllegalArgumentException("Pokémon com ID " + pokemon2Id + " não encontrado!"));

        PokemonInBattle fighter1 = new PokemonInBattle(pokemon1Entity, 50);
        PokemonInBattle fighter2 = new PokemonInBattle(pokemon2Entity, 50);

        BattleLogDto battleLog = new BattleLogDto();

        PokemonInBattle firstAttacker = fighter1.getSpeed() >= fighter2.getSpeed() ? fighter1 : fighter2;
        PokemonInBattle secondAttacker = fighter1.getSpeed() >= fighter2.getSpeed() ? fighter2 : fighter1;

        battleLog.getLog().add(String.format("A batalha começa entre %s e %s!", firstAttacker.getName(), secondAttacker.getName()));
        battleLog.getLog().add(String.format("%s começa atacando por ser mais rápido!", firstAttacker.getName()));

        int turn = 1;
        while (!fighter1.isFainted() && !fighter2.isFainted()) {
            battleLog.getLog().add(String.format("--- Turno %d ---", turn));

            executeTurn(firstAttacker, secondAttacker, battleLog);
            if (secondAttacker.isFainted()) break;

            executeTurn(secondAttacker, firstAttacker, battleLog);
            if (firstAttacker.isFainted()) break;

            turn++;
        }

        Pokemon winnerEntity;
        Pokemon loserEntity;

        if (fighter1.isFainted()) {
            battleLog.setWinnerName(fighter2.getName());
            battleLog.setLoserName(fighter1.getName());
            winnerEntity = pokemon2Entity;
            loserEntity = pokemon1Entity;
        } else {
            battleLog.setWinnerName(fighter1.getName());
            battleLog.setLoserName(fighter2.getName());
            winnerEntity = pokemon1Entity;
            loserEntity = pokemon2Entity;
        }

        battleLog.getLog().add(String.format("A batalha terminou! %s é o vencedor!", battleLog.getWinnerName()));

        return battleLog;
    }

    private void executeTurn(PokemonInBattle attacker, PokemonInBattle defender, BattleLogDto battleLog) {
        boolean continueTurn = attacker.checkCondition(battleLog);
        if (!continueTurn) {
            return;
        }

        Move move = attacker.chooseRandomMove();
        battleLog.getLog().add(String.format("%s usa %s!", attacker.getName(), move.getName()));

        Category category = move.getCategory();
        String categoryName = category.name();

        Random random = new Random();
        int hitRoll = random.nextInt(100) + 1;
        boolean moveHits = move.getAccuracy() > 100;
        if (!moveHits) {
            double hitChance = move.getAccuracy() * attacker.getAccuracy();
            if (hitRoll <= hitChance) {
                moveHits = true;
            }
        }


        if (moveHits) {
            if (categoryName.contains("PHYSICAL") || categoryName.contains("SPECIAL")) {
                double typeMultiplier = TypeChart.getMultiplier(move.getType(), defender.getType());
                if (typeMultiplier == 0.0) {
                    battleLog.getLog().add(String.format("Não surte efeito em %s...", defender.getName()));
                    return;
                } else if (typeMultiplier > 1.0) {
                    battleLog.getLog().add("É super efetivo!");
                } else if (typeMultiplier < 1.0) {
                    battleLog.getLog().add("Não é muito efetivo...");
                }

                double stabBonus = 1.0;
                if (move.getType() == attacker.getType()) {
                    stabBonus = 1.5;
                    battleLog.getLog().add(String.format("%s recebe o bônus de STAB!", attacker.getName()));
                }

                double attackType = (move.getCategory() == Category.PHYSICAL) ? attacker.getAttack() : attacker.getSpAttack();
                double defenseType = (move.getCategory() == Category.PHYSICAL) ? defender.getDefense() : defender.getSpDefense();
                double baseDamage = ((move.getPower() * attackType / defenseType) / 5) + 2;

                int finalDamage = (int) Math.round(baseDamage * typeMultiplier * stabBonus);

                defender.takeDamage(finalDamage);
                battleLog.getLog().add(String.format("%s recebe %d de dano! HP restante: %.0f", defender.getName(), finalDamage, defender.getCurrentHp()));
            }
            if (categoryName.contains("STATUS")) {
                PokemonInBattle target = categoryName.contains("SELF") ? attacker : defender;
                int amount = categoryName.contains("SELF") ? 1 : -1;
                if (move.getStatus1() == move.getStatus2()) amount *= 2;

                applyStatusEffect(move.getStatus1(), amount, target, battleLog);
                if (move.getStatus2() != null && move.getStatus1() != move.getStatus2()) {
                    applyStatusEffect(move.getStatus2(), amount, target, battleLog);
                }
            }
        } else {
            battleLog.getLog().add(String.format("%s errou o ataque!", attacker.getName()));
            return;
        }
    }

    private void applyStatusEffect(Status status, int amount, PokemonInBattle target, BattleLogDto battleLog) {
        if (status == null) return;

        String targetName = target.getName();
        String result = (amount >= 1) ? " aumentou!" : " diminuiu!";

        switch (status) {
        case ATK:
            target.modifyAttackStage(amount);
            battleLog.getLog().add(String.format("O Ataque de %s" + result, targetName));
            break;

        case DEF:
            target.modifyDefenseStage(amount);
            battleLog.getLog().add(String.format("A Defesa de %s" + result, targetName));
            break;

        case SPD:
            target.modifySpeedStage(amount);
            battleLog.getLog().add(String.format("A Velocidade de %s" + result, targetName));
            break;

        case SPATK:
            target.modifySpAttackStage(amount);
            battleLog.getLog().add(String.format("O Ataque Especial de %s" + result, targetName));
            break;

        case SPDEF:
            target.modifySpDefenseStage(amount);
            battleLog.getLog().add(String.format("A Defesa Especial de %s" + result, targetName));
            break;

        case ACC:
            target.modifyAccuracyStage(amount);
            battleLog.getLog().add(String.format("A Defesa Especial de %s" + result, targetName));
            break;

        case BURN:
            target.setCondition(Status.BURN);
            break;

        case FROZEN:
            target.setCondition(Status.FROZEN);
            break;

        case CONFUSION:
            target.setCondition(Status.CONFUSION);
            break;

        case PARALYZE:
            target.setCondition(Status.PARALYZE);
            break;

        case SLEEP:
            target.setCondition(Status.SLEEP);
            break;

        case POISONED:
            target.setCondition(Status.POISONED);
            break;

        default:
            battleLog.getLog().add(String.format("O status %s não teve efeito implementado.", status.name()));
            break;
        }
    }
}