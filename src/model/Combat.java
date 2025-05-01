/**
 * @class Combat
 * @brief Battle simulation engine for Pokémon matches
 * 
 * @section overview Overview
 * The Combat class serves as the core battle simulator, managing turn-based
 * Pokémon battles with complete ability event handling and result logging.
 * Implements a tournament-style battle system when multiple Pokémon are selected.
 * 
 * @section mechanics Battle Mechanics
 * - Turn order determined by speed stats
 * - Ability event triggers at key battle phases
 * - Full damage calculation and status effect application
 * - Automatic healing after each match
 * 
 * @section logging Battle Logging
 * Generates detailed JSON-style logs containing:
 * - Move sequence
 * - Winner information
 * - Complete turn-by-turn breakdown
 * 
 * @design
 * @li Uses Observer pattern for ability event handling
 * @li Implements round-robin tournament for multiple Pokémon
 * 
 * @see model.pokemon.Pokemon
 * @see model.abilities.AbilityObserver
 */
package model;

import java.util.ArrayList;
import model.abilities.AbilityEvent;
import model.abilities.AbilityObserver;
import model.pokemon.Pokemon;

public class Combat {
    /** @brief Observer for ability trigger events */
    private AbilityObserver observer = new AbilityObserver();
    
    /** @brief List of Pokémon participating in battles */
    private ArrayList<Pokemon> selectedPokemons = new ArrayList<>();

    /**
     * @brief Initializes combat with selected Pokémon
     * @param selectedPokemons ArrayList of Pokémon to battle
     */
    public Combat(ArrayList<Pokemon> selectedPokemons) {
        this.selectedPokemons = selectedPokemons;
    }

    /**
     * @brief Executes tournament battles
     * @param repetitions Number of matches per Pokémon pair
     * @return String JSON-formatted battle log
     * 
     * @details
     * Runs round-robin tournament where each Pokémon battles every other:
     * - Matches are repeated per repetition parameter
     * - Returns aggregated battle logs
     */
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

    /**
     * @brief Simulates a single Pokémon battle
     * @param p1 First combatant
     * @param p2 Second combatant
     * @return String JSON-formatted battle result
     * 
     * @details
     * Handles complete battle lifecycle:
     * 1. Determines turn order by speed
     * 2. Processes moves until winner emerges
     * 3. Tracks all moves and outcomes
     * 4. Automatically heals Pokémon post-battle
     */
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