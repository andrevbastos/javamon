package model.moves;

import java.util.HashMap;
import java.util.Map;
import model.util.Types.Type;

public class MovesFactory {
    
    private final Map<String, Move> movesChart = new HashMap<>();

    public MovesFactory() {
        createMoves();
    }

    private void createMoves() {
        addMove("COVET", Type.FAIRY, "PHYSICAL", 40);
        addMove("GROWL", Type.NORMAL, "STATUS2", "Attack", null);
        addMove("HEADBUTT", Type.NORMAL, "PHYSICAL", 70);
        addMove("TAIL WHIP", Type.NORMAL, "STATUS2", "Defense", null);
        addMove("CONFUSION", Type.PSYCHIC, "SPECIAL", 50); // !!! confusion
        addMove("TACKLE", Type.NORMAL, "PHYSICAL", 40);
        addMove("SWIFT", Type.NORMAL, "SPECIAL", 60);
        addMove("BITE", Type.DARK, "PHYSICAL", 60);
        addMove("SCREECH", Type.NORMAL, "STATUS2", "Defense", null);
        addMove("FIRE FANG", Type.FIRE, "PHYSICAL", 65); // !!! burn
        addMove("FLASH", Type.ELECTRIC, "SPECIAL", 60); // !!! -atk
        addMove("THUNDERSHOCK", Type.ELECTRIC, "SPECIAL", 40); // !!! paralyze
        addMove("WATER PULSE", Type.WATER, "SPECIAL", 60); // !!! confusion
        addMove("AURORA BEAM", Type.ICE, "SPECIAL", 65); // !!! -atk
        addMove("RAZOR LEAF", Type.GRASS, "PHYSICAL", 55);
        addMove("GRASS WHISTLE", Type.GRASS, "STATUS2", "Attack", null); // !!! sleep
        addMove("ICY WIND", Type.ICE, "SPECIAL", 55); // !!! -spd
        addMove("DISARM CRY", Type.FAIRY, "SPECIAL", 40);
    }

    private void addMove(String name, Type type, String category, int power) {
        Move move = new Move(name, type, category, power);
        movesChart.put(name, move);
    }

    private void addMove(String name, Type type, String category, String attribute1, String attribute2) {
        Move move = new Move(name, type, category, attribute1, attribute2);
        movesChart.put(name, move);
    }

    public Move getMove(String name) {
        return movesChart.get(name);
    }

}
