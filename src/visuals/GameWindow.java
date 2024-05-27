package visuals;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        this.setTitle("2024 © IFC | Javamon");
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();
    }

    public void initialize() {
        ImageIcon icon = null;

        try {
            icon = new ImageIcon("res/pokemon/charizard_front.png");
            this.setIconImage(icon.getImage());

            GamePanel panel = new GamePanel();
            this.add(panel);
            this.pack();
            this.requestFocus();
            panel.startGameThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
