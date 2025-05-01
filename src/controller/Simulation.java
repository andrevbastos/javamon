package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.pokemon.Pokemon;
import model.util.LogHandler;
import view.Panel;

/**
 * Simulation is the main controller class for the Javamon simulation.
 * It manages the states of the simulation, handles user input,
 * and coordinates the interaction between different components.
 * It is responsible for initializing the simulation, updating the states,
 * and managing the Pokémon selection and battle process.
 * All combat informations are stored to be given to the LogHandler class.
 * Using State design pattern, it allows for easy transition between different states
 * of the simulation (Selection, Combat, Stats).
 * By the Composite design pattern, it allows for easy addition of new states
 * and global updates to all states.
 * 
 * @see CompositeState
 * @see SimulationState
 * @see SelectionState
 * @see CombatState
 * @see StatsState
 * 
 * @see LogHandler
 *
 * @see view.Window
 */
public class Simulation {
    private final int repetitions = 100;

    private final Panel panel;
    private final CompositeState states = new CompositeState();
    private final SimulationState selectionState = new SelectionState();
    private final CombatState combatState = new CombatState();
    private final StatsState statsState = new StatsState();

    private final ArrayList<String> selectedPokemons = new ArrayList<>();
    private final ArrayList<Pokemon> pokemons = new ArrayList<>();

    private final LogHandler log = new LogHandler();

    private SimulationState currentState;
    
    public Simulation(Panel panel) {
        this.panel = panel;

        states.addState(selectionState);
        states.addState(combatState);
        states.addState(statsState);

        this.currentState = selectionState;
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
        if (selectedPokemons.contains(pokemonName)) 
            selectedPokemons.remove(pokemonName);
        else 
            selectedPokemons.add(pokemonName);
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
        states.reset();
        selectedPokemons.clear();
        pokemons.clear();
    }

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
