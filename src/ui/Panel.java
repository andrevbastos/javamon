package ui;

import simulation.Simulation;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontFormatException;

public class Panel extends JPanel {
    private Simulation sim = new Simulation();
    public int width, height;
    public Font pkmn, solid;

    public Panel(int width, int height) {
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
            case KeyEvent.VK_ENTER:
                input = "ENTER";
                break;
            case KeyEvent.VK_1:
                input = "1";
                break;
            case KeyEvent.VK_2:
                input = "2";
                break;
            case KeyEvent.VK_3:
                input = "3";
                break;
            case KeyEvent.VK_4:
                input = "4";
                break;
            case KeyEvent.VK_R:
                input = "R";
                break;
            // Adicione mais teclas conforme necessário
        }
        
        if (!input.isEmpty()) {
            sim.handleInput(input);
            repaint(); // Redesenha o painel após processar a entrada
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