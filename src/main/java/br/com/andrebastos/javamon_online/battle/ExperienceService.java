package br.com.andrebastos.javamon_online.battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andrebastos.javamon_online.battle.BattleLogDto;
import br.com.andrebastos.javamon_online.pokemon.Pokemon;
import br.com.andrebastos.javamon_online.pokemon.PokemonRepository;

@Service
public class ExperienceService {

    @Autowired
    private PokemonRepository pokemonRepository;

    private int getExpForNextLevel(int level) {
        return (int) Math.pow(level, 3);
    }

    public void awardExperience(Pokemon winner, Pokemon loser, BattleLogDto battleLog) {
        int baseExpYield = 60;
        int experienceGained = (baseExpYield * loser.getLevel()) / 7;

        battleLog.getLog().add(String.format("%s ganhou %d pontos de experiência!", winner.getName(), experienceGained));

        int newTotalExperience = winner.getExperience() + experienceGained;
        winner.setExperience(newTotalExperience);

        int expForNextLevel = getExpForNextLevel(winner.getLevel() + 1);
        if (winner.getExperience() >= expForNextLevel) {
            winner.setLevel(winner.getLevel() + 1);
            battleLog.getLog().add(String.format("%s subiu para o nível %d!", winner.getName(), winner.getLevel()));
        }

        pokemonRepository.save(winner);
    }
}