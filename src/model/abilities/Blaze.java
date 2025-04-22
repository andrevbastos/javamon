package model.abilities;

import java.util.concurrent.atomic.AtomicReference;

import model.moves.Move;
import model.pokemon.Pokemon;
import model.util.Types.Type;

public class Blaze extends Ability {
    public Blaze(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier) {
        if (move.getType() == Type.FIRE && getOwner().getHp() <= getOwner().getHpmax() / 3) {
            multiplier.set(1.5f);
        }
    }
    
}
