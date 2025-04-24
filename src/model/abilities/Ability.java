package model.abilities;

import java.util.concurrent.atomic.AtomicReference;

import model.moves.Move;
import model.pokemon.Pokemon;
import model.util.Types.Type;

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

class Blaze extends Ability {
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

class Torrent extends Ability {
    public Torrent(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier) {
        if (move.getType() == Type.WATER && getOwner().getHp() <= getOwner().getHpmax() / 3) {
            multiplier.set(1.5f);
        }
    }
}

class Overgrow extends Ability {
    public Overgrow(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier) {
        if (move.getType() == Type.GRASS && getOwner().getHp() <= getOwner().getHpmax() / 3) {
            multiplier.set(1.5f);
        }
    }
}