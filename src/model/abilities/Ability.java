package model.abilities;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import model.moves.Move;
import model.pokemon.Pokemon;
import model.util.Category;
import model.util.Status;
import model.util.Type;

/**
 * @class Ability
 * @brief Abstract base for all Pokémon abilities.
 * Implements Template Method pattern for battle effects.
 * Each concrete ability defines execution logic for its event.
 * 
 * @details Key features:
 * - Owner Pokémon reference
 * - Associated trigger event
 * - Modifiable battle parameters
 * - Status effect handling
 * 
 * @note Subclasses must implement execute() method
 * 
 * @see model.pokemon.Pokemon
 * @see model.moves.Move
 * @see model.util.Status
 */
public abstract class Ability {
    /** @brief When this ability activates */
    protected AbilityEvent event;

    Ability(Pokemon owner, AbilityEvent event) {
        this.event = event;
    }

    AbilityEvent getAbilityEvent() { return event; };

    public void execute(Pokemon user, Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {};
}

/**
 * @class Adaptability
 * @brief Boosts STAB (Same-Type Attack Bonus) moves by 30%.
 * 
 * Trigger: BEFORE_MOVE
 * Effect: Increases damage multiplier for same-type moves
 */
class Adaptability extends Ability {
    public Adaptability(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon user, Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (move.getType() == user.getType()) {
            multiplier.set(1.3f);
        }
    }
}

/**
 * @class IceBody
 * @brief 25% chance to freeze on physical hits.
 * 
 * Trigger: ON_HIT
 * Effect: May apply FROZEN status
 */
class IceBody extends Ability {
    public IceBody(Pokemon owner) {
        super(owner, AbilityEvent.ON_HIT);
    }

    @Override
    public void execute(Pokemon user, Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (move.getCategory1() == Category.PHYSICAL) {
            Random rn = new Random();
            int roll = rn.nextInt(4) + 1;
            if (roll == 4) {
                enemy.status(Status.FROZEN, null, enemy, -1);
            }
        }
    }
}

/**
 * @class Pixilate
 * @brief Converts Normal moves to Fairy-type with 30% power boost.
 * 
 * Trigger: BEFORE_MOVE
 * Effect: Modifies move type and power
 */
class Pixilate extends Ability {
    public Pixilate(Pokemon owner) {
        super(owner, AbilityEvent.BEFORE_MOVE);
    }

    @Override
    public void execute(Pokemon user, Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (move.getType() == Type.NORMAL) {
            move.setType(Type.FAIRY);
            int newPower = (int) (move.getPower() * 1.3f);
            move.setPower(newPower);
        }
    }
}

/**
 * @class MagicBounce
 * @brief Reflects status conditions back to attacker.
 * 
 * Trigger: ON_STATUS
 * Effect: Reverses status effect application
 */
class MagicBounce extends Ability {
    public MagicBounce(Pokemon owner) {
        super(owner, AbilityEvent.ON_STATUS);
    }

    @Override
    public void execute(Pokemon user, Pokemon enemy, Move move, AtomicReference<Float> multiplier, Status status) {
        if (status.getMethodSuffix().equals("Condition")) {
            enemy.status(status, null, enemy, -1);
        }
    }
}