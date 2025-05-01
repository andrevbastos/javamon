package model.util;

/**
 * This enum represents the different status effects that can be applied to
 * Pokémon during battles. Each status effect is associated with a specific
 * method suffix that indicates the type of effect it has.
 * "Condition" refers to status conditions that a pokémon can be in,
 * while "Attack", "Defense", "SpAttack", "SpDefense", "Speed",
 * "Accuracy" refer to the stats that can be affected.
 * 
 * @see model.moves.MovesFactory
 */
public enum Status {
    BURN("Condition"),
    PARALYZE("Condition"),
    SLEEP("Condition"),
    CONFUSION("Condition"),
    POISONED("Condition"),
    FROZEN("Condition"),
    ATK("Attack"),
    DEF("Defense"),
    SPATK("SpAttack"),
    SPDEF("SpDefense"),
    SPD("Speed"),
    ACC("Accuracy");

    private final String methodSuffix;

    Status(String methodSuffix) {
        this.methodSuffix = methodSuffix;
    }

    public String getMethodSuffix() {
        return methodSuffix;
    }
}
