package models;

public class Moves {
    private String name;
    private String type;
    private String category;
    private int power;
    private String attribute1;
    private String attribute2;

    public Moves(String name, String type, String category, int power) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.attribute1 = null;
        this.attribute2 = null;
    }

    public Moves(String name, String type, String category, String attribute1, String attribute2) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = 0;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
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

    public String getAttribute1() {
        return attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

}
