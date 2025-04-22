package model.moves;

import java.util.HashMap;
import java.util.Map;
import model.util.Types.Type;

public class MovesFactory {
    
    private final Map<String, Moves> movesChart = new HashMap<>();

    public MovesFactory() {
        createMoves();
    }

    private void createMoves() {
        addMove("GROWL", Type.NORMAL,"STATUS1", "Attack", null);
        addMove("SCRATCH", Type.NORMAL, "PHYSICAL", 40);
        addMove("EMBER", Type.FIRE, "SPECIAL", 40);
        addMove("SMOKESCREEN", Type.NORMAL,"STATUS1", "Accuracy", null);
        addMove("TACKLE", Type.NORMAL, "PHYSICAL", 40);
        addMove("VINE WHIP", Type.GRASS, "PHYSICAL", 45);
        addMove("GROWTH", Type.NORMAL, "STATUS2", "Attack", "SpAttack");
        addMove("TAIL WHIP", Type.NORMAL, "STATUS1", "Defense", null);
        addMove("WATER GUN", Type.WATER, "SPECIAL", 40);
        addMove("WITHDRAW", Type.WATER, "STATUS2", "Defense", null);
        addMove("THUNDER SHOCK", Type.ELECTRIC, "SPECIAL", 40);
        addMove("SWIFT", Type.NORMAL, "SPECIAL", 60);
        addMove("AGILITY", Type.PSYCHIC, "STATUS2", "Speed", null);
        addMove("SPLASH", Type.NORMAL, "STATUS1", "Accuracy", null);
        addMove("BITE", Type.DARK, "PHYSICAL", 60);
        addMove("ROCK THROW", Type.ROCK, "PHYSICAL", 50);
        addMove("DEFENSE CURL", Type.NORMAL, "STATUS2", "Defense", null);
        addMove("ROLLOUT", Type.ROCK, "PHYSICAL", 30);
        addMove("THUNDERBOLT", Type.ELECTRIC, "SPECIAL", 90);
        addMove("RAZOR LEAF", Type.GRASS, "PHYSICAL", 55);
        addMove("SOLAR BEAM", Type.GRASS, "SPECIAL", 120);
    }

    private void addMove(String name, Type type, String category, int power) {
        Moves move = new Moves(name, type, category, power);
        movesChart.put(name, move);
    }

    private void addMove(String name, Type type, String category, String attribute1, String attribute2) {
        Moves move = new Moves(name, type, category, attribute1, attribute2);
        movesChart.put(name, move);
    }

    public Moves getMove(String name) {
        return movesChart.get(name);   
    }

}
