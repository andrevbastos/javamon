package model.abilities;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import model.moves.Move;
import model.pokemon.Pokemon;
import model.util.Category;
import model.util.Status;
import model.util.Type;

public abstract class Ability {
    Pokemon owner;
    AbilityEvent event;

    Ability(Pokemon owner, AbilityEvent event) {
        this.owner = owner;
        this.event = event;
    }

    Pokemon getOwner() { return owner; };
    AbilityEvent getAbilityEvent() { return event; };

    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {};
}

class Adaptability extends Ability {
    public Adaptability(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (move.getType() == owner.getType()) {
            multiplier.set(1.3f);
        }
    }
}

class IceBody extends Ability {
    public IceBody(Pokemon owner) {
        super(owner, AbilityEvent.ON_HIT);
    }

    @Override
    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (move.getCategory1() == Category.PHYSICAL) {
            Random rn = new Random();
            int roll = rn.nextInt(4) + 1;
            if (roll == 4) {
                enemy.status(Status.FROZEN, null, enemy, -1);
            }
        }
    }
}

class Pixilate extends Ability {
    public Pixilate(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (move.getType() == Type.NORMAL) {
            move.setType(Type.FAIRY);
            int newPower = (int) (move.getPower() * 1.3f);
            move.setPower(newPower);
        }
    }
}