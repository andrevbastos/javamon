package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.pokemon.Pokemon;
import view.Panel;

/**
 * StatsState is a class that represents the state of the simulation
 * where the user can view the statistics of the Pokémon battles
 * and create the logs of the battle's statistics.
 * It implements the SimulationState.
 * By user input, it can either transition back to the SelectionState
 * or end the aplication.
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 * @see controller.SelectionState
 */
public class StatsState implements SimulationState {
    private int pokeIndex = 0;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

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

    public ArrayList<Pokemon> orderPokemons(ArrayList<Pokemon> pokemons) {
        pokemons.sort((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()));
        return pokemons;
    }

    @Override
    public void reset() {
        pokeIndex = 0;
    }
}
