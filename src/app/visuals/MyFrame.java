package app.visuals;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {

        GamePanel panel = new GamePanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
