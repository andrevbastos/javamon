package model.pokemon;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import model.abilities.Ability;
import model.abilities.AbilityEvent;
import model.abilities.AbilityObserver;
import model.moves.Move;
import model.util.Types;
import model.util.Types.Type;

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
    private int[] statistics = new int[2];

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
        this.accuracy = new double[] {0, 100};
        this.moves = moves;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Ability getAbility() {
        return ability;
    }

    public void ability(Pokemon enemy, Move move, AtomicReference<Float> multiplier) {
        ability.execute(enemy, move, multiplier);
    }

    public int getHpmax() {
        return hpmax;
    }

    public int getHp() {
        if (this.hp > 0) 
            return (int) hp;
        else
            return 0;
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

    public Move getMove(int i) {
        return moves[i];
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
    
    public void setAttack(int stage) {
        if (attack[0] + stage <= 3 || attack[0] + stage >= -3) {
            this.attack[0] += stage;
        }
    }

    public void setSpAttack(int stage) {
        if (spattack[0] + stage <= 3 || spattack[0] + stage >= -3) {
            this.spattack[0] += stage;
        }
    }

    public void setDefense(int stage) {
        if (defense[0] + stage <= 3 || defense[0] + stage >= -3) {
            this.defense[0] += stage;
        }
    }

    public void setSpDefense(int stage) {
        if (spdefense[0] + stage <= 3 || spdefense[0] + stage >= -3) {   
            this.spdefense[0] += stage;
        }
    }

    public void setSpeed(int stage) {
        if (speed[0] + stage <= 3 || speed[0] + stage >= -3) {  
            this.speed[0] += stage;
        }
    }

    public void setAccuracy(int stage) {
        if (accuracy[0] + stage <= 3 || accuracy[0] + stage >= -3) { 
            this.accuracy[0] += stage;
        }
    }

    public String useMove(AbilityObserver observer, Pokemon defender) {
        Random rn = new Random();
        int ataque = rn.nextInt(100) + 1;
        Move selectedMove = this.getMove(rn.nextInt(4));
        AtomicReference<Float> multiplier = new AtomicReference<>(1.0f);
        
        if (ataque <= this.getAccuracy()) {
            observer.handleEvent(AbilityEvent.BEFORE_MOVE, selectedMove, multiplier);
            defender.takeMove(observer, this, selectedMove, multiplier);
            observer.handleEvent(AbilityEvent.AFTER_MOVE);
        }

        addMoves();
        return selectedMove.getName();
    }

    public void takeMove(AbilityObserver observer, Pokemon attacker, Move move, AtomicReference<Float>  multiplier) {
        String category = move.getCategory();
        double damage = 0;
        multiplier.set(multiplier.get() * Types.checkMultiplier(move.getType(), this.getType()));

        switch (category) {
            case "PHYSICAL" -> damage = (int) ((move.getPower() * attacker.getAttack() / this.getDefense()) / 5) + 2;
            case "SPECIAL" -> damage = (int) ((move.getPower() * attacker.getSpAttack() / this.getSpDefense()) / 5) + 2;
            case "STATUS1" -> status(move, this, 1);
            case "STATUS2" -> status(move, attacker, -1);
        }

        if (category != "STATUS1" && category != "STATUS2") {
            damage = damage * multiplier.get();
            if (hp - damage < 0) {
                hp = 0;
            } else {
                observer.handleEvent(AbilityEvent.ON_HIT, move, multiplier);
                hp += -damage;
            }
        }
    }
    
    private void status(Move move, Pokemon p, int value) {
        Method m;
		try {
			m = methodTroughName(Pokemon.class, "set" + move.getAttribute1());
            m.invoke(p, value);
            if (move.getAttribute2() != null) { 
			    m = methodTroughName(Pokemon.class, "set" + move.getAttribute2());
                m.invoke(p, value);
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

    public void addWin() {
        statistics[0]++;
    }
    
    public void addMoves() {
        statistics[1]++;
    }

    public int getWins() {
        return statistics[0];
    }

    public int getMoves() {
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
    }

}
