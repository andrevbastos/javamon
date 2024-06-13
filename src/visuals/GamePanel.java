package visuals;

import javax.swing.*;

import visuals.screens.Combat;
import visuals.screens.Start;

import java.awt.*;
import java.io.*;

public class GamePanel extends JPanel {
    private static Font pkmn, hollow, solid;

    private int prefWidth = 960;
    private int prefHeight = 640;

    private Start startPanel;
    private Combat combatPanel;    
    
    private String p1Name;
    private int p1Hp;
    private String p2Name;
    private int p2Hp;
    private String textBox = "";

    public enum SimState {
        START_SCREEN,
        COMBAT_SCREEN
    }

    private SimState currentState;

    public SimState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SimState currentState) {
        this.currentState = currentState;
    }

    public GamePanel() {

        createFonts();

        this.setPreferredSize(new Dimension(prefWidth, prefHeight));
        this.setDoubleBuffered(true);

    }

    public void createFonts() {
        try {
            pkmn = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PKMN.ttf"));
            hollow = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PokemonHollow.ttf"));
            solid = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PokemonSolid.ttf"));

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void startGameThread() {

        // this.startPanel = new Start(this);
        // currentState = SimState.START_SCREEN;
        // startPanel.runStartSequence();

        currentState = SimState.COMBAT_SCREEN;
        if (currentState == SimState.COMBAT_SCREEN) {
            this.combatPanel = new Combat(this);
            combatPanel.runStartSequence();
        }

    }  

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(solid.deriveFont(Font.PLAIN, 60));
        int textWidth = g2d.getFontMetrics().stringWidth("Javamon");
        g2d.setColor(Color.BLACK);
        g2d.drawString("Javamon", (prefWidth/2) - (textWidth/2), 80);

        g2d.setFont(pkmn.deriveFont(Font.PLAIN, 15));
        if (currentState == SimState.COMBAT_SCREEN) {
            g2d.drawString(p1Name + ": ", 50, 140);
            g2d.drawString("" + p1Hp, 50, 160);
        
            g2d.drawString(p2Name + ": ", 50, 210);
            g2d.drawString("" + p2Hp, 50, 230);

            g2d.drawString(textBox, 50, 270);
        }
    }

    public void updatePokemon1Info(String name, int hp) {
        this.p1Name = name;
        this.p1Hp = hp;
        repaint();
    }

    public void updatePokemon2Info(String name, int hp) {
        this.p2Name = name;
        this.p2Hp = hp;
        repaint();
    }

    public void updateTextBox(String txt) {
        this.textBox = txt;
        repaint();
    }
    
    public int getPrefWidth() {
        return prefWidth;
    }

    public int getPrefHeight() {
        return prefHeight;
    }

}