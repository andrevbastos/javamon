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
        int value;
        if (attack[0] > 0) {
            value = (int) ((2 + Math.abs(attack[0]) / 2) * attack[1]);
            return value;
        } else if (attack[0] < 0) {
            return ((2 / 2 + Math.abs(attack[0])) * attack[1]);
        } else 
            return attack[1];
    }

    public double getSpAttack() {
        int value;
        if (attack[0] > 0) {
            value = (int) ((2 + Math.abs(spattack[0] / 2)) * spattack[1]);
            return value;
        } else if (attack[0] < 0) {
            return ((2 / 2 + Math.abs(spattack[0])) * spattack[1]);
        } else 
            return spattack[1];
    }

    public double getDefense() {
        if (defense[0] > 0) {
            return ((2 + Math.abs(defense[0]) / 2) * defense[1]);
        } else if (attack[0] < 0) {
            return ((2 / 2 + Math.abs(defense[0])) * defense[1]);
        } else 
            return defense[1];
    }

    public double getSpDefense() {
        if (spdefense[0] > 0) {
            return ((2 + Math.abs(spdefense[0]) / 2) * spdefense[1]);
        } else if (attack[0] < 0) {
            return ((2 / 2 + Math.abs(spdefense[0])) * spdefense[1]);
        } else 
            return spdefense[1];
    }

    public double getSpeed() {
        if (speed[0] > 0) {
            return ((2 + Math.abs(speed[0]) / 2) * speed[1]);
        } else if (attack[0] < 0) {
            return ((2 / 2 + Math.abs(speed[0])) * speed[1]);
        } else 
            return speed[1];
    }

    public double getAccuracy() {
        if (accuracy[0] > 0) {
            return ((2 + Math.abs(accuracy[0]) / 2) * accuracy[1]);
        } else if (attack[0] < 0) {
            return ((2 / 2 + Math.abs(accuracy[0])) * accuracy[1]);
        } else 
            return accuracy[1];
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
        this.attack[0] += stage;
    }

    public void setSpAttack(int stage) {
        this.spattack[0] += stage;
    }

    public void setDefense(int stage) {
        this.defense[0] += stage;
    }

    public void setSpdefense(int stage) {
        this.spdefense[0] += stage;
    }

    public void setSpeed(int stage) {
        this.speed[0] += stage;
    }

    public void setAccuracy(int stage) {
        this.accuracy[0] += stage;
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
