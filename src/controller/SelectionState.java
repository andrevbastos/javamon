package controller;

import java.awt.Graphics2D;
import view.Panel;

public class SelectionState implements SimulationState {
    private int pokeIndex = 0;
    private final String[] availablePokemons = {
        "CHARMANDER", "BULBASAUR", "SQUIRTLE",
        "PIKACHU", "CATERPIE", "POOCHYENA",
        "AXEW", "TOGEPI", "MANKEY", 
        "ROOKIDEE", "GASTLY", "SANDSHREW", 
        "VULPIX-ALOLA", "RATATTA", "EKANS", 
        "SPOINK", "NOSEPASS", "KLINK"
    };

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        g2d.drawString("Select your pokémons", 50, 50);
        
        g2d.setFont(panel.pkmn.deriveFont(10f));
        int endIndex = Math.min(pokeIndex + 9, availablePokemons.length);
        
        for (int i = pokeIndex; i < endIndex; i++) {
            int displayPos = i - pokeIndex + 1;
            g2d.drawString(
                displayPos + ". " + availablePokemons[i],
                (panel.width/2) - (panel.width/5),
                80 + (displayPos-1) * 30
            );
        }
        
        g2d.drawString("E to next page", 10, panel.height - 10);
        g2d.drawString("Q to next page", 10, panel.height - 30);
        g2d.drawString("" + (1+(pokeIndex/9)), panel.width - 30, panel.height - 10);
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
                pokeIndex = Math.min(pokeIndex + 9, availablePokemons.length - 9);
                sim.repaint();
                return;
            }
            if (input.equalsIgnoreCase("Q")) {
                pokeIndex = Math.max(0, pokeIndex - 9);
                sim.repaint();
                return;
            }
    
            int choice = Integer.parseInt(input);
            int displayedPokemons = Math.min(9, availablePokemons.length - pokeIndex);
            if (choice >= 1 && choice <= displayedPokemons) {
                String pokemonName = availablePokemons[pokeIndex + choice - 1];
                sim.selectPokemon(pokemonName);
                sim.repaint();
            } else {
                System.out.println("Invalid number! Choose from 1 to " + displayedPokemons);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
}