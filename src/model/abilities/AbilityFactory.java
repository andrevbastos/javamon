package model.abilities;
import model.pokemon.Pokemon;

/**
 * @class AbilityFactory
 * @brief Factory for creating Pokémon ability instances.
 * Implements the Factory Method pattern to instantiate specific ability
 * implementations based on string identifiers.
 * 
 * @details
 * - Supports 4 core abilities (adaptability, ice body, pixilate, magic bounce)
 * - Gracefully handles unknown abilities
 * - Maintains owner Pokémon reference
 * 
 * @warning Logs to console for unknown abilities
 * 
 * @see model.pokemon.Pokemon
 * @see model.abilities.Ability
 */
public class AbilityFactory {
    /**
     * @brief Creates ability instance by name
     * @param abilityName Case-insensitive ability name
     * @param owner The Pokémon owning this ability
     * @return Ability instance or null if unknown
     */
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
