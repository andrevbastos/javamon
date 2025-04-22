package model.abilities;

import java.util.concurrent.atomic.AtomicReference;

import model.moves.Move;
import model.pokemon.Pokemon;

public class AbilityObserver {
    Pokemon p1, p2;

    public void setPokemons(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void handleEvent(AbilityEvent event) {
        handleEvent(event, null, null);
    }

    public void handleEvent(AbilityEvent event, Move move, AtomicReference<Float> multiplier) {
        if (p1.getAbility().getAbilityEvent() == event)
            p1.ability(p2, move, multiplier);
        if (p2.getAbility().getAbilityEvent() == event)
            p2.ability(p1, move, multiplier);
    }
}