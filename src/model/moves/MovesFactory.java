package model.moves;

import java.util.HashMap;
import java.util.Map;

public class MovesFactory {
    
    private final Map<String, Moves> movesChart = new HashMap<>();

    public MovesFactory() {
        createMoves();
    }

    private void createMoves() {
        addMove("GROWL", "NORMAL","STATUS1", "Attack", null);
        addMove("SCRATCH", "NORMAL", "PHYSICAL", 40);
        addMove("EMBER", "FIRE", "SPECIAL", 40);
        addMove("SMOKESCREEN", "NORMAL","STATUS1", "Accuracy", null);
        addMove("TACKLE", "NORMAL", "PHYSICAL", 40);
        addMove("VINE WHIP", "GRASS", "PHYSICAL", 45);
        addMove("GROWTH", "NORMAL", "STATUS2", "Attack", "SpAttack");
        addMove("TAIL WHIP", "NORMAL", "STATUS1", "Defense", null);
        addMove("WATER GUN", "WATER", "SPECIAL", 40);
        addMove("WITHDRAW", "WATER", "STATUS2", "Defense", null);
        addMove("THUNDER SHOCK", "ELECTRIC", "SPECIAL", 40);
        addMove("SWIFT", "NORMAL", "SPECIAL", 60);
        addMove("AGILITY", "PSYCHIC", "STATUS2", "Speed", null);
        addMove("SPLASH", "NORMAL", "STATUS1", "Accuracy", null);
        addMove("BITE", "DARK", "PHYSICAL", 60);
        addMove("ROCK THROW", "ROCK", "PHYSICAL", 50);
        addMove("DEFENSE CURL", "NORMAL", "STATUS2", "Defense", null);
        addMove("ROLLOUT", "ROCK", "PHYSICAL", 30);
        addMove("THUNDERBOLT", "ELECTRIC", "SPECIAL", 90);
        addMove("RAZOR LEAF", "GRASS", "PHYSICAL", 55);
        addMove("SOLAR BEAM", "GRASS", "SPECIAL", 120);
    }

    private void addMove(String name, String type, String category, int power) {
        Moves move = new Moves(name, type, category, power);
        movesChart.put(name, move);
    }

    private void addMove(String name, String type, String category, String attribute1, String attribute2) {
        Moves move = new Moves(name, type, category, attribute1, attribute2);
        movesChart.put(name, move);
    }

    public Moves getMove(String name) {
        return movesChart.get(name);   
    }

}
