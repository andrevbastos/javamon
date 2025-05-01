package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.pokemon.Pokemon;
import view.Panel;

/**
 * @class StatsState
 * @brief Represents the statistics viewing phase of the simulation.
 * This state displays battle statistics and handles log creation.
 * Implements the SimulationState interface as part of the State design pattern.
 * 
 * @details
 * - Displays Pokémon win statistics in sorted order
 * - Provides pagination for viewing multiple Pokémon
 * - Handles log creation and simulation restart
 * - Manages transitions back to SelectionState
 * 
 * @note Pokémon sprites are loaded from "res/pokemon/" directory
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 * @see controller.SelectionState
 */
public class StatsState implements SimulationState {
    /** @brief Current page index for pagination */
    private int pokeIndex = 0;
    
    /** @brief List of Pokémon with their battle statistics */
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    /**
     * @brief Updates and renders the statistics screen
     * 
     * @param sim The Simulation controller
     * @param panel The view panel to render to
     * @param g2d Graphics context for drawing
     * 
     * @details
     * - Displays Pokémon win statistics in descending order
     * - Shows Pokémon sprites and win counts
     * - Renders navigation controls and options
     */
    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        pokemons = orderPokemons(sim.getPokemons());

        try {
            int startX = panel.width / 5;
            int spriteX = startX + 50;
            int nameX = spriteX + panel.width/10 + 20;
            int startY = 100;
            int lineHeight = 60;
            
            int endIndex = Math.min(pokeIndex + 5, pokemons.size());
            for (int i = pokeIndex; i < endIndex; i++) {
                int displayPos = i - pokeIndex + 1;
                int currentY = startY + (displayPos-1) * lineHeight;
                
                g2d.drawString(
                    displayPos + ". ",
                    startX,
                    currentY
                );
                String pokemonName = pokemons.get(i).getName().toLowerCase();
                BufferedImage sprite = ImageIO.read(new File("res/pokemon/" + pokemonName + ".png"));
                g2d.drawImage(
                    sprite, 
                    spriteX,
                    currentY - 10 - panel.height/20,
                    panel.width/10,
                    panel.height/10,
                    null
                );
                g2d.drawString(
                    pokemons.get(i).getName() + " - " + pokemons.get(i).getWins(),
                    nameX,
                    currentY
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        g2d.setFont(panel.pkmn.deriveFont(10f));
        g2d.drawString("Q to previous page", 10, panel.height - 70);
        g2d.drawString("E to next page", 10, panel.height - 50);
        g2d.drawString("ENTER to create logs", 10, panel.height - 30);
        g2d.drawString("R to restart", 10, panel.height - 10);
        g2d.drawString("" + (1 + (pokeIndex / 5)), panel.width - 30, panel.height - 10);
    }

    /**
     * @brief Handles user input for statistics screen
     * 
     * @param sim The Simulation controller
     * @param input The user input string
     * 
     * @details Handles:
     * - ENTER: Creates battle logs
     * - E/Q: Page navigation
     * - R: Restarts simulation (returns to SelectionState)
     */
    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("ENTER")) {
                sim.createLog();
            }
            if (input.equalsIgnoreCase("E")) {
                pokeIndex = Math.min(pokeIndex + 5, pokemons.size() - pokeIndex);
                sim.repaint();
                return;
            }
            if (input.equalsIgnoreCase("Q")) {
                pokeIndex = Math.max(0, pokeIndex - 5);
                sim.repaint();
                return;
            }
            if (input.equals("R")) {
                sim.reset();
                sim.setState(sim.getSelectionState());
            }
        } catch (NumberFormatException e) {
            System.out.println("Input inválido!");
        }
    }

    /**
     * @brief Orders Pokémon by win count (descending)
     * 
     * @param pokemons List of Pokémon to sort
     * @return Sorted list of Pokémon by win count
     */
    public ArrayList<Pokemon> orderPokemons(ArrayList<Pokemon> pokemons) {
        pokemons.sort((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()));
        return pokemons;
    }

    /**
     * @brief Resets the statistics state
     * 
     * Resets pagination index for new session
     */
    @Override
    public void reset() {
        pokeIndex = 0;
    }
}