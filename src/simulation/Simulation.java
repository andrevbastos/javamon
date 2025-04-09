package simulation;

import ui.Panel;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Simulation {
    private ArrayList<String> selectedPokemons = new ArrayList<>();
    private SimulationState selectionState = new SelectionState();
    private CombatState combatState = new CombatState();

    private SimulationState currentState;

    public Simulation() {
        this.currentState = new SelectionState();
    }

    public void update(Panel panel, Graphics2D g2d) {
        currentState.update(this, panel, g2d);
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

    public void setState(SimulationState state) {
        this.currentState = state;
    }

    public ArrayList<String> getSelectedPokemons() {
        return selectedPokemons;
    }

    public SimulationState getSelectionState() {
        return selectionState;
    }
    
    public CombatState getCombatState() {
        return combatState;
    }

    public void reset() {
        selectedPokemons.clear();
    }
}