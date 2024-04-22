package main.combat;

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
    private final String sprite;

    public Pokemon(String name, String type, int hpmax, double attack, double spattack, double defense, double spdefense, double speed
                    , double accuracy, Moves[] moves, String sprite) {
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
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return name + " [" + type + "]";
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

    public double getHp() {
        // Vida não é enviada como negativa
        if (this.hp > 0) 
            return hp;
        else
            return 0;
    }

    public double getAttack() {
        return getStagedStatus(attack);
    }

    public double getSpAttack() {
        return getStagedStatus(spattack);
    }

    public double getDefense() {
        return getStagedStatus(defense);
    }

    public double getSpDefense() {
        return getStagedStatus(spdefense);
    }

    public double getSpeed() {
        return getStagedStatus(speed);
    }

    public double getAccuracy() {
        return getStagedStatus(accuracy);
    }

    public double getStagedStatus(double[] type) { 

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
        // Pega o move do pokemon dentro do seu vetor de moves
        return moves[i];
    }    
    
    public String getSprite() {
        return sprite;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
    
    public void setAttack(int stage) {
        if (attack[1] + stage >= 3) {
            this.attack[0] += stage;
            System.out.println(this + "`s Attack fell.");
        } else
            System.out.println(this + "`s Attack can`t go any lower");
    }

    public void setSpAttack(int stage) {
        if (spattack[1] + stage >= 3) {
            this.spattack[0] += stage;
            System.out.println(this + "`s Sp. Attack fell.");
        } else
            System.out.println(this + "`s Sp. Attack can`t go any lower");
    }

    public void setDefense(int stage) {
        if (defense[1] + stage >= 3) {
            this.defense[0] += stage;
            System.out.println(this + "`s Defense fell.");
        } else
            System.out.println(this + "`s Defense can`t go any lower");
    }

    public void setSpdefense(int stage) {
        if (spdefense[1] + stage >= 3) {   
            this.spdefense[0] += stage;
            System.out.println(this + "`s Sp. Defense fell.");
        } else
            System.out.println(this + "`s Sp. Defense can`t go any lower");
    }

    public void setSpeed(int stage) {
        if (speed[1] + stage >= 3) {  
            this.speed[0] += stage;
            System.out.println(this + "`s Speed fell.");
        } else
            System.out.println(this + "`s Speed can`t go any lower");
    }

    public void setAccuracy(int stage) {
        if (accuracy[1] + stage >= 3) { 
            this.accuracy[0] += stage;
            System.out.println(this + "`s Accuracy fell.");
        } else
            System.out.println(this + "`s Accuracy can`t go any lower");
    }

    public void checkStats() {
        System.out.println( this 
            + "\nAttack: " + attack[0] + " " + getAttack() 
            + "\nSpAttack: " + spattack[0] + " " + getSpAttack() 
            + "\nDefense: " + defense[0] + " " + getDefense()
            + "\nSpDefense: " + spdefense[0] + " " + getSpDefense()
            + "\nSpeed: " + speed[0] + " " + getSpeed()
            + "\nAccuracy: " + accuracy[0] + " " + getAccuracy() );
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
