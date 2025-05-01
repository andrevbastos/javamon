package model.util;

/**
 * This enum represents the different categories of moves in the game.
 * Each category defines how the move interacts with the target.
 * 
 * @see model.moves.Move
 */
public enum Category {
    PHYSICAL,
    SPECIAL,
    STATUS_SELF,
    STATUS_ENEMY
}