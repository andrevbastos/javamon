public class Pokemon {
    private String name;
    private final String type;
    private final int hpmax;
    private double hp;
    private double attack;
    private double spattack;
    private double defense;
    private double spdefense;
    private double speed;
    private double accuracy;
    private Moves[] moves;
    private final String sprite;

    public Pokemon(String name, String type, int hpmax, int attack, int spattack, int defense, int spdefense, int speed, int accuracy, Moves[] moves, String sprite) {
        this.name = name; 
        this.type = type;
        this.hpmax = hpmax;
        this.hp = hpmax;
        this.attack = attack;
        this.spattack = spattack;
        this.defense = defense;
        this.spdefense = spdefense;
        this.speed = speed;
        this.accuracy = accuracy;
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
        return attack;
    }

    public double getSpattack() {
        return spattack;
    }

    public double getDefense() {
        return defense;
    }

    public double getSpdefense() {
        return spdefense;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAccuracy() {
        return accuracy;
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

    public void setAttack(int i) {
        this.attack += i;
    }

    public void setSpAttack(int i) {
        this.spattack += i;
    }

    public void setDefense(int i) {
        this.defense += i;
    }

    public void setSpDefense(int i) {
        this.spdefense += i;
    }

    public void setSpeed(int i) {
        this.speed += i;
    }

    public void setAccuracy(int i) {
        this.accuracy += i;
    }
}
