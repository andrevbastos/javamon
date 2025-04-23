package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.pokemon.Pokemon;
import model.util.LogHandler;
import view.Panel;

public class Simulation {
    private final Panel panel;
    private final int repetitions = 1000;
    private final ArrayList<String> selectedPokemons = new ArrayList<>();
    private final ArrayList<Pokemon> pokemons = new ArrayList<>();

    private final LogHandler log = new LogHandler();

    private final SimulationState selectionState = new SelectionState();
    private final CombatState combatState = new CombatState();
    private final StatsState statsState = new StatsState();

    private SimulationState currentState;
    
    public Simulation(Panel panel) {
        this.panel = panel;
        this.currentState = new SelectionState();
    }
    
    public void update(Panel panel, Graphics2D g2d) {
        currentState.update(this, panel, g2d);
    }

    public void repaint() {
        panel.repaint();
        panel.revalidate();
    }
    
    public void handleInput(String input) {
        currentState.handleInput(this, input);
    }
    
    public void selectPokemon(String pokemonName) {
        if (selectedPokemons.contains(pokemonName)) {
            selectedPokemons.remove(pokemonName);
            System.out.println(pokemonName + " removido da seleção!");
        } else {
            selectedPokemons.add(pokemonName);
            System.out.println(pokemonName + " adicionado à seleção!");
        }
    }
    
    public void addToLog(String string) {
        log.addToLog(string);
    }

    public void createLog() {
        log.createLog();
    }
    
    public int getRepetitions() {
        return repetitions;
    }

    public ArrayList<String> getSelectedPokemons() {
        return selectedPokemons;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemon(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
    }

    public void setState(SimulationState state) {
        this.currentState = state;
        repaint();
    }

    public SimulationState getSelectionState() {
        return selectionState;
    }
        
    public CombatState getCombatState() {
        return combatState;
    }    
    
    public StatsState getStatsState() {
        return statsState;
    }
    
    public void reset() {
        selectedPokemons.clear();
        pokemons.clear();
    }
}    
