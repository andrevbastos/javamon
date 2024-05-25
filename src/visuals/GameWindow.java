package visuals;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;
    public int width = 960;
    public int height = 640;

    public GameWindow(GamePanel gamePanel) {

        jframe = new JFrame();

        jframe.setSize(width, height);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

    }
}
