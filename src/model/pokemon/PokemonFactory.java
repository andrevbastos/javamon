package model.pokemon;

import model.abilities.AbilityFactory;
import model.moves.Move;
import model.moves.MovesFactory;
import model.util.Type;

/**
 * @class PokemonFactory
 * @brief The PokemonFactory class is responsible for creating instances of Pokémon.
 * It uses the MovesFactory and AbilityFactory to create moves and abilities
 * for the Pokémon.
 * 
 * @see model.moves.MovesFactory
 * @see model.abilities.AbilityFactory
 */
public class PokemonFactory {
    private final MovesFactory mf = new MovesFactory();
    private final AbilityFactory af = new AbilityFactory();

    /**
     * @brief Creates a Pokémon instance based on the given name.
     * @param p The name of the Pokémon to create (e.g., "EEVEE", "VAPOREON")
     * @return Pokemon The created Pokémon instance
     * @throws IllegalArgumentException If the Pokémon name is not recognized
     * 
     * @details
     * For each Pokémon, this method:
     * 1. Creates a moveset using MovesFactory
     * 2. Sets base stats according to canonical values
     * 3. Assigns an appropriate ability using AbilityFactory
     */
    public Pokemon getPokemon(String p) {		
        switch (p) {
            case "EEVEE" -> {
                Move[] moves = {
                    mf.getMove("COVET"),
                    mf.getMove("GROWL"),
                    mf.getMove("HEADBUTT"),
                    mf.getMove("TAIL WHIP")
                };
                Pokemon eevee = new Pokemon("EEVEE", Type.NORMAL, 55, 55, 50, 45, 65, 55, moves);
                eevee.setAbility(af.get("ADAPTABILITY", eevee));
                return eevee;
            }

            case "ESPEON" -> {
                Move[] moves = {
                    mf.getMove("PSYCHIC"), 
                    mf.getMove("PSYBEAM"), 
                    mf.getMove("SWIFT"),
                    mf.getMove("FIERY DANCE")
                };
                Pokemon espeon = new Pokemon("ESPEON", Type.PSYCHIC, 65, 65, 60, 130, 95, 110, moves);
                espeon.setAbility(af.get("MAGIC BOUNCE", espeon));
                return espeon;
            }

            case "UMBREON" -> {
                Move[] moves = {
                    mf.getMove("CRUNCH"), 
                    mf.getMove("CONFUSE RAY"), 
                    mf.getMove("FEINT ATTACK"), 
                    mf.getMove("CUT")
                };
                Pokemon umbreon = new Pokemon("UMBREON", Type.DARK, 95, 65, 110, 60, 130, 65, moves);
                umbreon.setAbility(af.get("SYNCHRONIZE", umbreon));
                return umbreon;
            }

            case "FLAREON" -> {
                Move[] moves = {
                    mf.getMove("SACRED FIRE"), 
                    mf.getMove("LAVA PLUME"), 
                    mf.getMove("SMOG"), 
                    mf.getMove("SCARY FACE")
                };
                Pokemon flareon = new Pokemon("FLAREON", Type.FIRE, 65, 130, 70, 95, 110, 95, moves);
                flareon.setAbility(af.get("FLASH FIRE", flareon));
                return flareon;
            }

            case "JOLTEON" -> {
                Move[] moves = {
                    mf.getMove("THUNDER"), 
                    mf.getMove("DISCHARGE"), 
                    mf.getMove("SIGNAL BEAM"), 
                    mf.getMove("AGILITY")
                };
                Pokemon jolteon = new Pokemon("JOLTEON", Type.ELECTRIC, 65, 65, 60, 110, 95, 130, moves);
                jolteon.setAbility(af.get("VOLT ABSORB", jolteon));
                return jolteon;
            }

            case "VAPOREON" -> {
                Move[] moves = {
                    mf.getMove("HYDRO PUMP"), 
                    mf.getMove("ACID ARMOR"), 
                    mf.getMove("ICE BEAM"), 
                    mf.getMove("SWIFT")
                };
                Pokemon vaporeon = new Pokemon("VAPOREON", Type.WATER, 130, 65, 60, 110, 95, 65, moves);
                vaporeon.setAbility(af.get("WATER ABSORB", vaporeon));
                return vaporeon;
            }

            case "LEAFEON" -> {
                Move[] moves = {
                    mf.getMove("LEAF BLADE"), 
                    mf.getMove("GRASS WHISTLE"), 
                    mf.getMove("SWORDS DANCE"), 
                    mf.getMove("IRON TAIL")
                };
                Pokemon leafeon = new Pokemon("LEAFEON", Type.GRASS, 65, 130, 110, 60, 65, 95, moves);
                leafeon.setAbility(af.get("SAP SIPPER", leafeon));
                return leafeon;
            }

            case "GLACEON" -> {
                Move[] moves = {
                    mf.getMove("BLIZZARD"), 
                    mf.getMove("FREEZE DRY"), 
                    mf.getMove("BARRIER"), 
                    mf.getMove("BITE")

                };
                Pokemon glaceon = new Pokemon("GLACEON", Type.ICE, 80, 45, 100, 130, 95, 75, moves);
                glaceon.setAbility(af.get("ICE BODY", glaceon));
                return glaceon;
            }

            case "SYLVEON" -> {
                Move[] moves = {
                    mf.getMove("MOONBLAST"), 
                    mf.getMove("LIGHT SCREEN"), 
                    mf.getMove("SWIFT"), 
                    mf.getMove("FLASH")
                };
                Pokemon sylveon = new Pokemon("SYLVEON", Type.FAIRY, 95, 65, 65, 110, 130, 60, moves);
                sylveon.setAbility(af.get("PIXILATE", sylveon));
                return sylveon;
            }
            
            default -> throw new IllegalArgumentException("Pokemon not found");
        }
    }
}
