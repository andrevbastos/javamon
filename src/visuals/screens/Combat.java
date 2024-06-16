package visuals.screens;

import java.io.*;

import combat.*;
import visuals.GamePanel;

public class Combat {
    
    private GamePanel gp;
    private Pokemon[] selectedPokemons;
    private Battle battle;
    private int repetitions = 10;
    private String winners = "";
    private String moveHistory = "";

    public Combat(GamePanel gp) {

        this.gp = gp;

        PokemonFactory pf = new PokemonFactory();
        this.selectedPokemons = new Pokemon[] { pf.getPokemon("CHARMANDER"), pf.getPokemon("SQUIRTLE"), pf.getPokemon("BULBASAUR"), pf.getPokemon("PIKACHU")};

        this.battle = new Battle(this);

    }

    public void runCombatSequence() {
        for (int i = 0; i < selectedPokemons.length; i++) {
            for (int j = i + 1; j < selectedPokemons.length; j++) {
                if (i != j) {
                    battle.setP1(selectedPokemons[i]);
                    battle.setP2(selectedPokemons[j]);
                    for (int k = repetitions; k >= 0; k--) {
                        battle.run();
                    }
                }
            }
        }

        createLogs();
        gp.setSimStats(selectedPokemons);
    }

    public void addWinner(String txt) {
        winners += txt;
    }

    public void addToHistory(String txt) {
        this.moveHistory += txt;
    }

    public void createLogs() {
        try {
            createWinnersLog();
            createMoveHistoryLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createWinnersLog() throws IOException {
        String winnersFilename = "logs/winners_log.txt";
        try (BufferedWriter w = new BufferedWriter(new FileWriter(winnersFilename))) {
            w.write(winners);
        }
    }

    private void createMoveHistoryLog() throws IOException {
        String moveHistoryFilename = "logs/move_history_log.txt";
        try (BufferedWriter w = new BufferedWriter(new FileWriter(moveHistoryFilename))) {
            w.write(moveHistory);
        }
    }

}
