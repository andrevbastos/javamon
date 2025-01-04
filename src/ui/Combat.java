package ui;

import java.io.*;
import models.*;

public class Combat {
    
    private GamePanel gp;
    private Pokemon[] selectedPokemons;
    private int repetitions = 1000;
    private String winners = "";
    private String moveHistory = "";
    private Pokemon currentP1;
    private Pokemon currentP2;
    private int rounds;

    public Combat(GamePanel gp) {

        this.gp = gp;

        PokemonFactory pf = new PokemonFactory();
        this.selectedPokemons = new Pokemon[] { pf.getPokemon("CHARMANDER"), pf.getPokemon("SQUIRTLE"), pf.getPokemon("BULBASAUR"), pf.getPokemon("PIKACHU")};

    }

    public void setcurrentP1(Pokemon p1) {
        this.currentP1 = p1;
    }

    public void setcurrentP2(Pokemon p2) {
        this.currentP2 = p2;
    }

    public void runCombatSequence() {
        for (int i = 0; i < selectedPokemons.length; i++) {
            for (int j = i + 1; j < selectedPokemons.length; j++) {
                if (i != j) {
                    setcurrentP1(selectedPokemons[i]);
                    setcurrentP2(selectedPokemons[j]);
                    for (int k = repetitions; k > 0; k--) {
                        run();
                    }
                    addWinner("\n");
                }
                gp.setCombatStats(selectedPokemons);
            }
        }

        createLogs();
        gp.setCombatStats(selectedPokemons);
        
    }

    public void run() {
        Pokemon first;
        Pokemon second;
        Pokemon winner = null;
        boolean stop = false;
        rounds = 0;

        addToHistory("(" + currentP1.getName() + " x " + currentP2.getName() + ":");

        while (!stop) {

            first = (currentP1.getSpeed() >= currentP2.getSpeed()) ? currentP1 : currentP2;
            second = (first == currentP1) ? currentP2 : currentP1;

            String firstMove = first.useMove(second);
            addToHistory(firstMove);
            
            if (second.getHp() != 0) {
                String secondMove = second.useMove(first);
                addToHistory(secondMove);
            }

            winner = (currentP1.getHp() == 0) ? currentP2 : (currentP2.getHp() == 0) ? currentP1 : null;
            stop = (winner != null);
            rounds++;

        }

        currentP1.heal();
        currentP2.heal();

        winner.addVictory();
        winner.setRounds(rounds);

        addToHistory(" " + rounds + ");\n");
        addWinner(winner.getName() + "; ");
    }

    public void addWinner(String txt) {
        winners += txt;
    }

    public void addToHistory(String txt) {
        this.moveHistory += txt;
    }

    public void createLogs() {
        try {        
            File logsDir = new File("logs");
            if (!logsDir.exists()) {
                logsDir.mkdirs();
            }
            createWinnersLog();
            createMoveHistoryLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createWinnersLog() throws IOException {
        String winnersFilename = "logs/winners_log.txt";
        try (BufferedWriter w = new BufferedWriter(new FileWriter(winnersFilename, false))) {
            w.write(winners);
        }
    }

    private void createMoveHistoryLog() throws IOException {
        String moveHistoryFilename = "logs/move_history_log.txt";
        try (BufferedWriter w = new BufferedWriter(new FileWriter(moveHistoryFilename, false))) {
            w.write(moveHistory);
        }
    }

}
