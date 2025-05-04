package model.abilities;

import java.util.concurrent.atomic.AtomicReference;
import model.moves.Move;
import model.pokemon.Pokemon;
import model.util.Status;

/**
 * @class AbilityObserver
 * @brief Mediates ability-triggered interactions between battling Pokémon.
 * Acts as the central hub for ability activation during battles. Implements the
 * Observer pattern to handle ability events between two Pokémon.
 * 
 * @details
 * - Tracks two battling Pokémon (p1 and p2)
 * - Dispatches events to appropriate abilities
 * - Handles four event types with method overloading
 * 
 * @note Thread-safe through AtomicReference for damage multipliers
 * 
 * @see model.pokemon.Pokemon
 * @see model.abilities.Ability
 * @see model.abilities.AbilityEvent
 */
public class AbilityObserver {
    /** @brief First battling Pokémon */
    Pokemon p1;
    /** @brief Second battling Pokémon */
    Pokemon p2;

    /**
     * @brief Sets the battling Pokémon pair
     * @param p1 First Pokémon
     * @param p2 Second Pokémon
     */
    public void setPokemons(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void handleEvent(AbilityEvent event) {
        handleEvent(event, null, null, null, null);
    }
    
    public void handleEvent(AbilityEvent event, Pokemon user, Status status) {
        handleEvent(event, user, null, null, status);
    }

    public void handleEvent(AbilityEvent event, Pokemon user, Move move) {
        handleEvent(event, user, move, null, null);
    }
    
    public void handleEvent(AbilityEvent event, Pokemon user,
        Move move, AtomicReference<Float> multiplier) 
    {
        handleEvent(event, user, move, multiplier, null);
    }

    public void handleEvent(AbilityEvent event, Pokemon user,
        Move move, AtomicReference<Float> multiplier, Status status) 
    {
        if (user != null) {
            Pokemon enemy = (user == p1) ? p2 : p1;
            if (user.getAbility() != null && user.getAbility().getAbilityEvent() == event) {
                user.ability(enemy, move, multiplier, status);
            }
        } else {
            if (p1.getAbility() != null && p1.getAbility().getAbilityEvent() == event) {
                p1.ability(p2, move, multiplier, status);
            }
            if (p2.getAbility() != null && p2.getAbility().getAbilityEvent() == event) {
                p2.ability(p1, move, multiplier, status);
            }
        }
    }
}