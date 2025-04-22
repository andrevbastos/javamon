package model.pokemon;

import model.moves.Moves;
import model.moves.MovesFactory;
import model.util.Types.Type;

public class PokemonFactory {
    private final MovesFactory mf = new MovesFactory();
    private final Moves charmanderMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("SCRATCH"),
        mf.getMove("EMBER"),
        mf.getMove("SMOKESCREEN")
    };
    
    private final Moves bulbasaurMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("TACKLE"),
        mf.getMove("VINE WHIP"),
        mf.getMove("GROWTH")
    };

    private final Moves squirtleMoves[] = {
        mf.getMove("TAIL WHIP"),
        mf.getMove("TACKLE"),
        mf.getMove("WATER GUN"),
        mf.getMove("WITHDRAW")
    };

    private final Moves pikachuMoves[] = {
        mf.getMove("THUNDER SHOCK"),
        mf.getMove("GROWL"),
        mf.getMove("SWIFT"),
        mf.getMove("AGILITY")
    };

    private final Moves gyaradosMoves[] = {
        mf.getMove("SPLASH"),
        mf.getMove("TACKLE"),
        mf.getMove("WATER GUN"),
        mf.getMove("BITE")
    };

    private final Moves geodudeMoves[] = {
        mf.getMove("TACKLE"),
        mf.getMove("ROCK THROW"),
        mf.getMove("DEFENSE CURL"),
        mf.getMove("ROLLOUT")
    };

    private final Moves jolteonMoves[] = {
        mf.getMove("THUNDER SHOCK"),
        mf.getMove("SWIFT"),
        mf.getMove("AGILITY"),
        mf.getMove("THUNDERBOLT")
    };

    private final Moves venusaurMoves[] = {
        mf.getMove("VINE WHIP"),
        mf.getMove("GROWTH"),
        mf.getMove("RAZOR LEAF"),
        mf.getMove("SOLAR BEAM")
    };

    public Pokemon getPokemon(String p) {		
        switch (p) {
            case "CHARMANDER" -> {
                return new Pokemon("CHARMANDER", Type.FIRE, 39, 52, 60, 43, 50, 65, 100, charmanderMoves);
            }
            
            case "BULBASAUR" -> {
                return new Pokemon("BULBASAUR", Type.GRASS, 45, 49, 65, 49, 65, 45, 100, bulbasaurMoves);
            }
                    
            case "SQUIRTLE" -> {
                return new Pokemon("SQUIRTLE", Type.WATER, 44, 48, 50, 65, 64, 43, 100, squirtleMoves);
            }
                
            case "PIKACHU" -> {
                return new Pokemon("PIKACHU", Type.ELECTRIC, 45, 55, 40, 50, 50, 90, 100, pikachuMoves);
            }
            
            case "GYARADOS" -> {
                return new Pokemon("GYARADOS", Type.WATER, 95, 125, 79, 100, 81, 81, 100, gyaradosMoves);
            }
            
            case "GEODUDE" -> {
                return new Pokemon("GEODUDE", Type.ROCK, 40, 80, 100, 30, 30, 20, 100, geodudeMoves);
            }
            
            case "JOLTEON" -> {
                return new Pokemon("JOLTEON", Type.ELECTRIC, 65, 65, 60, 110, 95, 130, 100, jolteonMoves);
            }
            
            case "VENUSAUR" -> {
                return new Pokemon("VENUSAUR", Type.GRASS, 80, 82, 83, 100, 100, 80, 100, venusaurMoves);
            }
            
            default -> throw new IllegalArgumentException("Pokemon not found");
        }
    }
}
