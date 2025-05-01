package model;

import java.util.ArrayList;

import model.abilities.AbilityEvent;
import model.abilities.AbilityObserver;
import model.pokemon.Pokemon;

/**
 * The Combat class is responsible for simulating battles between Pokémon.
 * It manages the selected Pokemon and instantiates them using the
 * PokemonFactory class.
 * The class handles the battle logic, including determining the order of moves,
 * logging the alterations and determining the winner.
 * It uses the AbilityObserver to handle events during the battle.
 * 
 * @see model.pokemon.Pokemon
 * @see model.pokemon.PokemonFactory
 * 
 * @see model.abilities.AbilityObserver
 */
public class Combat {
    private AbilityObserver observer = new AbilityObserver();
    private ArrayList<Pokemon> selectedPokemons = new ArrayList<>();

    public Combat(ArrayList<Pokemon> selectedPokemons) {
        this.selectedPokemons = selectedPokemons;
    }

    public String run(int repetitions) {
        String log = "";

        for (int i = 0; i < selectedPokemons.size() - 1; i++) {
            for (int j = i + 1; j < selectedPokemons.size(); j++) {
                for (int rep = 0; rep < repetitions; rep++) {
                    log += singleBattle(selectedPokemons.get(i), selectedPokemons.get(j));
                }
            }
        }

        selectedPokemons.clear();
        return log;
    }

    private String singleBattle(Pokemon p1, Pokemon p2) {
        observer.setPokemons(p1, p2);
        Pokemon winner = null;
        Pokemon first, last;
        String moves = "";

        observer.handleEvent(AbilityEvent.BATTLE_START);

        while (winner == null) {
            observer.handleEvent(AbilityEvent.TURN_START);

            first = (p1.getSpeed() >= p2.getSpeed()) ? p1 : p2;
            last = (first == p1) ? p2 : p1;

            moves += "{" + first.getName() + ":" + first.useMove(observer, last);
            if (last.getHp() > 0) {
                moves += "," + last.getName() + ":" + last.useMove(observer, first);
            }
            moves += "}";

            if (p1.getHp() == 0) {
                winner = p2;
                p2.addWin();
            }
            else if (p2.getHp() == 0) {
                winner = p1;
                p1.addWin();
            }
            else {
                observer.handleEvent(AbilityEvent.TURN_END);
                winner = null;
                moves += ",";
            }
        }

        p1.heal();
        p2.heal();

        return "{winner:{" + winner.getName() + "},moves:{" + moves + "}}";
    }

}
