package model.util;

public enum Status {
    BURN("Condition"),
    PARALYZE("Condition"),
    SLEEP("Condition"),
    CONFUSION("Condition"),
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
