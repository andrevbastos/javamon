package visuals.screens;

import combat.*;
import visuals.GamePanel;

public class Combat {
    
    private GamePanel gp;
    private Pokemon[] selectedPokemons;
    private Battle battle;
    private int repetitions = 10;
    private String winners = "";
    private String moveHistory = "";

    public Combat(GamePanel gp) {

        this.gp = gp;

        PokemonFactory pf = new PokemonFactory();
        this.selectedPokemons = new Pokemon[] { pf.getPokemon("CHARMANDER"), pf.getPokemon("SQUIRTLE"), pf.getPokemon("BULBASAUR"), pf.getPokemon("PIKACHU")};

        this.battle = new Battle(this);

    }

    public void runStartSequence() {

        for (int i = 0; i < selectedPokemons.length; i++) {
            for (int j = i + 1; j < selectedPokemons.length; j++) {
                if (i != j) {
                    battle.setP1(selectedPokemons[i]);
                    battle.setP2(selectedPokemons[j]);
                    for (int k = repetitions; k >= 0; k--) {
                        winners += battle.run() + "; ";
                    }
                    winners += "\n\n";
                }
            }
        }

        System.out.println(winners);
        System.out.println(moveHistory);

    }

    public void addToHistory(String txt) {
        this.moveHistory += txt;
    }

}
