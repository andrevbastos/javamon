package model.pokemon;

import model.abilities.AbilityFactory;
import model.moves.Move;
import model.moves.MovesFactory;
import model.util.Type;

public class PokemonFactory {
    private final MovesFactory mf = new MovesFactory();
    private final AbilityFactory af = new AbilityFactory();
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
                    mf.getMove("CONFUSION"),
                    mf.getMove("TACKLE"),
                    mf.getMove("TAIL WHIP"),
                    mf.getMove("SWIFT")
                };
                Pokemon espeon = new Pokemon("ESPEON", Type.PSYCHIC, 65, 65, 60, 130, 95, 110, moves);
                espeon.setAbility(af.get("MAGIC BOUNCE", espeon));
                return espeon;
            }

            case "UMBREON" -> {
                Move[] moves = {
                    mf.getMove("BITE"),
                    mf.getMove("TACKLE"),
                    mf.getMove("TAIL WHIP"),
                    mf.getMove("SCREECH")
                };
                Pokemon umbreon = new Pokemon("UMBREON", Type.DARK, 95, 65, 110, 60, 130, 65, moves);
                umbreon.setAbility(af.get("SYNCHRONIZE", umbreon));
                return umbreon;
            }

            case "FLAREON" -> {
                Move[] moves = {
                    mf.getMove("FIRE FANG"),
                    mf.getMove("TACKLE"),
                    mf.getMove("TAIL WHIP"),
                    mf.getMove("BITE")
                };
                Pokemon flareon = new Pokemon("FLAREON", Type.FIRE, 65, 130, 70, 95, 110, 95, moves);
                flareon.setAbility(af.get("FLASH FIRE", flareon));
                return flareon;
            }

            case "JOLTEON" -> {
                Move[] moves = {
                    mf.getMove("FLASH"),
                    mf.getMove("TACKLE"),
                    mf.getMove("TAIL WHIP"),
                    mf.getMove("THUNDERSHOCK")
                };
                Pokemon jolteon = new Pokemon("JOLTEON", Type.ELECTRIC, 65, 65, 60, 110, 95, 130, moves);
                jolteon.setAbility(af.get("VOLT ABSORB", jolteon));
                return jolteon;
            }

            case "VAPOREON" -> {
                Move[] moves = {
                    mf.getMove("WATER PULSE"),
                    mf.getMove("TACKLE"),
                    mf.getMove("TAIL WHIP"),
                    mf.getMove("AURORA BEAM")
                };
                Pokemon vaporeon = new Pokemon("VAPOREON", Type.WATER, 130, 65, 60, 110, 95, 65, moves);
                vaporeon.setAbility(af.get("WATER ABSORB", vaporeon));
                return vaporeon;
            }

            case "LEAFEON" -> {
                Move[] moves = {
                    mf.getMove("RAZOR LEAF"),
                    mf.getMove("TACKLE"),
                    mf.getMove("TAIL WHIP"),
                    mf.getMove("GRASS WHISTLE")
                };
                Pokemon leafeon = new Pokemon("LEAFEON", Type.GRASS, 65, 130, 110, 60, 65, 95, moves);
                leafeon.setAbility(af.get("SAP SIPPER", leafeon));
                return leafeon;
            }

            case "GLACEON" -> {
                Move[] moves = {
                    mf.getMove("ICY WIND"),
                    mf.getMove("TACKLE"),
                    mf.getMove("GROWL"),
                    mf.getMove("BITE")
                };
                Pokemon glaceon = new Pokemon("GLACEON", Type.ICE, 80, 45, 100, 130, 95, 75, moves);
                glaceon.setAbility(af.get("ICE BODY", glaceon));
                return glaceon;
            }

            case "SYLVEON" -> {
                Move[] moves = {
                    mf.getMove("DISARM CRY"),
                    mf.getMove("TACKLE"),
                    mf.getMove("GROWL"),
                    mf.getMove("SWIFT")
                };
                Pokemon sylveon = new Pokemon("SYLVEON", Type.FAIRY, 95, 65, 65, 110, 130, 60, moves);
                sylveon.setAbility(af.get("PIXILATE", sylveon));
                return sylveon;
            }
            
            default -> throw new IllegalArgumentException("Pokemon not found");
        }
    }
}
