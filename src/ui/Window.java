package ui;
import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        this.setTitle("2024 © IFC | Javamon");
        
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = null;

        try {
            icon = new ImageIcon("res/pokemon/charmander.png");
            this.setIconImage(icon.getImage());

            Panel panel = new Panel(400, 400);
            this.add(panel);
            this.pack();
            this.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
