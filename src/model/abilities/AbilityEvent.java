package model.abilities;

/**
 * The AbilityEvent enum represents the different events that can trigger
 * abilities in Pokémon battles. Each event corresponds to a specific point
 * in the battle where an ability may activate.
 * 
 * @see model.abilities.Ability
 */
public enum AbilityEvent {
    BATTLE_START,
    TURN_START,
    TURN_END,
    BEFORE_MOVE,
    AFTER_MOVE,
    ON_HIT,
    ON_STATUS
};
