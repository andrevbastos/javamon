package model.abilities;

import model.pokemon.Pokemon;

/**
 * The AbilityFactory class is responsible for creating instances of different
 * Pokémon abilities. It provides a method to retrieve an ability based on its
 * name and the Pokémon that owns it.
 * 
 * @see model.pokemon.Pokemon
 * @see model.abilities.Ability
 */
public class AbilityFactory {
        public Ability get(String abilityName, Pokemon owner) {
        switch (abilityName.toUpperCase()) {
            case "ADAPTABILITY":
                return new Adaptability(owner);
            case "ICE BODY":
                return new IceBody(owner);
            case "PIXILATE":
                return new Pixilate(owner);
            case "MAGIC BOUNCE":
                return new MagicBounce(owner);
            default:
                System.out.println("Habilidade desconhecida: " + abilityName);
                return null;
        }
    }
}
