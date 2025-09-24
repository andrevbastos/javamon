package br.com.andrebastos.javamon_online.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonMovesetDto {

    @JsonProperty("pokemon_id")
    private Long pokemonId;

    @JsonProperty("pokemon_name")
    private String pokemonName;

    private List<Long> moves;

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public List<Long> getMoves() {
        return moves;
    }

    public void setMoves(List<Long> moves) {
        this.moves = moves;
    }
}