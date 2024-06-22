package visuals;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import visuals.screens.*;
import combat.*;

public class GamePanel extends JPanel {
    private Font pkmn, solid;

    private int prefWidth = 960;
    private int prefHeight = 640;

    private Start startPanel;
    private Combat combatPanel;

    private Pokemon[] selectedPokemons;

    public enum SimState {
        START_SCREEN,
        COMBAT_SCREEN
    }
    private SimState currentState;

    public GamePanel() {
        createFonts();
        this.setPreferredSize(new Dimension(prefWidth, prefHeight));
        this.setDoubleBuffered(true);
    }

    public void createFonts() {
        try {
            pkmn = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PKMN.ttf"));
            solid = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PokemonSolid.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void startSimThread() {
        // this.startPanel = new Start(this);
        // currentState = SimState.START_SCREEN;
        // startPanel.runStartSequence();

        currentState = SimState.COMBAT_SCREEN;
        if (currentState == SimState.COMBAT_SCREEN) {
            this.combatPanel = new Combat(this);
            combatPanel.runCombatSequence();
        }
    }  

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        ImageIcon icon = new ImageIcon("res/gui/bg.png");
        g2d.drawImage(icon.getImage(), 0, 0, null);

        g2d.setFont(solid.deriveFont(Font.PLAIN, 60));
        int textWidth = g2d.getFontMetrics().stringWidth("Javamon");
        g2d.setColor(Color.BLACK);
        g2d.drawString("Javamon", (prefWidth/2) - (textWidth/2), 120);

        if (selectedPokemons != null) {
            g2d.setFont(pkmn.deriveFont(Font.PLAIN, 15));
            int pokemonHeight = 220;
            for (Pokemon p : selectedPokemons) {
                int victories = p.getVictories();
                int rounds = (int) p.getRounds() / p.getVictories();
                g2d.drawString(p.getName() + ": won " + victories + " in an avegerage of " + rounds + " rounds", 200, pokemonHeight);
                pokemonHeight += 40;
            }
            orderPokemon();
            pokemonHeight += 40;
            for (int i = 0; i < selectedPokemons.length; i++) {
                g2d.drawString("" + (i + 1) + ". " +selectedPokemons[i].getName(), 200, pokemonHeight);
                pokemonHeight += 40;
            }
        }
    }

    public void setStartStats(ArrayList<String> sp) {
        PokemonFactory pf = new PokemonFactory();
        this.selectedPokemons = new Pokemon[sp.size()];
        for (int i = 0; i < sp.size(); i++) {
            selectedPokemons[i] = pf.getPokemon(sp.get(i));
        }
    }

    public void setCombatStats(Pokemon[] sp) {
        this.selectedPokemons = sp;
        repaint();
    }

    public void orderPokemon() {
        int i, j;
        Pokemon temp;

        for (i = selectedPokemons.length - 1; i > 0; i--) {
            for (j = 0; j < i; j++) {
                if (selectedPokemons[j].getVictories() < selectedPokemons[j + 1].getVictories()) {
                    temp = selectedPokemons[j];
                    selectedPokemons[j] = selectedPokemons[j + 1];
                    selectedPokemons[j + 1] = temp;
                } else if (selectedPokemons[j].getVictories() == selectedPokemons[j + 1].getVictories()) {
                    if (selectedPokemons[j].getRounds() > selectedPokemons[j + 1].getRounds()) {
                        temp = selectedPokemons[j];
                        selectedPokemons[j] = selectedPokemons[j + 1];
                        selectedPokemons[j + 1] = temp;
                    }
                }
            }
        }
    }

    public int getPrefWidth() {
        return prefWidth;
    }

    public int getPrefHeight() {
        return prefHeight;
    }

}