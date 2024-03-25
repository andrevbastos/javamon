public class Moves {
    private String name;
    private String type;
    private int power;
    private String category;

    public Moves(String name, String type, int power, String category) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.category = category;
    }

    public void setPower(int i) {
        this.power += i;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public String getCategory() {
        return category;
    }
}
