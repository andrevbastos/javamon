package visuals;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GamePanel extends JPanel implements Runnable {
    static Font pkmn, hollow, solid;

    Thread gameThread;
    Start startPanel;
    Controller combatPanel;

    int fps = 60;
    private long last_update_time = System.nanoTime();

    public enum SimState {
        START_SCREEN,
        COMBAT_SCREEN
    }

    private SimState currentState;

    public SimState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SimState currentState) {
        this.currentState = currentState;
    }

    public GamePanel() {

        createFonts();

        this.setPreferredSize(new Dimension(480, 320));
        this.setDoubleBuffered(true);

        JLabel label = new JLabel();
        label.setFont(solid.deriveFont(Font.PLAIN, 64));
        label.setText("Javamon");
        this.add(label);

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

    public void startGameThread() {

        this.startPanel = new Start(this);
    
        currentState = SimState.START_SCREEN;
    
        startPanel.runStartSequence();
        
        this.combatPanel = new Controller(startPanel.getPlayer(), startPanel.getRival(), this);
    
        currentState = SimState.COMBAT_SCREEN;

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double delta = 0;
        double draw_interval = 1_000_000_000 / fps;

        while (gameThread != null) {
            long now = System.nanoTime();
            delta += (now - last_update_time) / draw_interval;
            last_update_time = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();

            try {
                double remaining_time = (last_update_time - now + draw_interval) / 1_000_000_000;
                remaining_time = remaining_time < 0 ? 0 : remaining_time;

                Thread.sleep((long)remaining_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        
    }   
    
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        
    }

}