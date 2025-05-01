package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import view.Panel;

/**
 * CompositeState is a class that is used to manage the different 
 * states of the simulation(Selection, Combat, Stats) and allows for
 * easy addition of new states.
 * It implements the SimulationState interface
 * and allows for the addition of multiple states.
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 */
public class CompositeState implements SimulationState {
    private ArrayList<SimulationState> states = new ArrayList<>();
    
    public void addState(SimulationState state) {
        states.add(state);
    }
    
    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {}
    
    @Override
    public void handleInput(Simulation sim, String input) {}

    @Override
    public void reset() {
        for (SimulationState state : states) {
            state.reset();
        }
    }
}
