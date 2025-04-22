package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import model.Combat;
import model.pokemon.Pokemon;
import model.pokemon.PokemonFactory;
import view.Panel;

public class CombatState implements SimulationState {
    private ArrayList<Pokemon> selectedPokemons = new ArrayList<>();
    private boolean battling = false;
    private String battleStatus = "Preparing to battle...";

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        g2d.drawString(battleStatus, 50, 50);
        
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
        
        sim.setState(sim.getStatsState());
        battling = false;
        battleStatus = "Preparing to battle...";
    }

    @Override
    public void handleInput(Simulation sim, String input) { }
}