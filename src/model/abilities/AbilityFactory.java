package model.abilities;

import model.pokemon.Pokemon;

public class AbilityFactory {
        public Ability get(String abilityName, Pokemon owner) {
        switch (abilityName.toUpperCase()) {
            case "OVERGROW":
                return new Overgrow(owner);
            case "BLAZE":
                return new Blaze(owner);
            case "TORRENT":
                return new Torrent(owner);
            default:
                System.out.println("Habilidade desconhecida: " + abilityName);
                return null;
        }
    }
}
