package model.abilities;

import java.util.concurrent.atomic.AtomicReference;

import model.moves.Move;
import model.pokemon.Pokemon;
import model.util.Status;

/**
 * The AbilityObserver class is responsible for observing and handling events
 * related to Pokémon abilities during battles. It manages the interaction
 * between two Pokémon and their abilities, allowing them to react to specific
 * events in the battle.
 * 
 * @see model.pokemon.Pokemon
 * @see model.abilities.Ability
 * @see model.abilities.AbilityEvent
 */
public class AbilityObserver {
    Pokemon p1, p2;

    public void setPokemons(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void handleEvent(AbilityEvent event) {
        handleEvent(event, null, null, null);
    }

    public void handleEvent(AbilityEvent event, Move move, AtomicReference<Float> multiplier) {
        handleEvent(event, move, multiplier, null);
    }

    public void handleEvent(AbilityEvent event, Status status) {
        handleEvent(event, null, null, status);
    }

    public void handleEvent(AbilityEvent event, Move move, AtomicReference<Float> multiplier, Status status) {
        if (p1.getAbility() != null && p1.getAbility().getAbilityEvent() == event)
            p1.ability(p2, move, multiplier, status);
        if (p2.getAbility() != null && p2.getAbility().getAbilityEvent() == event)
            p2.ability(p1, move, multiplier, status);
    }
}