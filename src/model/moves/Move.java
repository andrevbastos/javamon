package model.moves;

import model.util.Types.Type;

public class Move {
    private final String name;
    private final Type type;
    private final String category;
    private final int power;
    private final String attribute1;
    private final String attribute2;

    public Move(String name, Type type, String category, int power) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.attribute1 = null;
        this.attribute2 = null;
    }

    public Move(String name, Type type, String category, String attribute1, String attribute2) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = 0;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
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
