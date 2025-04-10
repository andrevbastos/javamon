package controller;

import java.awt.Graphics2D;
import view.Panel;

public interface SimulationState {
    void update(Simulation sim, Panel panel, Graphics2D g2d);
    void handleInput(Simulation sim, String input);
    void run(Simulation sim);
}