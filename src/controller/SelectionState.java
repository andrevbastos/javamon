package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.io.File;
import view.Panel;

/**
 * SelectionState is a class that represents the state of the simulation
 * where the user can select their Pokémon. It implements the SimulationState.
 * Once user selects at least 2 Pokémon, it transitions to the CombatState.
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 * @see controller.CombatState
 */
public class SelectionState implements SimulationState {
    private int pokeIndex = 0;
    private final ArrayList<String> availablePokemons = new ArrayList<>(); 

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

    @Override
    public void reset() {
        pokeIndex = 0;
    }
}