package combat;

import java.util.Random;

public class PokemonFactory {
    MovesFactory mf = new MovesFactory();

    Moves charmanderMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("SCRATCH"),
        mf.getMove("EMBER"),
        mf.getMove("SMOKESCREEN")
    };

    Moves bulbasaurMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("TACKLE"),
        mf.getMove("VINE WHIP"),
        mf.getMove("GROWTH")
    };

    Moves squirtleMoves[] = {
        mf.getMove("TAIL WHIP"),
        mf.getMove("TACKLE"),
        mf.getMove("WATER GUN"),
        mf.getMove("WITHDRAW")
    };

    Moves pikachuMoves[] = {
        mf.getMove("THUNDER SHOCK"),
        mf.getMove("GROWL"),
        mf.getMove("SWIFT"),
        mf.getMove("AGILITY")
    };

    public Pokemon getPokemon(String p) {		
        switch (p) {
            case "CHARMANDER":
                return new Pokemon("CHARMANDER", "FIRE", 39, 52, 60, 43, 50, 65, 100, charmanderMoves);
            
            case "BULBASAUR":
                return new Pokemon("BULBASAUR", "GRASS", 45, 49, 65, 49, 65, 45, 100, bulbasaurMoves);
                    
            case "SQUIRTLE":
                return new Pokemon("SQUIRTLE", "WATER", 44, 48, 50, 65, 64, 43, 100, squirtleMoves);
                
            case "PIKACHU":
                return new Pokemon("PIKACHU", "ELECTRIC", 45, 55, 40, 50, 50, 90, 100, squirtleMoves);
        
            default:
                throw new IllegalArgumentException("Pokemon not found");
        }
    }

    public Pokemon getRandomPokemon() {
        Random r = new Random();
        int n = r.nextInt(2);

        switch (n) {
            case 0:
                return getPokemon("CHARMANDER");
            case 1:
                return getPokemon("BULBASAUR");
            case 2:
                return getPokemon("SQUIRTLE");
            default:
                throw new IllegalArgumentException("Pokemon not found");
        }
    }

}
