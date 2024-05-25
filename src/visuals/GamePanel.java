package visuals;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class GamePanel extends JPanel {
    Font pkmn, hollow, solid;

    public GamePanel() {

        createFonts();

        ImageIcon image = new ImageIcon("res/charizard_front.png");
        JLabel label = new JLabel();
        label.setText("fodase");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(new Color(0xff5000));
        label.setFont(solid.deriveFont(Font.PLAIN, 60));

        this.add(label);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        
    }

    public void createFonts() {
        try {
            pkmn = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PKMN.ttf"));
            hollow = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PokemonHollow.ttf"));
            solid = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PokemonSolid.ttf"));

        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
