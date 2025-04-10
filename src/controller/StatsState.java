package controller;

import java.awt.Graphics2D;
import view.Panel;

public class StatsState implements SimulationState {
    

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        
    }

    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("R")) {
                sim.reset();
                sim.setState(sim.getSelectionState());
            }
        } catch (NumberFormatException e) {
            System.out.println("Input inválido!");
        }
    }

    @Override
    public void run(Simulation sim) {
        sim.createLog();
    }
    
}
