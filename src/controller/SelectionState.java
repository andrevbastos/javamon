package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.io.File;
import view.Panel;

/**
 * @class SelectionState
 * @brief Represents the Pokémon selection phase of the simulation.
 * This state allows users to select their Pokémon before battle. 
 * Implements the SimulationState interface as part of the State design pattern.
 * 
 * @details 
 * - Manages a list of available Pokémon (Eevee and its evolutions)
 * - Handles pagination for displaying Pokémon selection
 * - Transitions to CombatState when at least 2 Pokémon are selected
 * - Implements all required SimulationState interface methods
 * 
 * @note The Pokémon sprites are loaded from the "res/pokemon/" directory
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 * @see controller.CombatState
 */
public class SelectionState implements SimulationState {
    /** @brief Current page index for Pokémon display pagination */
    private int pokeIndex = 0;
    
    /** @brief List of available Pokémon names */
    private final ArrayList<String> availablePokemons = new ArrayList<>(); 

    /**
     * @brief Constructs a SelectionState with default Pokémon roster
     * 
     * Initializes the available Pokémon list with Eevee and its evolutions.
     */
    public SelectionState() {
        availablePokemons.add("EEVEE");
        availablePokemons.add("ESPEON");
        availablePokemons.add("UMBREON");
        availablePokemons.add("FLAREON");
        availablePokemons.add("JOLTEON");
        availablePokemons.add("VAPOREON");
        availablePokemons.add("LEAFEON");
        availablePokemons.add("GLACEON");
        availablePokemons.add("SYLVEON");
    }

    /**
     * @brief Updates and renders the selection screen
     * 
     * @param sim The Simulation controller
     * @param panel The view panel to render to
     * @param g2d Graphics context for drawing
     * 
     * @details
     * - Displays selection instructions
     * - Renders available Pokémon with pagination
     * - Shows selected Pokémon in color, others in grayscale
     * - Displays navigation controls
     */
    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        g2d.drawString("Select your pokémons", 50, 50);
                
        try {
            int startX = panel.width / 5;
            int spriteX = startX + 50;
            int nameX = spriteX + panel.width/10 + 20;
            int startY = 100;
            int lineHeight = 60;
            
            int endIndex = Math.min(pokeIndex + 5, availablePokemons.size());
            for (int i = pokeIndex; i < endIndex; i++) {
                int displayPos = i - pokeIndex + 1;
                int currentY = startY + (displayPos-1) * lineHeight;
                
                g2d.drawString(
                    displayPos + ". ",
                    startX,
                    currentY
                );
                String pokemonName = availablePokemons.get(i).toLowerCase();
                BufferedImage sprite = ImageIO.read(new File("res/pokemon/" + pokemonName + ".png"));
                if (!sim.getSelectedPokemons().contains(availablePokemons.get(i))) {
                    sprite = sim.convertToBlackAndWhite(sprite);
                }
                g2d.drawImage(
                    sprite, 
                    spriteX,
                    currentY - 10 - panel.height/20,
                    panel.width/10,
                    panel.height/10,
                    null
                );
                g2d.drawString(
                    availablePokemons.get(i),
                    nameX,
                    currentY
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        g2d.setFont(panel.pkmn.deriveFont(10f));
        g2d.drawString("E to next page", 10, panel.height - 10);
        g2d.drawString("Q to previous page", 10, panel.height - 30);
        g2d.drawString("" + (1 + (pokeIndex / 5)), panel.width - 30, panel.height - 10);
    }

    /**
     * @brief Handles user input for Pokémon selection
     * 
     * @param sim The Simulation controller
     * @param input The user input string
     * 
     * @details Handles:
     * - ENTER: Confirm selection and transition to combat (if >= 2 Pokémon)
     * - E/Q: Page navigation through available Pokémon
     * - Number inputs: Select/deselect specific Pokémon
     */
    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("ENTER")) {
                if (sim.getSelectedPokemons().size() >= 2) {
                    sim.setState(sim.getCombatState());
                } else {
                    System.out.println("Selecione pelo menos 2 Pokémon!");
                }
                return;
            }
            if (input.equalsIgnoreCase("E")) {
                pokeIndex = Math.min(pokeIndex + 5, availablePokemons.size() - pokeIndex);
                sim.repaint();
                return;
            }
            if (input.equalsIgnoreCase("Q")) {
                pokeIndex = Math.max(0, pokeIndex - 5);
                sim.repaint();
                return;
            }
    
            int choice = Integer.parseInt(input);
            int displayedPokemons = Math.min(5, availablePokemons.size() - pokeIndex);
            if (choice >= 1 && choice <= displayedPokemons) {
                String pokemonName = availablePokemons.get(pokeIndex + choice - 1);
                sim.selectPokemon(pokemonName);
                sim.repaint();
            } else {
                System.out.println("Número inválido! Escolha de 1 até " + displayedPokemons);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input inválido!");
        }
    }

    /**
     * @brief Resets the selection state
     * 
     * Resets the pagination index to 0 for a new selection session.
     */
    @Override
    public void reset() {
        pokeIndex = 0;
    }
}