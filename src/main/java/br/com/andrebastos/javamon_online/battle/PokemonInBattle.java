package br.com.andrebastos.javamon_online.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.andrebastos.javamon_online.move.Move;
import br.com.andrebastos.javamon_online.pokemon.Pokemon;
import br.com.andrebastos.javamon_online.shared.Status;
import br.com.andrebastos.javamon_online.shared.Type;

public class PokemonInBattle {

    private final Pokemon baseData;

    private final double maxHp;
    private final double attack;
    private final double defense;
    private final double spAttack;
    private final double spDefense;
    private final double speed;

    private double currentHp;
    private final List<Move> moves;

    private Status condition;
    private int conditionCount;

    private int attackStage;
    private int defenseStage;
    private int spAttackStage;
    private int spDefenseStage;
    private int speedStage;
    private int accuracyStage;

    public PokemonInBattle(Pokemon baseData) {
        this.baseData = baseData;
        int level = baseData.getLevel();

        this.maxHp = Math.floor(((2 * baseData.getHpmax() * level / 100.0) + level + 10));
        this.attack = Math.floor(((2 * baseData.getAttack() * level / 100.0) + 5));
        this.defense = Math.floor(((2 * baseData.getDefense() * level / 100.0) + 5));
        this.spAttack = Math.floor(((2 * baseData.getSpattack() * level / 100.0) + 5));
        this.spDefense = Math.floor(((2 * baseData.getSpdefense() * level / 100.0) + 5));
        this.speed = Math.floor(((2 * baseData.getSpeed() * level / 100.0) + 5));

        this.currentHp = this.maxHp;
        this.moves = new ArrayList<>(baseData.getMoves());

        this.attackStage = 0;
        this.defenseStage = 0;
        this.spAttackStage = 0;
        this.spDefenseStage = 0;
        this.speedStage = 0;
        this.accuracyStage = 0;
        this.condition = null;
        this.conditionCount = 0;
    }

    public double getAttack() {
        return getStagedStat(this.attack, this.attackStage);
    }

    public double getDefense() {
        return getStagedStat(this.defense, this.defenseStage);
    }

    public double getSpAttack() {
        return getStagedStat(this.spAttack, spAttackStage);
    }

    public double getSpDefense() {
        return getStagedStat(this.spDefense, spDefenseStage);
    }

    public double getSpeed() {
        return getStagedStat(this.speed, this.speedStage);
    }

    public double getAccuracy() {
        return getStagedStat(100, accuracyStage);
    }

    public boolean isFainted() {
        return this.currentHp <= 0;
    }

    public Move chooseRandomMove() {
        if (this.moves.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int moveIndex = random.nextInt(this.moves.size());
        return this.moves.get(moveIndex);
    }

    public void takeDamage(double damage) {
        this.currentHp -= damage;
        if (this.currentHp < 0) {
            this.currentHp = 0;
        }
    }

    public String getName() {
        return this.baseData.getName();
    }

    private double getStagedStat(double baseStat, int stage) {
        if (stage > 0) {
            return baseStat * (2.0 + stage) / 2.0;
        } else if (stage < 0) {
            return baseStat * 2.0 / (2.0 + Math.abs(stage));
        }
        return baseStat;
    }

    public void modifyAttackStage(int amount) {
        this.attackStage = Math.max(-6, Math.min(6, this.attackStage + amount));
    }

    public void modifyDefenseStage(int amount) {
        this.defenseStage = Math.max(-6, Math.min(6, this.attackStage + amount));
    }

    public void modifySpAttackStage(int amount) {
        this.spAttackStage = Math.max(-6, Math.min(6, this.attackStage + amount));
    }

    public void modifySpDefenseStage(int amount) {
        this.spDefenseStage = Math.max(-6, Math.min(6, this.attackStage + amount));
    }

    public void modifySpeedStage(int amount) {
        this.speedStage = Math.max(-6, Math.min(6, this.attackStage + amount));
    }

    public void modifyAccuracyStage(int amount) {
        this.accuracyStage = Math.max(-6, Math.min(6, this.attackStage + amount));
    }

    public double getCurrentHp() {
        return currentHp;
    }

    public Type getType() {
        return this.baseData.getType();
    }

    public Status getCondition() {
        return condition;
    }

    public void setCondition(Status condition) {
        this.condition = condition;
    }

    public boolean checkCondition(BattleLogDto bl) {
        if (this.condition != null) {
            Random rn = new Random();
            int roll;
            switch (this.condition) {
            case BURN:
                this.takeDamage((int) Math.round((float) this.baseData.getHpmax() / 16));
                bl.add(String.format("%s foi queimado.", this.baseData.getName()));
                break;

            case FROZEN:
                this.takeDamage((int) Math.round((float) this.baseData.getHpmax() / 16));
                bl.add(String.format("%s foi congelado.", this.baseData.getName()));
                break;

            case CONFUSION:
                if (conditionCount == 0) {
                    conditionCount = rn.nextInt(3) + 1;
                } else if (conditionCount == 1) {
                    this.condition = null;
                    this.conditionCount = 0;
                    bl.add(String.format("%s despertou da confusao.", this.baseData.getName()));
                }

                this.conditionCount--;
                roll = rn.nextInt(3) + 1;
                if (roll == 3) {
                    this.takeDamage((int) Math.round((float) this.baseData.getHpmax() / 16));
                    bl.add(String.format("%s se acertou com a confusao.", this.baseData.getName()));
                    return false;
                }
                break;

            case PARALYZE:
                roll = rn.nextInt(4) + 1;
                if (roll == 4) {
                    bl.add(String.format("%s esta paralisado.", this.baseData.getName()));
                    return false;
                }
                break;

            case SLEEP:
                roll = rn.nextInt(4) + 1;
                if (roll == 4) {
                    this.condition = null;
                    bl.add(String.format("%s despertou do sono.", this.baseData.getName()));
                } else {
                    bl.add(String.format("%s esta dormindo.", this.baseData.getName()));
                    return false;
                }
                break;

            case POISONED:
                this.conditionCount++;
                this.takeDamage((float) (this.baseData.getHpmax() / 16) * conditionCount);
                bl.add(String.format("%s esta envenenado.", this.baseData.getName()));
                break;
            }
        }

        return this.currentHp >= 0;
    }
}