package model.abilities;

import java.util.concurrent.atomic.AtomicReference;

import model.moves.Move;
import model.pokemon.Pokemon;

public abstract class Ability {
    Pokemon owner;
    AbilityEvent event;

    Ability(Pokemon owner, AbilityEvent event) {
        this.owner = owner;
        this.event = event;
    }

    Pokemon getOwner() { return owner; };
    AbilityEvent getAbilityEvent() { return event; };

    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier) {};
}
