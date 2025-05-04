package model.pokemon;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import model.abilities.Ability;
import model.abilities.AbilityEvent;
import model.abilities.AbilityObserver;
import model.moves.Move;
import model.util.Status;
import model.util.Type;
import model.util.TypesChart;

/**
 * @class Pokemon
 * @brief The Pokemon class represents a Pokémon in the game.
 * It contains attributes such as name, type, stats, moves, and status conditions.
 * The class provides methods to manage the Pokémon's stats, abilities
 * and interactions with other Pokémon.
 * All interactions are followed by an Event Observer pattern to handle game logic during battles.
 * 
 * @see model.abilities.Ability
 * @see model.abilities.AbilityObserver
 * @see model.moves.Move
 * @see model.util.Status
 * @see model.util.Type
 * @see model.util.TypesChart
 */
public class Pokemon {
    private final String name;
    private final Type type;
    private Ability ability;
    private final int hpmax;
    private final double[] attack;
    private final double[] spattack;
    private final double[] defense;
    private final double[] spdefense;
    private final double[] speed;
    private final double[] accuracy;
    private final Move[] moves;
    private double hp;
    private Status condition;
    private int conditionCount;
    
    private int[] statistics = new int[2];

    /**
     * @brief Constructs a Pokémon with base stats and moveset.
     * @param name The Pokémon's name
     * @param type The Pokémon's primary type
     * @param hpmax Maximum HP value
     * @param attack Base attack stat
     * @param defense Base defense stat
     * @param spattack Base special attack stat
     * @param spdefense Base special defense stat
     * @param speed Base speed stat
     * @param moves Array of 4 moves
     */
    public Pokemon(String name, Type type, int hpmax, double attack, double defense, double spattack, double spdefense, double speed, Move[] moves) {
        this.name = name; 
        this.type = type;
        this.hpmax = hpmax;
        this.hp = hpmax;
        this.attack = new double[] {0, attack};
        this.defense = new double[] {0, defense};
        this.spattack = new double[] {0, spattack};
        this.spdefense = new double[] {0, spdefense};
        this.speed = new double[] {0, speed};
        this.accuracy = new double[] {0, 1};
        this.moves = moves;
        this.condition = null;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @brief Uses a randomly selected move against the defender.
     * @param observer AbilityObserver to handle ability events
     * @param defender Target Pokémon
     * @return String Name of the used move
     * 
     * @details
     * Handles complete move execution including:
     * 1. Random move selection
     * 2. Accuracy check
     * 3. Ability event triggering
     * 4. Damage calculation if hit
     */
    public String useMove(AbilityObserver observer, Pokemon defender) {
        Random rn = new Random();
        Move selectedMove = this.getMove(rn.nextInt(4));
        AtomicReference<Float> multiplier = new AtomicReference<>(1.0f);

        int hitChance = 0;
        int roll = rn.nextInt(100) + 1;

        if (this.checkCondition()) {
            if (selectedMove.getAccuracy1() == 101) {
                hitChance = 100;
            } else {
                hitChance = (int)(selectedMove.getAccuracy1() * this.getAccuracy());
            }
            
            if (roll <= hitChance) {
                observer.handleEvent(AbilityEvent.BEFORE_MOVE, this, selectedMove, multiplier);
                defender.takeMove(this, selectedMove, observer, multiplier);
                observer.handleEvent(AbilityEvent.AFTER_MOVE, this, selectedMove);
            }
        }

        addRound();
        return selectedMove.getName();
    }

    /**
     * @brief Processes incoming move damage and effects.
     * @param attacker The Pokémon using the move
     * @param move The move being used
     * @param observer AbilityObserver for event handling
     * @param multiplier Reference to damage multiplier
     * 
     * @details
     * Handles:
     * 1. Type effectiveness calculation
     * 2. STAB (Same Type Attack Bonus)
     * 3. Physical/Special damage calculation
     * 4. Status move application
     * 5. Secondary effects
     */
    public void takeMove(Pokemon attacker, Move move, AbilityObserver observer, AtomicReference<Float> multiplier) {
        double damage = 0;
        multiplier.set(multiplier.get() * TypesChart.checkMultiplier(move.getType(), this.getType()));
        if (attacker.getType() == move.getType()) {
            multiplier.set(multiplier.get() * 1.5f);
        }

        switch (move.getCategory1()) {
            case PHYSICAL -> {
                damage = (int) ((move.getPower() * attacker.getAttack() / this.getDefense()) / 5) + 2;
                hit(damage, move, observer, multiplier);
                observer.handleEvent(AbilityEvent.ON_HIT, this, move);
            }
            case SPECIAL -> {
                damage = ((move.getPower() * attacker.getSpAttack() / this.getSpDefense()) / 5) + 2;
                hit(damage, move, observer, multiplier);
                observer.handleEvent(AbilityEvent.ON_HIT, this, move);
            }
            case STATUS_SELF -> {
                status(move.getAttribute1(), move.getAttribute2(), attacker, 1);
                observer.handleEvent(AbilityEvent.ON_STATUS, this, move.getAttribute1());
            }
            case STATUS_ENEMY -> {
                status(move.getAttribute1(), move.getAttribute2(), this, -1);
                observer.handleEvent(AbilityEvent.ON_STATUS, this, move.getAttribute1());
            }
        }

        if (move.getCategory2() != null) {
            Random rn = new Random();
            int roll = rn.nextInt(100) + 1;

            if (roll <= move.getAccuracy2()) {
                switch (move.getCategory2()) {
                    case STATUS_SELF -> {
                        status(move.getAttribute1(), move.getAttribute2(), attacker, 1);
                        observer.handleEvent(AbilityEvent.ON_STATUS, this, move.getAttribute1());
                    }
                    case STATUS_ENEMY -> {
                        status(move.getAttribute1(), move.getAttribute2(), this, -1);
                        observer.handleEvent(AbilityEvent.ON_STATUS, this, move.getAttribute1());
                    }
                }
            }
        }
    }

    /**
     * @brief Applies damage to the Pokémon.
     * @param damage Raw damage amount before multipliers
     * @param move The move causing damage
     * @param observer AbilityObserver for event handling
     * @param multiplier Final damage multiplier
     */
    public void hit(double damage, Move move, AbilityObserver observer, AtomicReference<Float> multiplier) {
        damage *= multiplier.get();
        if (this.hp - damage < 0) {
            this.hp = 0;
        } else {
            this.hp += -damage;
        }
    }

    /**
     * @brief Applies status condition(s) to target Pokémon.
     * @param attribute1 Primary status effect
     * @param attribute2 Secondary status effect (nullable)
     * @param target Pokémon to receive status
     * @param value Stage modifier value if applicable
     */
    public void status(Status attribute1, Status attribute2, Pokemon target, int value) {
        try {
            Method m = methodTroughName(Pokemon.class, "set" + attribute1.getMethodSuffix());
            if (attribute1.getMethodSuffix().equals("Condition"))
                m.invoke(target, attribute1);
            else 
                m.invoke(target, value);

            if (attribute2 != null) {
                m = methodTroughName(Pokemon.class, "set" + attribute2.getMethodSuffix());
                if (attribute2.getMethodSuffix().equals("Condition"))
                    m.invoke(target, attribute2);
                else 
                    m.invoke(target, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Method methodTroughName(Class<?> c, String nome) throws Exception {
        for (Method m : c.getMethods()) {
            if (m.getName().equals(nome)) {
                return m;
            } 
        }
        throw new Exception("Método " + nome + " não encontrado");
    }

    /**
     * @brief Checks and processes current status condition.
     * @return boolean False if status prevents action, true otherwise
     * 
     * @details
     * Handles effects of:
     * - Burn: HP reduction
     * - Freeze: HP reduction and possible immobility
     * - Paralysis: Chance to prevent move
     * - Sleep: Prevents moves until waking
     * - Poison: HP reduction
     * - Confusion: Chance to self-hit
     */
    public boolean checkCondition() {
        if (this.condition != null) {
            switch (this.condition) {
                case BURN -> {
                    this.hp -= this.hpmax / 16;
                }
                case FROZEN -> {
                    this.hp -= this.hpmax / 16;
                }
                case CONFUSION -> {
                    Random rn = new Random();

                    if (conditionCount == 0) {
                        conditionCount = rn.nextInt(3) + 1;
                    } else if (conditionCount == 1) {
                        this.condition = null;
                        this.conditionCount = 0;
                    }

                    int roll = rn.nextInt(3) + 1;
                    if (roll == 3) {
                        this.hp -= this.hpmax / 16;
                        this.conditionCount--;
                        return false;
                    }
                }
                case PARALYZE -> {
                    Random rn = new Random();
                    int roll = rn.nextInt(4) + 1;
                    if (roll == 4) {
                        return false;
                    }
                }
                case SLEEP -> {
                    Random rn = new Random();
                    int roll = rn.nextInt(4) + 1;
                    if (roll == 4) {
                        this.condition = null;
                    } else {
                        return false;
                    }
                }
                case POISONED -> {
                    this.hp -= (this.hpmax / 16) * conditionCount;
                }
            }
            
            if (this.hp <= 0) {
                this.hp = 0;
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * @brief Gets the current HP value.
     * @return int Current HP (returns 0 if HP is negative)
     */
    public int getHp() {
        if (this.hp > 0) 
            return (int) hp;
        else
            return 0;
    }

    /**
     * @brief Calculates staged stat value considering stat stage.
     * @param type The stat array containing [stage, baseValue]
     * @return double The calculated stat value after modifiers
     * 
     * @details
     * Stat stages range from -6 to +6:
     * - Positive stages: stat * (2 + |stage|) / 2
     * - Negative stages: stat * 2 / (2 + |stage|)
     */
    public double getStagedStats(double[] type) { 
        double value;

        if (type[0] > 0) {
            value = type[1] * (2 + Math.abs(type[0])) / 2;
        } else if (type[0] < 0) {
            value = type[1] * 2 / (2 + Math.abs(type[0]));
        } else 
            value = type[1];

        return value;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Ability getAbility() {
        return ability;
    }

    public int getHpmax() {
        return hpmax;
    }
    
    public double getAttack() {
        return getStagedStats(attack);
    }

    public double getSpAttack() {
        return getStagedStats(spattack);
    }

    public double getDefense() {
        return getStagedStats(defense);
    }

    public double getSpDefense() {
        return getStagedStats(spdefense);
    }

    public double getSpeed() {
        return getStagedStats(speed);
    }

    public double getAccuracy() {
        return getStagedStats(accuracy);
    }

    public Move getMove(int i) {
        return moves[i];
    }

    public Status getCondition() {
        return condition;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
    
    public void setAttack(int stage) {
        if (attack[0] + stage <= 6 || attack[0] + stage >= -6) {
            this.attack[0] += stage;
        }
    }

    public void setSpAttack(int stage) {
        if (spattack[0] + stage <= 6 || spattack[0] + stage >= -6) {
            this.spattack[0] += stage;
        }
    }

    public void setDefense(int stage) {
        if (defense[0] + stage <= 6 || defense[0] + stage >= -6) {
            this.defense[0] += stage;
        }
    }

    public void setSpDefense(int stage) {
        if (spdefense[0] + stage <= 6 || spdefense[0] + stage >= -6) {   
            this.spdefense[0] += stage;
        }
    }

    public void setSpeed(int stage) {
        if (speed[0] + stage <= 6 || speed[0] + stage >= -6) {  
            this.speed[0] += stage;
        }
    }

    public void setAccuracy(int stage) {
        if (accuracy[0] + stage <= 6 || accuracy[0] + stage >= -6) { 
            this.accuracy[0] += stage;
        }
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public void setCondition(Status condition) {
        if (this.condition == null) {
            this.condition = condition;
            conditionCount = 0;
        }
    }

    public void ability(Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        ability.execute(this, enemy, move, multiplier, status);
    }

    public void addWin() {
        statistics[0]++;
    }
    
    public void addMoves() {
        statistics[1]++;
    }

    public int getWins() {
        return statistics[0];
    }

    public int addRound() {
        return statistics[1];
    }

    public void heal() {
        this.hp = hpmax;
        this.attack[0] = 0;
        this.spattack[0] = 0;
        this.defense[0] = 0;
        this.spdefense[0] = 0;
        this.speed[0] = 0;
        this.accuracy[0] = 0;
        this.condition = null;
    }

}
