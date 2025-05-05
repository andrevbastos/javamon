package view;
import javax.swing.*;

/**
 * @class Window
 * @brief  Window class that creates a JFrame for the application.
 * It sets the title, icon, and adds a Panel to the frame.
 * The window is not resizable and closes the application on exit.
 * The icon is loaded from a specified path.
 * The Panel is added to the frame and requested to focus.
 * 
 * @see Panel
 */
public class Window extends JFrame {
    public Window() {
        this.setTitle("2025 © IFC | Javamon");
        
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = null;

        try {
            icon = new ImageIcon("res/pokemon/eevee.png");
            this.setIconImage(icon.getImage());

            Panel panel = new Panel(this, 600, 500);
            this.add(panel);
            this.pack();
            panel.requestFocusInWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
