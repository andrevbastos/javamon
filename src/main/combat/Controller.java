package main.combat;

public class Controller {

    public static void startSimulation() {

        Trainer player = new Trainer("a");
        givePokemon(player, PokemonFactory.getPokemon("CHARMANDER"));

        Trainer inimigo = new Trainer("b");
        givePokemon(inimigo, PokemonFactory.getPokemon("SQUIRTLE"));

        Battle b = new Battle(player, inimigo);
        b.battle();

    }

    public static void givePokemon(Trainer t, Pokemon p) {
        t.setPokemon(p);
    }
    
}
