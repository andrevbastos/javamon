package model.moves;

import model.util.Type;
import model.util.Category;
import model.util.Status;

/**
 * @class Move
 * @brief Represents a move in the game. Each move has a name, type, category, power, accuracy, and associated status effects.
 * Some moves may have two categories, one for damage and one for status effects.
 * For each category, there is an associated power/attribute and accuracy.
 * 
 * @see model.util.Type
 * @see model.util.Category
 * @see model.util.Status
 * 
 * @see model.moves.MovesFactory
 */
public class Move {
    private final String name;
    private Type type;
    private final Category category1;
    private final Category category2;
    private int power;
    private final int accuracy1;
    private final int accuracy2;
    private final Status attribute1;
    private final Status attribute2;

    /**
     * @brief Constructs a move with full parameters.
     * @param name Move name
     * @param type Move type
     * @param category1 Primary category
     * @param category2 Secondary category (nullable)
     * @param power Base power (0 for status moves)
     * @param attribute1 Primary status effect (nullable)
     * @param attribute2 Secondary status effect (nullable)
     * @param accuracy1 Primary accuracy
     * @param accuracy2 Secondary accuracy
     */
    public Move(String name, Type type, Category category1, Category category2,
        int power, Status attribute1, Status attribute2, int accuracy1, int accuracy2) 
    {
        this.name = name;
        this.type = type;
        this.category1 = category1;
        this.category2 = category2;
        this.power = power;
        this.accuracy1 = accuracy1;
        this.accuracy2 = accuracy2;
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

    public Category getCategory1() {
        return category1;
    }

    public Category getCategory2() {
        return category2;
    }

    public int getAccuracy1() {
        return accuracy1;
    }

    public int getAccuracy2() {
        return accuracy2;
    }

    public Status getAttribute1() {
        return attribute1;
    }

    public Status getAttribute2() {
        return attribute2;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
