package model.moves;

import java.util.HashMap;
import java.util.Map;

import model.util.Category;
import static model.util.Category.*;

import model.util.Status;
import static model.util.Status.*;

import model.util.Type;
import static model.util.Type.*;

public class MovesFactory {
    
    private final Map<String, Move> movesChart = new HashMap<>();

    public MovesFactory() {
        createMoves();
    }

    private void createMoves() {
        addMove("COVET", FAIRY, PHYSICAL, 40, 100);
        addMove("GROWL", NORMAL, STATUS_ENEMY, ATK, null, 100);
        addMove("HEADBUTT", NORMAL, PHYSICAL, 70, 100);
        addMove("TAIL WHIP", NORMAL, STATUS_ENEMY, DEF, null, 100);
        addMove("CONFUSION", PSYCHIC, SPECIAL, STATUS_ENEMY, 50, CONFUSION, null, 100, 10);
        addMove("TACKLE", NORMAL, PHYSICAL, 40, 100);
        addMove("SWIFT", NORMAL, SPECIAL, 60, 101);
        addMove("BITE", DARK, PHYSICAL, 60, 100);
        addMove("SCREECH", NORMAL, STATUS_ENEMY, DEF, null, 100);
        addMove("FIRE FANG", FIRE, PHYSICAL, STATUS_ENEMY, 65, BURN, null, 100, 10);
        addMove("FLASH", ELECTRIC, SPECIAL, STATUS_ENEMY, 60, ATK, null, 100, 10);
        addMove("THUNDERSHOCK", ELECTRIC, SPECIAL, STATUS_ENEMY, 40, PARALYZE, null, 100, 10);
        addMove("WATER PULSE", WATER, SPECIAL, STATUS_ENEMY, 60, CONFUSION, null, 100, 10);
        addMove("AURORA BEAM", ICE, SPECIAL, STATUS_ENEMY, 65, ATK, null, 100, 10);
        addMove("RAZOR LEAF", GRASS, PHYSICAL, 55, 100);
        addMove("GRASS WHISTLE", GRASS, STATUS_ENEMY, SLEEP, null, 55);
        addMove("ICY WIND", ICE, SPECIAL, STATUS_ENEMY, 55, SPD, null, 100, 10);
        addMove("DISARM CRY", FAIRY, SPECIAL, 40, 100);
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
        return movesChart.get(name);
    }

}
