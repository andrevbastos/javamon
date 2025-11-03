package br.com.andrebastos.javamon_online.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonAbilityDto {
    @JsonProperty("pokemon_id")
    private Long pokemonId;
    private List<Long> abilities;

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public List<Long> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Long> abilities) {
        this.abilities = abilities;
    }
}