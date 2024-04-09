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
        double value;
        if (attack[0] > 0) {
            value = attack[1] * (2 + Math.abs(attack[0])) / 2;
        } else if (attack[0] < 0) {
            value = attack[1] * 2 / (2 + Math.abs(attack[0]));
        } else 
            value = attack[1];

        return value;
    }

    public double getSpAttack() {
        double value;
        if (spattack[0] > 0) {
            value = spattack[1] * (2 + Math.abs(spattack[0])) / 2;
        } else if (spattack[0] < 0) {
            value = spattack[1] * 2 / (2 + Math.abs(spattack[0]));
        } else 
            value = spattack[1];

        return value;
    }

    public double getDefense() {
        double value;
        if (defense[0] > 0) {
            value = defense[1] * (2 + Math.abs(defense[0])) / 2;
        } else if (defense[0] < 0) {
            value = defense[1] * 2 / (2 + Math.abs(defense[0]));
        } else 
            value = defense[1];

        return value;
    }

    public double getSpDefense() {
        double value;
        if (spdefense[0] > 0) {
            value = spdefense[1] * (2 + Math.abs(spdefense[0])) / 2;
        } else if (spdefense[0] < 0) {
            value = spdefense[1] * 2 / (2 + Math.abs(spdefense[0]));
        } else 
            value = spdefense[1];

        return value;
    }

    public double getSpeed() {
        double value;
        if (speed[0] > 0) {
            value = speed[1] * (2 + Math.abs(speed[0])) / 2;
        } else if (speed[0] < 0) {
            value = speed[1] * 2 / (2 + Math.abs(speed[0]));
        } else 
            value = speed[1];

        return value;
    }

    public double getAccuracy() {
        double value;
        if (accuracy[0] > 0) {
            value = accuracy[1] * (2 + Math.abs(accuracy[0])) / 2;
        } else if (accuracy[0] < 0) {
            value = accuracy[1] * 2 / (2 + Math.abs(accuracy[0]));
        } else 
            value = accuracy[1];

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
        if (attack[1] + stage >= 6) {
            this.attack[0] += stage;
            System.out.println(this + "`s Attack fell.");
        } else
            System.out.println(this + "`s Attack can`t go any lower");
    }

    public void setSpAttack(int stage) {
        if (spattack[1] + stage >= 6) {
            this.spattack[0] += stage;
            System.out.println(this + "`s Sp. Attack fell.");
        } else
            System.out.println(this + "`s Sp. Attack can`t go any lower");
    }

    public void setDefense(int stage) {
        if (defense[1] + stage >= 6) {
            this.defense[0] += stage;
            System.out.println(this + "`s Defense fell.");
        } else
            System.out.println(this + "`s Defense can`t go any lower");
    }

    public void setSpdefense(int stage) {
        if (spdefense[1] + stage >= 6) {   
            this.spdefense[0] += stage;
            System.out.println(this + "`s Sp. Defense fell.");
        } else
            System.out.println(this + "`s Sp. Defense can`t go any lower");
    }

    public void setSpeed(int stage) {
        if (speed[1] + stage >= 6) {  
            this.speed[0] += stage;
            System.out.println(this + "`s Speed fell.");
        } else
            System.out.println(this + "`s Speed can`t go any lower");
    }

    public void setAccuracy(int stage) {
        if (accuracy[1] + stage >= 6) { 
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
