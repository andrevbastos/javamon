package main.visuals;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {

    GamePanel() {

        this.setPreferredSize(new Dimension(960, 640));
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(0, 0, 960, 640);

        
    }
}
