package visuals;

import javax.swing.*;
import combat.*;

public class Start extends JPanel {

    private GamePanel screen;
    private Trainer player;
    private Trainer rival;
    private ChoosePokemon choosePokemon;

    public Start(GamePanel screen) {
        
        this.screen = screen;
        this.player = new Trainer(null);
        this.rival = new Trainer(null);
        this.choosePokemon = new ChoosePokemon();
        add(choosePokemon);
    }

    public void runStartSequence() {

        namePlayer();
        nameRival();
        chooseCharacter("Pick your pokémon:");
        chooseCharacter("Pick your rival's pokémon:");
    }

    public void namePlayer() {
        
        String playerName = JOptionPane.showInputDialog(screen, "Enter your name:");
        player.setName(playerName);
    }

    public void nameRival() {

        String rivalName = JOptionPane.showInputDialog(screen, "Enter your rival's name:");
        rival.setName(rivalName);
    }

    public void chooseCharacter(String strg) {

        JOptionPane.showMessageDialog(screen, choosePokemon, strg, JOptionPane.PLAIN_MESSAGE);

        String selectedPokemon = choosePokemon.getSelectedSkin();

        if (strg == "Pick your pokémon:")
            player.setPokemon(PokemonFactory.getPokemon(selectedPokemon));
        else 
            rival.setPokemon(PokemonFactory.getPokemon(selectedPokemon));
    }

    public Trainer getPlayer() {
        return player;
    }

    public Trainer getRival() {
        return rival;
    }
}