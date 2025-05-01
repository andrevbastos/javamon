package model.abilities;

/**
 * @enum AbilityEvent
 * @brief Defines trigger points for ability activation.
 * Represents key battle moments when abilities may activate.
 * Used by AbilityObserver for event dispatching.
 * 
 * @details Timeline:
 * 1. BATTLE_START - When battle begins
 * 2. TURN_START - Start of each turn
 * 3. BEFORE_MOVE - Before move execution
 * 4. AFTER_MOVE - After move completes
 * 5. ON_HIT - When hit by a move
 * 6. ON_STATUS - When status effect is applied
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