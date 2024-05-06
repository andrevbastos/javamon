package app.visuals;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import app.inputs.*;

public class GamePanel extends JPanel {
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;

    public GamePanel() {
        keyboardInput = new KeyboardInput();
        mouseInput = new MouseInput();
        
        addKeyListener(keyboardInput);
        addMouseListener(mouseInput);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        
    }
}
