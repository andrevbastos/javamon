package simulation;

import ui.Panel;
import java.awt.Graphics2D;

public interface SimulationState {
    void update(Simulation sim, Panel panel, Graphics2D g2d);
    void handleInput(Simulation sim, String input);
}