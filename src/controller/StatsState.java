package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.pokemon.Pokemon;
import view.Panel;

public class StatsState implements SimulationState {
    private int pokeIndex = 0;

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(10f));
        ArrayList<Pokemon> pokemons = orderPokemons(sim.getPokemons());
        System.out.println(sim.getPokemons().size());
        System.out.println(pokemons.size());

        int endIndex = Math.min(pokeIndex + 9, pokemons.size());
        for (int i = pokeIndex; i < endIndex; i++) {
            int displayPos = i - pokeIndex + 1;
            g2d.drawString(
                displayPos + ". " + pokemons.get(i).getName() + " - " + pokemons.get(i).getWins(),
                (panel.width/2) - (panel.width/5),
                60 + (displayPos-1) * 30
            );
        }

        g2d.setFont(panel.pkmn.deriveFont(10f));
        g2d.drawString("E to create logs", 10, panel.height - 50);
        g2d.drawString("Q to battle again", 10, panel.height - 30);
        g2d.drawString("R to restart", 10, panel.height - 10);
    }

    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("E")) {
                sim.createLog();
            }
            if (input.equals("Q")) {
                sim.setState(sim.getCombatState());
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
}
