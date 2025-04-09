package simulation;

import ui.Panel;
import java.awt.Graphics2D;
import java.util.ArrayList;

import simulation.pokemon.Pokemon;
import simulation.pokemon.PokemonFactory;

public class CombatSim {
    private ArrayList<Pokemon> pokemons = new ArrayList<>();

    public CombatSim() {
        PokemonFactory pokemonFactory = new PokemonFactory();
        this.pokemons.add(pokemonFactory.getPokemon("CHARMANDER"));
        this.pokemons.add(pokemonFactory.getPokemon("SQUIRTLE"));
        this.pokemons.add(pokemonFactory.getPokemon("BULBASAUR"));
        this.pokemons.add(pokemonFactory.getPokemon("PIKACHU"));
    }

    public void update(Panel p, Graphics2D g) {      

    }
}
