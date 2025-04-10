package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import model.Combat;
import model.pokemon.Pokemon;
import model.pokemon.PokemonFactory;
import view.Panel;

public class CombatState implements SimulationState {
    public ArrayList<Pokemon> selectedPokemons = new ArrayList<>();

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        
        g2d.drawString("Battling...", 50, 50);
    }

    @Override
    public void handleInput(Simulation sim, String input) { }

    @Override
    public void run(Simulation sim) {
        PokemonFactory pf = new PokemonFactory();
        
        for (String pokeName : sim.getSelectedPokemons()) {
            this.selectedPokemons.add(pf.getPokemon(pokeName));
        }
        
        Combat combat = new Combat(selectedPokemons);
        sim.addToLog(combat.run(sim.getRepetitions()));
        sim.setState(sim.getStatsState());
    }
}
