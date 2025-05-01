package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.pokemon.Pokemon;
import model.util.LogHandler;
import view.Panel;

/**
 * @class Simulation
 * @brief Main controller class for the Javamon simulation.
 * The Simulation class is the core controller that manages:
 * - Simulation states (Selection, Combat, Statistics)
 * - User input handling
 * - Component interactions
 * - Pokémon selection and battle process
 * 
 * Implements design patterns:
 * - State: For transitioning between simulation states
 * - Composite: For easy addition of new states and global updates
 * 
 * @note All combat information is stored for processing by the LogHandler.
 * 
 * @see controller.CompositeState
 * @see controller.SimulationState
 * @see controller.SelectionState
 * @see controller.CombatState
 * @see controller.StatsState
 * @see model.util.LogHandler
 * @see view.Panel
 */
public class Simulation {
    /** @brief Number of repetitions for statistical simulation */
    private final int repetitions = 100;

    /** @brief Reference to the view panel */
    private final Panel panel;
    
    /** @brief Collection of all simulation states */
    private final CompositeState states = new CompositeState();
    
    /** @brief Pokémon selection state */
    private final SelectionState selectionState = new SelectionState();
    
    /** @brief Pokémon battle state */
    private final CombatState combatState = new CombatState();
    
    /** @brief Statistics display state */
    private final StatsState statsState = new StatsState();

    /** @brief List of selected Pokémon names */
    private final ArrayList<String> selectedPokemons = new ArrayList<>();
    
    /** @brief List of Pokémon instances */
    private final ArrayList<Pokemon> pokemons = new ArrayList<>();

    /** @brief Logger handler for combat records */
    private final LogHandler log = new LogHandler();

    /** @brief Current active state of the simulation */
    private SimulationState currentState;
    
    /**
     * @brief Constructs a Simulation controller
     * @param panel The view panel to control
     */
    public Simulation(Panel panel) {
        this.panel = panel;

        states.addState(selectionState);
        states.addState(combatState);
        states.addState(statsState);

        this.currentState = selectionState;
    }
    
    /**
     * @brief Updates the simulation state
     * @param panel The view panel
     * @param g2d Graphics context for rendering
     */
    public void update(Panel panel, Graphics2D g2d) {
        currentState.update(this, panel, g2d);
    }

    /**
     * @brief Triggers panel repaint
     */
    public void repaint() {
        panel.repaint();
        panel.revalidate();
    }
    
    /**
     * @brief Handles user input
     * @param input The input string to process
     */
    public void handleInput(String input) {
        currentState.handleInput(this, input);
    }
    
    /**
     * @brief Toggles Pokémon selection
     * @param pokemonName Name of the Pokémon to select/deselect
     */
    public void selectPokemon(String pokemonName) {
        if (selectedPokemons.contains(pokemonName)) 
            selectedPokemons.remove(pokemonName);
        else 
            selectedPokemons.add(pokemonName);
    }
    
    /**
     * @brief Adds a message to the combat log
     * @param string The log message to add
     */
    public void addToLog(String string) {
        log.addToLog(string);
    }

    /**
     * @brief Finalizes and creates the combat log
     */
    public void createLog() {
        log.createLog();
    }
    
    /**
     * @brief Gets the number of simulation repetitions
     * @return Number of repetitions
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * @brief Gets selected Pokémon names
     * @return List of selected Pokémon names
     */
    public ArrayList<String> getSelectedPokemons() {
        return selectedPokemons;
    }

    /**
     * @brief Gets Pokémon instances
     * @return List of Pokémon objects
     */
    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * @brief Sets the Pokémon roster
     * @param pokemons List of Pokémon to add
     */
    public void setPokemon(ArrayList<Pokemon> pokemons) {
        this.pokemons.addAll(pokemons);
    }

    /**
     * @brief Changes the current simulation state
     * @param state The new state to transition to
     */
    public void setState(SimulationState state) {
        this.currentState = state;
        repaint();
    }

    /**
     * @brief Gets the selection state
     * @return Selection state instance
     */
    public SimulationState getSelectionState() {
        return selectionState;
    }
        
    /**
     * @brief Gets the combat state
     * @return Combat state instance
     */
    public CombatState getCombatState() {
        return combatState;
    }    
    
    /**
     * @brief Gets the statistics state
     * @return Statistics state instance
     */
    public StatsState getStatsState() {
        return statsState;
    }
    
    /**
     * @brief Resets the simulation to initial state
     */
    public void reset() {
        states.reset();
        selectedPokemons.clear();
        pokemons.clear();
    }

    /**
     * @brief Converts an image to grayscale
     * @param coloredImage The input color image
     * @return Grayscale version of the image
     */
    public BufferedImage convertToBlackAndWhite(BufferedImage coloredImage) {
        BufferedImage grayImage = new BufferedImage(
            coloredImage.getWidth(), 
            coloredImage.getHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        
        for (int y = 0; y < coloredImage.getHeight(); y++) {
            for (int x = 0; x < coloredImage.getWidth(); x++) {
                int rgb = coloredImage.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                
                if (alpha == 0) {
                    grayImage.setRGB(x, y, rgb);
                } else {
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    int grayValue = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                    int grayRGB = (alpha << 24) | (grayValue << 16) | (grayValue << 8) | grayValue;
                    grayImage.setRGB(x, y, grayRGB);
                }
            }
        }
        return grayImage;
    }
}