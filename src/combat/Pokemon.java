package combat;

import java.lang.reflect.Method;
import java.util.Random;

import visuals.screens.Combat;

public class Pokemon {
    private String name;
    private final String type;
    private final int hpmax;
    private double hp;
    private double[] attack;
    private double[] spattack;
    private double[] defense;
    private double[] spdefense;
    private double[] speed;
    private double[] accuracy;
    private Moves[] moves;
    private int victories;
    private int rounds;

    public Pokemon(String name, String type, int hpmax, double attack, double spattack, double defense, double spdefense, double speed, double accuracy, Moves[] moves) {
        this.name = name; 
        this.type = type;
        this.hpmax = hpmax;
        this.hp = hpmax;
        this.attack = new double[] {0, attack};
        this.spattack = new double[] {0, spattack};
        this.defense = new double[] {0, defense};
        this.spdefense = new double[] {0, spdefense};
        this.speed = new double[] {0, speed};
        this.accuracy = new double[] {0, accuracy};
        this.moves = moves;
        this.victories = 0;
        this.rounds = 0;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public Moves getMoves(int i) {
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

    public void setSpdefense(int stage) {
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

    public void useMove(Pokemon defender, Combat c) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1;
        int i = rn.nextInt(3);
        String txt;

        txt = " " + this + " uses " + this.getMoves(i).getName() + ",";
        c.addToHistory(txt);
        
        if (ataque <= this.getAccuracy()) {
            defender.takeMove(this.getMoves(i), this);
        }

    }

    public void takeMove(Moves move, Pokemon attacker) {
        String category = move.getCategory();
        double damage = 0;
        double multiplier = Types.checkMultiplier(move.getType(), this.getType());

        switch (category) {
        case "PHYSICAL":
            damage = (int) ((move.getPower() * attacker.getAttack() / this.getDefense()) / 5) + 2;
            break;

        case "SPECIAL":
            damage = (int) ((move.getPower() * attacker.getSpAttack() / this.getSpDefense()) / 5) + 2;
            break;
        
        case "STATUS1":
            status(move, this, -1);
            break;

        case "STATUS2":
            status(move, attacker, 1);
            break;
            
        }

        damage = damage * multiplier;

        if (category != "STATUS1" && category != "STATUS2") {
            if (hp - damage < 0) {
                hp = 0;
            } else {
                hp += -damage;
            }
        }

    }
    
    private void status(Moves move, Pokemon p, int value) {
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

    public int getVictories() {
        return victories;
    }

    public int getRounds() {
        return rounds;
    }

    public void addVictory() {
        this.victories += 1;
    }

    public void setRounds(int r) {
        this.rounds += r;
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
