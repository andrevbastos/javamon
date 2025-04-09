package ui;

import simulation.CombatSim;

import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontFormatException;

public class Panel extends JPanel {
    private CombatSim sim = new CombatSim();
    public int width, height;
    public Font pkmn, solid;

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
        
        createFonts();
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