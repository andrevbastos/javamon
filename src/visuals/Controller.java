package visuals;

import javax.swing.JPanel;
import combat.*;

public class Controller extends JPanel {

    GamePanel gp;

    public Controller(Trainer t1, Trainer t2, GamePanel gp) {
        this.gp = gp;
        Battle b = new Battle(t1, t2);
        boolean stop = true;

        while (stop) {
            b.battle();

        }

    }
    
}
