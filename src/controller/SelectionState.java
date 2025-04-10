package controller;

import java.awt.Graphics2D;
import view.Panel;

public class SelectionState implements SimulationState {
    private final String[] availablePokemons = {"CHARMANDER", "BULBASAUR", "SQUIRTLE", "PIKACHU", "GYARADOS", "GEODUDE", "JOLTEON", "VENUSAUR" };

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        
        g2d.drawString("Selecione pokémons:", 50, 50);
        
        for (int i = 0; i < availablePokemons.length; i++) {
            g2d.drawString((i+1) + ". " + availablePokemons[i], 50, 80 + i * 30);
        }
    }

    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("ENTER")) {
                if (sim.getSelectedPokemons().size() >= 2) {
                    sim.setState(sim.getCombatState());
                } else {
                    System.out.println("Not enough pokémons!");
                }
                return;
            }
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= availablePokemons.length) {
                String pokemonName = availablePokemons[choice - 1];
                sim.selectPokemon(pokemonName);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input inválido!");
        }
    }

    @Override
    public void run(Simulation sim) { }
}