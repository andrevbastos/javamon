package model.abilities;

import model.pokemon.Pokemon;

public class AbilityFactory {
        public Ability get(String abilityName, Pokemon owner) {
        switch (abilityName.toUpperCase()) {
            case "ADAPTABILITY":
                return new Adaptability(owner);
            case "ICE BODY":
                return new IceBody(owner);
            case "PIXILATE":
                return new Pixilate(owner);
            default:
                System.out.println("Habilidade desconhecida: " + abilityName);
                return null;
        }
    }
}
