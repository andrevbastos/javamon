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
        return attack[1];
    }

    public double getSpattack() {
        return spattack[1];
    }

    public double getDefense() {
        return defense[1];
    }

    public double getSpdefense() {
        return spdefense[1];
    }

    public double getSpeed() {
        return speed[1];
    }

    public double getAccuracy() {
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

    public void setSpattack(int stage) {
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
