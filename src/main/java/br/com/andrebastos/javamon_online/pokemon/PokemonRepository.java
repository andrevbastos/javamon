package br.com.andrebastos.javamon_online.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andrebastos.javamon_online.pokemon.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}