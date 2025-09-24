package br.com.andrebastos.javamon_online;

import br.com.andrebastos.javamon_online.common.MoveJsonDto;
import br.com.andrebastos.javamon_online.common.PokemonJsonDto;
import br.com.andrebastos.javamon_online.common.PokemonMovesetDto;
import br.com.andrebastos.javamon_online.move.Move;
import br.com.andrebastos.javamon_online.move.MoveRepository;
import br.com.andrebastos.javamon_online.pokemon.Pokemon;
import br.com.andrebastos.javamon_online.pokemon.PokemonRepository;
import br.com.andrebastos.javamon_online.shared.Category;
import br.com.andrebastos.javamon_online.shared.Status;
import br.com.andrebastos.javamon_online.shared.Type;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class DbSeeder implements CommandLineRunner {

    private final PokemonRepository pokemonRepository;
    private final MoveRepository moveRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper mapper = new ObjectMapper();

    public DbSeeder(PokemonRepository pokemonRepository, MoveRepository moveRepository, ResourceLoader resourceLoader) {
        this.pokemonRepository = pokemonRepository;
        this.moveRepository = moveRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (pokemonRepository.count() > 0) {
            System.out.println("O banco de dados já parece estar populado. Seeder não será executado.");
            return;
        }

        System.out.println("Iniciando o seeder do banco de dados a partir de arquivos JSON...");

        Map<Long, Move> allMoves = loadMoves();
        Map<Long, Pokemon> allPokemons = loadPokemons();
        loadPokemonMoveRelations(allPokemons, allMoves);

        System.out.println("Seeder finalizado com sucesso!");
    }

    private Map<Long, Move> loadMoves() throws Exception {
        InputStream inputStream = resourceLoader.getResource("classpath:data/moves.json").getInputStream();
        List<MoveJsonDto> moveDtos = mapper.readValue(inputStream, new TypeReference<>() {});
        Map<Long, Move> savedMovesMap = new HashMap<>();

        moveDtos.forEach(dto -> {
            Move move = new Move();
            move.setName(dto.getName());
            move.setPower(dto.getPower() != null ? dto.getPower() : 0);
            move.setAccuracy(dto.getAccuracy() != null ? dto.getAccuracy() : 101);

            try {
                move.setType(Type.valueOf(dto.getType().toUpperCase()));
                if (dto.getCategory() != null && !dto.getCategory().isEmpty()) {
                    move.setCategory(Category.valueOf(dto.getCategory().toUpperCase()));
                } else {
                    move.setCategory(Category.PHYSICAL);
                }

                if (dto.getStatus1() != null) {
                    move.setStatus1(Status.valueOf(dto.getStatus1().toUpperCase()));
                }
                if (dto.getStatus2() != null) {
                    move.setStatus2(Status.valueOf(dto.getStatus2().toUpperCase()));
                }

                moveRepository.save(move);
                savedMovesMap.put((long) dto.getId(), move);
            } catch (IllegalArgumentException e) {
                System.err.println("ERRO: Dados inválidos para o golpe '" + dto.getName() + "'. Pulando. Detalhe: " + e.getMessage());
            }
        });
        System.out.println(savedMovesMap.size() + " golpes foram salvos.");
        return savedMovesMap;
    }

    private Map<Long, Pokemon> loadPokemons() throws Exception {
        InputStream inputStream = resourceLoader.getResource("classpath:data/pokemon.json").getInputStream();
        List<PokemonJsonDto> pokemonDtos = mapper.readValue(inputStream, new TypeReference<>() {});
        Map<Long, Pokemon> savedPokemonsMap = new HashMap<>();

        pokemonDtos.forEach(dto -> {
            if (dto.getBase() == null) {
                System.err.println("AVISO: Pokémon '" + dto.getName() + "' não tem stats base. Pulando.");
                return;
            }
            Pokemon pokemon = new Pokemon();
            pokemon.setName(dto.getName());

            PokemonJsonDto.BaseStats stats = dto.getBase();
            pokemon.setHpmax(stats.getHp());
            pokemon.setAttack(stats.getAttack());
            pokemon.setDefense(stats.getDefense());
            pokemon.setSpattack(stats.getSpAttack());
            pokemon.setSpdefense(stats.getSpDefense());
            pokemon.setSpeed(stats.getSpeed());
            pokemon.setLevel(1);
            pokemon.setExperience(0);

            try {
                if (dto.getType() != null && !dto.getType().isEmpty()) {
                    pokemon.setType(Type.valueOf(dto.getType().get(0).toUpperCase()));
                } else {
                    System.err.println("AVISO: Pokémon '" + dto.getName() + "' não tem tipo definido. Pulando.");
                    return;
                }
                pokemonRepository.save(pokemon);
                savedPokemonsMap.put((long) dto.getId(), pokemon);
            } catch (IllegalArgumentException e) {
                System.err.println("ERRO: Tipo inválido para o Pokémon '" + dto.getName() + "'. Pulando.");
            }
        });
        System.out.println(savedPokemonsMap.size() + " Pokémon foram salvos.");
        return savedPokemonsMap;
    }

    private void loadPokemonMoveRelations(Map<Long, Pokemon> pokemons, Map<Long, Move> moves) throws Exception {
        InputStream inputStream = resourceLoader.getResource("classpath:data/pokemon_movesets.json").getInputStream();
        List<PokemonMovesetDto> relations = mapper.readValue(inputStream, new TypeReference<>() {});

        System.out.println("Associando golpes aos Pokémon...");
        relations.forEach(relation -> {
            Pokemon pokemon = pokemons.get(relation.getPokemonId());
            if (pokemon == null) {
                System.err.println("AVISO: Pokémon '" + relation.getPokemonName() + "' da relação não encontrado. Pulando.");
                return;
            }

            Set<Move> moveSet = new HashSet<>();
            if (relation.getMoves() != null) {
                relation.getMoves().forEach(moveId -> {
                    Move move = moves.get(moveId);
                    if (move != null) {
                        moveSet.add(move);
                    }
                });
            }
            pokemon.setMoves(moveSet);
        });
        System.out.println("Associação de golpes finalizada.");
    }
}