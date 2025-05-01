package model.moves;

import java.util.HashMap;
import java.util.Map;
import model.util.Category;
import static model.util.Category.*;
import model.util.Status;
import static model.util.Status.*;
import model.util.Type;
import static model.util.Type.*;

/**
 * This class is responsible for creating and managing moves in the game.
 * It uses the Factory design pattern to create moves and store them in a
 * moves chart. All moves are created when the factory is instantiated and
 * can be retrieved by their name.
 * 
 * @see model.moves.Move
 */
public class MovesFactory {
    
    private final Map<String, Move> movesChart = new HashMap<>();

    public MovesFactory() {
        createMoves();
    }

    private void createMoves() {
        // EEVEE
        addMove("COVET", FAIRY, PHYSICAL, 40, 100);
        addMove("GROWL", NORMAL, STATUS_ENEMY, ATK, null, 100);
        addMove("HEADBUTT", NORMAL, PHYSICAL, 70, 100);
        addMove("TAIL WHIP", NORMAL, STATUS_ENEMY, DEF, null, 100);

        // ESPEON
        addMove("PSYCHIC", PSYCHIC, SPECIAL, STATUS_ENEMY, 90, SPDEF, null, 100, 10);
        addMove("PSYBEAM", PSYCHIC, SPECIAL, STATUS_ENEMY, 65, CONFUSION, null, 100, 10);
        addMove("SWIFT", NORMAL, SPECIAL, 60, 101);
        addMove("FIERY DANCE", FIRE, SPECIAL, STATUS_SELF, 80, SPATK, null, 100, 50);

        // UMBREON
        addMove("CRUNCH", DARK, PHYSICAL, STATUS_ENEMY, 80, DEF, null, 100, 20);
        addMove("CONFUSE RAY", GHOST, STATUS_ENEMY, CONFUSION, null, 100);
        addMove("FEINT ATTACK", DARK, PHYSICAL, 60, 101);
        addMove("CUT", STEEL, PHYSICAL, 75, 100);

        // FLAREON
        addMove("SACRED FIRE", FIRE, SPECIAL, STATUS_ENEMY, 100, BURN, null, 95, 50);
        addMove("LAVA PLUME", FIRE, SPECIAL, STATUS_ENEMY, 80, BURN, null, 100, 30);
        addMove("SMOG", POISON, SPECIAL, STATUS_ENEMY, 30, POISONED, null, 100, 50);
        addMove("SCARY FACE", NORMAL, STATUS_ENEMY, SPD, SPD, 100);

        // JOLTEON
        addMove("THUNDER", ELECTRIC, SPECIAL, STATUS_ENEMY, 110, PARALYZE, null, 70, 30);
        addMove("DISCHARGE", ELECTRIC, SPECIAL, STATUS_ENEMY, 80, PARALYZE, null, 100, 30);
        addMove("SIGNAL BEAM", BUG, SPECIAL, STATUS_ENEMY, 75, CONFUSION, null, 100, 10);
        addMove("AGILITY", PSYCHIC, STATUS_SELF, SPD, SPD, 100);

        // VAPOREON
        addMove("HYDRO PUMP", WATER, SPECIAL, 110, 80);
        addMove("ACID ARMOR", POISON, STATUS_SELF, DEF, DEF, 100);
        addMove("ICE BEAM", ICE, SPECIAL, STATUS_ENEMY, 75, FROZEN, null, 100, 10);
        addMove("SWIFT", NORMAL, SPECIAL, 60, 101);

        // LEAFEON
        addMove("LEAF BLADE", GRASS, PHYSICAL, 90, 100);
        addMove("GRASS WHISTLE", GRASS, STATUS_ENEMY, SLEEP, null, 55);
        addMove("SWORDS DANCE", NORMAL, STATUS_SELF, ATK, ATK, 100);
        addMove("IRON TAIL", STEEL, PHYSICAL, 100, 75);

        // GLACEON
        addMove("BLIZZARD", ICE, SPECIAL, 110, 70);
        addMove("FREEZE DRY", ICE, SPECIAL, STATUS_ENEMY, 70, FROZEN, null, 100, 10);
        addMove("BARRIER", PSYCHIC, STATUS_SELF, DEF, DEF, 100);
        addMove("BITE", DARK, PHYSICAL, 60, 100);

        // SYLVEON
        addMove("MOONBLAST", FAIRY, SPECIAL, STATUS_ENEMY, 95, SPATK, null, 100, 30);
        addMove("LIGHT SCREEN", PSYCHIC, STATUS_SELF, SPDEF, SPDEF, 100);
        addMove("FLASH", ELECTRIC, SPECIAL, STATUS_ENEMY, 60, ATK, null, 100, 10);
    }

    private void addMove(String name, Type type, Category category1, Category category2,
        int power, Status attribute1, Status attribute2, int accuracy1, int accuracy2) 
    {
        Move move = new Move(name, type, category1, category2, power, attribute1, attribute2, accuracy1, accuracy2);
        movesChart.put(name, move);
    }

    private void addMove(String name, Type type, Category category, int power, int accuracy) {
        addMove(name, type, category, null, power, null, null, accuracy, 0);
    }

    private void addMove(String name, Type type, Category category, Status attribute1, Status attribute2,  int accuracy) {
        addMove(name, type, category, null, 0, attribute1, attribute2, accuracy, 0);
    }

    public Move getMove(String name) {
        if (!movesChart.containsKey(name)) {
            System.out.println("Move not found: " + name);
            return movesChart.get("HEADBUTT");
        }
        return movesChart.get(name);
    }

}
