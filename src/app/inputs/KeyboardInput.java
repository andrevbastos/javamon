package app.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.combat.Battle;

public class KeyboardInput implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            Battle.wait = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
