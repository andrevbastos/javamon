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
        addMove("AGILITY", Type.PSYCHIC, "STATUS2", "Speed", null);
        addMove("BITE", Type.DARK, "PHYSICAL", 60);
        addMove("BLOCK", Type.NORMAL, "STATUS1", "Speed", null);
        addMove("BUG BITE", Type.BUG, "PHYSICAL", 60);
        addMove("CHARGE", Type.ELECTRIC, "STATUS2", "SpDefense", null);
        addMove("CHARM", Type.FAIRY, "STATUS1", "Attack", null);
        addMove("CONFUSE RAY", Type.GHOST, "STATUS1", "Accuracy", null);
        addMove("DEFENSE CURL", Type.NORMAL, "STATUS2", "Defense", null);
        addMove("DRAGON RAGE", Type.DRAGON, "SPECIAL", 40);
        addMove("EMBER", Type.FIRE, "SPECIAL", 40);
        addMove("FAIRY WIND", Type.FAIRY, "SPECIAL", 40);
        addMove("GEAR GRIND", Type.STEEL, "PHYSICAL", 50);
        addMove("GROWL", Type.NORMAL, "STATUS1", "Attack", null);
        addMove("GROWTH", Type.NORMAL, "STATUS2", "Attack", "SpAttack");
        addMove("HARDEN", Type.NORMAL, "STATUS2", "Defense", null);
        addMove("HOWL", Type.NORMAL, "STATUS2", "Attack", null);
        addMove("HYPER FANG", Type.NORMAL, "PHYSICAL", 80);
        addMove("ICY WIND", Type.ICE, "SPECIAL", 55);
        addMove("KARATE CHOP", Type.FIGHTING, "PHYSICAL", 50);
        addMove("LEER", Type.NORMAL, "STATUS1", "Defense", null);
        addMove("LICK", Type.GHOST, "PHYSICAL", 30);
        addMove("LOW KICK", Type.FIGHTING, "PHYSICAL", 50);
        addMove("METRONOME", Type.NORMAL, "STATUS1", "Accuracy", null);
        addMove("MUD-SLAP", Type.GROUND, "SPECIAL", 20);
        addMove("NIGHT SHADE", Type.GHOST, "SPECIAL", 100);
        addMove("PECK", Type.FLYING, "PHYSICAL", 35);
        addMove("POISON STING", Type.POISON, "PHYSICAL", 15);
        addMove("POWDER SNOW", Type.ICE, "SPECIAL", 40);
        addMove("POWER TRIP", Type.DARK, "PHYSICAL", 20);
        addMove("PSYBEAM", Type.PSYCHIC, "SPECIAL", 65);
        addMove("PSYCH UP", Type.NORMAL, "STATUS2", "Attack", "Defense");
        addMove("QUICK ATTACK", Type.NORMAL, "PHYSICAL", 40);
        addMove("ROCK THROW", Type.ROCK, "PHYSICAL", 50);
        addMove("ROLLOUT", Type.ROCK, "PHYSICAL", 30);
        addMove("SAND ATTACK", Type.GROUND, "STATUS1", "Accuracy", null);
        addMove("SCRATCH", Type.NORMAL, "PHYSICAL", 40);
        addMove("SLASH", Type.NORMAL, "PHYSICAL", 70);
        addMove("SMOG", Type.POISON, "SPECIAL", 30);
        addMove("SMOKESCREEN", Type.NORMAL, "STATUS1", "Accuracy", null);
        addMove("SPLASH", Type.NORMAL, "STATUS1", "Accuracy", null);
        addMove("STRING SHOT", Type.BUG, "STATUS1", "Speed", null);
        addMove("SWIFT", Type.NORMAL, "SPECIAL", 60);
        addMove("TACKLE", Type.NORMAL, "PHYSICAL", 40);
        addMove("TAIL WHIP", Type.NORMAL, "STATUS1", "Defense", null);
        addMove("THUNDER SHOCK", Type.ELECTRIC, "SPECIAL", 40);
        addMove("VICE GRIP", Type.NORMAL, "PHYSICAL", 55);
        addMove("VINE WHIP", Type.GRASS, "PHYSICAL", 45);
        addMove("WATER GUN", Type.WATER, "SPECIAL", 40);
        addMove("WING ATTACK", Type.FLYING, "PHYSICAL", 60);
        addMove("WITHDRAW", Type.WATER, "STATUS2", "Defense", null);
        addMove("WRAP", Type.NORMAL, "PHYSICAL", 15);
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
