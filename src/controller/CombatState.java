package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.FontMetrics;

import model.Combat;
import model.pokemon.Pokemon;
import model.pokemon.PokemonFactory;
import view.Panel;

/**
 * CombatState is a class that represents the state of the simulation
 * where the battle takes place. It implements the SimulationState.
 * It handles the battle logic and updates the simulation state accordingly.
 * Once the battle is complete, it transitions to the StatsState.
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 * @see controller.StatsState
 */
public class CombatState implements SimulationState {
    private ArrayList<Pokemon> selectedPokemons = new ArrayList<>();
    private boolean battling = false;
    private String battleStatus = "Preparing to battle...";

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

    @Override
    public void handleInput(Simulation sim, String input) { }

    @Override
    public void reset() {
        selectedPokemons.clear();
        battling = false;
        battleStatus = "Preparing to battle...";
    }
}