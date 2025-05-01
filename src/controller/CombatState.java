package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.FontMetrics;

import model.Combat;
import model.pokemon.Pokemon;
import model.pokemon.PokemonFactory;
import view.Panel;

/**
 * @class CombatState
 * @brief Represents the battle phase of the simulation.
 * This state handles Pokémon battles and implements the SimulationState interface
 * as part of the State design pattern. It manages:
 * - Battle preparation and execution
 * - Combat simulation between selected Pokémon
 * - Transition to statistics display after battle completion
 * 
 * @details
 * - Creates Pokémon instances from selected names
 * - Runs battles in a separate thread to avoid UI freezing
 * - Updates battle status messages during different phases
 * - Delegates actual combat logic to the Combat class
 * 
 * @note Battle results are logged through the Simulation controller
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 * @see controller.StatsState
 * @see model.Combat
 * @see model.pokemon.PokemonFactory
 */
public class CombatState implements SimulationState {
    /** @brief List of Pokémon participating in battle */
    private ArrayList<Pokemon> selectedPokemons = new ArrayList<>();
    
    /** @brief Flag indicating if battle is in progress */
    private boolean battling = false;
    
    /** @brief Current status message of the battle */
    private String battleStatus = "Preparing to battle...";

    /**
     * @brief Updates and renders the combat screen
     * 
     * @param sim The Simulation controller
     * @param panel The view panel to render to
     * @param g2d Graphics context for drawing
     * 
     * @details
     * - Displays current battle status centered on screen
     * - Starts battle thread if not already running
     */
    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(battleStatus);
        g2d.drawString(battleStatus, 300 - (textWidth / 2), 300);
        
        if (!battling) {
            battling = true;
            new Thread(() -> run(sim)).start();
        }
    }

    /**
     * @brief Executes the battle logic in a separate thread
     * 
     * @param sim The Simulation controller
     * 
     * @details
     * - Creates Pokémon instances from selected names
     * - Updates status messages through battle phases
     * - Runs combat simulation for configured repetitions
     * - Transitions to StatsState when complete
     * 
     * @note This method runs asynchronously to avoid UI freezing
     */
    public void run(Simulation sim) {
        PokemonFactory pf = new PokemonFactory();
        
        for (String pokeName : sim.getSelectedPokemons()) {
            selectedPokemons.add(pf.getPokemon(pokeName));
        }
        sim.setPokemon(selectedPokemons);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        battleStatus = "Battling...";
        sim.repaint();
        
        Combat combat = new Combat(selectedPokemons);
        sim.addToLog(combat.run(sim.getRepetitions()));
        
        battleStatus = "Battle complete!";
        sim.repaint();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        sim.setPokemon(selectedPokemons);
        sim.setState(sim.getStatsState());
    }

    /**
     * @brief Handles user input during combat
     * 
     * @param sim The Simulation controller
     * @param input The user input string
     * 
     * @note Currently no input is handled during battle phase
     */
    @Override
    public void handleInput(Simulation sim, String input) { }

    /**
     * @brief Resets the combat state
     * 
     * Clears selected Pokémon and resets battle flags and messages
     * for a new battle session.
     */
    @Override
    public void reset() {
        selectedPokemons.clear();
        battling = false;
        battleStatus = "Preparing to battle...";
    }
}