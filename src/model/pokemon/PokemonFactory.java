package model.pokemon;

import model.abilities.Overgrow;
import model.moves.Move;
import model.moves.MovesFactory;
import model.util.Types.Type;

public class PokemonFactory {
    private final MovesFactory mf = new MovesFactory();
    private final Move charmanderMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("SCRATCH"),
        mf.getMove("EMBER"),
        mf.getMove("SMOKESCREEN")
    };
    
    private final Move bulbasaurMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("TACKLE"),
        mf.getMove("VINE WHIP"),
        mf.getMove("GROWTH")
    };

    private final Move squirtleMoves[] = {
        mf.getMove("TAIL WHIP"),
        mf.getMove("TACKLE"),
        mf.getMove("WATER GUN"),
        mf.getMove("WITHDRAW")
    };

    private final Move pikachuMoves[] = {
        mf.getMove("THUNDER SHOCK"),
        mf.getMove("GROWL"),
        mf.getMove("SWIFT"),
        mf.getMove("AGILITY")
    };

    private final Move caterpieMoves[] = {
        mf.getMove("TACKLE"),
        mf.getMove("STRING SHOT"),
        mf.getMove("BUG BITE"),
        mf.getMove("HARDEN")
    };

    private final Move poochyenaMoves[] = {
        mf.getMove("TACKLE"),
        mf.getMove("HOWL"),
        mf.getMove("BITE"),
        mf.getMove("SAND ATTACK")
    };

    private final Move axewMoves[] = {
        mf.getMove("SCRATCH"),
        mf.getMove("LEER"),
        mf.getMove("DRAGON RAGE"),
        mf.getMove("SLASH")
    };

    private final Move togepiMoves[] = {
        mf.getMove("GROWL"),
        mf.getMove("CHARM"),
        mf.getMove("METRONOME"),
        mf.getMove("FAIRY WIND")
    };

    private final Move mankeyMoves[] = {
        mf.getMove("SCRATCH"),
        mf.getMove("LEER"),
        mf.getMove("LOW KICK"),
        mf.getMove("KARATE CHOP")
    };

    private final Move rookideeMoves[] = {
        mf.getMove("PECK"),
        mf.getMove("LEER"),
        mf.getMove("POWER TRIP"),
        mf.getMove("WING ATTACK")
    };

    private final Move gastlyMoves[] = {
        mf.getMove("LICK"),
        mf.getMove("CONFUSE RAY"),
        mf.getMove("NIGHT SHADE"),
        mf.getMove("SMOG")
    };

    private final Move sandshrewMoves[] = {
        mf.getMove("SCRATCH"),
        mf.getMove("DEFENSE CURL"),
        mf.getMove("MUD-SLAP"),
        mf.getMove("ROLLOUT")
    };

    private final Move vulpixAlolaMoves[] = {
        mf.getMove("POWDER SNOW"),
        mf.getMove("TAIL WHIP"),
        mf.getMove("CONFUSE RAY"),
        mf.getMove("ICY WIND")
    };

    private final Move ratattaMoves[] = {
        mf.getMove("TACKLE"),
        mf.getMove("TAIL WHIP"),
        mf.getMove("QUICK ATTACK"),
        mf.getMove("HYPER FANG")
    };

    private final Move ekansMoves[] = {
        mf.getMove("POISON STING"),
        mf.getMove("LEER"),
        mf.getMove("WRAP"),
        mf.getMove("BITE")
    };

    private final Move spoinkMoves[] = {
        mf.getMove("SPLASH"),
        mf.getMove("PSYBEAM"),
        mf.getMove("CONFUSE RAY"),
        mf.getMove("PSYCH UP")
    };

    private final Move nosepassMoves[] = {
        mf.getMove("TACKLE"),
        mf.getMove("HARDEN"),
        mf.getMove("ROCK THROW"),
        mf.getMove("BLOCK")
    };

    private final Move klinkMoves[] = {
        mf.getMove("VICE GRIP"),
        mf.getMove("CHARGE"),
        mf.getMove("THUNDER SHOCK"),
        mf.getMove("GEAR GRIND")
    };

    public Pokemon getPokemon(String p) {		
        switch (p) {
            case "CHARMANDER" -> {
                Pokemon charmander = new Pokemon("CHARMANDER", Type.FIRE, 39, 52, 60, 43, 50, 65, 100, charmanderMoves);
                return charmander;
            }
        
            case "BULBASAUR" -> {
                Pokemon bulbasaur = new Pokemon("BULBASAUR", Type.GRASS, 45, 49, 65, 49, 65, 45, 100, bulbasaurMoves);
                bulbasaur.setAbility(new Overgrow(bulbasaur));
                return bulbasaur;
            }
        
            case "SQUIRTLE" -> {
                Pokemon squirtle = new Pokemon("SQUIRTLE", Type.WATER, 44, 48, 50, 65, 64, 43, 100, squirtleMoves);
                return squirtle;
            }
        
            case "PIKACHU" -> {
                Pokemon pikachu = new Pokemon("PIKACHU", Type.ELECTRIC, 35, 55, 50, 30, 40, 90, 100, pikachuMoves);
                return pikachu;
            }
        
            case "CATERPIE" -> {
                Pokemon caterpie = new Pokemon("CATERPIE", Type.BUG, 45, 30, 20, 35, 20, 45, 100, caterpieMoves);
                return caterpie;
            }
        
            case "POOCHYENA" -> {
                Pokemon poochyena = new Pokemon("POOCHYENA", Type.DARK, 35, 55, 30, 35, 30, 35, 100, poochyenaMoves);
                return poochyena;
            }
        
            case "AXEW" -> {
                Pokemon axew = new Pokemon("AXEW", Type.DRAGON, 46, 70, 30, 60, 40, 57, 100, axewMoves);
                return axew;
            }
        
            case "TOGEPI" -> {
                Pokemon togepi = new Pokemon("TOGEPI", Type.FAIRY, 35, 20, 40, 65, 65, 20, 100, togepiMoves);
                return togepi;
            }
        
            case "MANKEY" -> {
                Pokemon mankey = new Pokemon("MANKEY", Type.FIGHTING, 40, 80, 35, 35, 45, 70, 100, mankeyMoves);
                return mankey;
            }
        
            case "ROOKIDEE" -> {
                Pokemon rookidee = new Pokemon("ROOKIDEE", Type.FLYING, 38, 47, 33, 35, 35, 57, 100, rookideeMoves);
                return rookidee;
            }
        
            case "GASTLY" -> {
                Pokemon gastly = new Pokemon("GASTLY", Type.GHOST, 30, 35, 100, 30, 35, 80, 100, gastlyMoves);
                return gastly;
            }
        
            case "SANDSHREW" -> {
                Pokemon sandshrew = new Pokemon("SANDSHREW", Type.GROUND, 50, 75, 20, 85, 30, 40, 100, sandshrewMoves);
                return sandshrew;
            }
        
            case "VULPIX-ALOLA" -> {
                Pokemon vulpixAlola = new Pokemon("VULPIX-ALOLA", Type.ICE, 38, 41, 50, 40, 65, 65, 100, vulpixAlolaMoves);
                return vulpixAlola;
            }
        
            case "RATATTA" -> {
                Pokemon rattata = new Pokemon("RATATTA", Type.NORMAL, 30, 56, 25, 35, 35, 72, 100, ratattaMoves);
                return rattata;
            }
        
            case "EKANS" -> {
                Pokemon ekans = new Pokemon("EKANS", Type.POISON, 35, 60, 40, 44, 54, 55, 100, ekansMoves);
                return ekans;
            }
        
            case "SPOINK" -> {
                Pokemon spoink = new Pokemon("SPOINK", Type.PSYCHIC, 60, 25, 70, 35, 80, 60, 100, spoinkMoves);
                return spoink;
            }
        
            case "NOSEPASS" -> {
                Pokemon nosepass = new Pokemon("NOSEPASS", Type.ROCK, 30, 45, 45, 135, 90, 30, 100, nosepassMoves);
                return nosepass;
            }
        
            case "KLINK" -> {
                Pokemon klink = new Pokemon("KLINK", Type.STEEL, 40, 55, 45, 70, 60, 30, 100, klinkMoves);
                return klink;
            }
            
            default -> throw new IllegalArgumentException("Pokemon not found");
        }
    }
}
