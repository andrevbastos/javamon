package view;

import controller.Simulation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

/**
 * @class Panel
 * @brief Panel class that extends JPanel to create a custom drawing area for the simulation.
 * It handles key events and id updated by the simulation state controller.
 * 
 * @see Simulation
 */
public class Panel extends JPanel {
    private final Simulation sim = new Simulation(this);
    private Window window;
    public int width, height;
    public Font pkmn, solid;

    public Panel(Window window, int width, int height) {
        this.window = window;
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
        
        createFonts();
        setupKeyListener();
    }

    private void setupKeyListener() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyInput(e.getKeyCode());
            }
        });
    }

    private void handleKeyInput(int keyCode) {
        String input = "";
        
        switch(keyCode) {
            case KeyEvent.VK_ESCAPE -> input = "ESCAPE";
            case KeyEvent.VK_ENTER -> input = "ENTER";
            case KeyEvent.VK_1 -> input = "1";
            case KeyEvent.VK_2 -> input = "2";
            case KeyEvent.VK_3 -> input = "3";
            case KeyEvent.VK_4 -> input = "4";
            case KeyEvent.VK_5 -> input = "5";
            case KeyEvent.VK_6 -> input = "6";
            case KeyEvent.VK_7 -> input = "7";
            case KeyEvent.VK_8 -> input = "8";
            case KeyEvent.VK_9 -> input = "9";
            case KeyEvent.VK_E -> input = "E";
            case KeyEvent.VK_Q -> input = "Q";
            case KeyEvent.VK_R -> input = "R";
        }
        
        if (input.equals("ESCAPE")) {
            window.dispose();
            System.exit(0);
        }

        if (!input.isEmpty()) {
            sim.handleInput(input);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        sim.update(this, g2d);
    }

    public void createFonts() {
        try {
            pkmn = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PKMN.ttf"));
            solid = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PokemonSolid.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}