package controller;

import java.awt.Graphics2D;
import java.util.ArrayList;
import view.Panel;

/**
 * @class CompositeState
 * @brief Manages multiple simulation states using Composite pattern.
 * This class implements SimulationState and serves as a container
 * for managing all simulation states collectively.
 * 
 * @details
 * - Maintains a collection of SimulationState objects
 * - Provides centralized reset functionality
 * - Allows easy addition of new states
 * 
 * @note While implementing SimulationState, most methods are empty
 * as this class primarily manages child states
 * 
 * @see controller.Simulation
 * @see controller.SimulationState
 */
public class CompositeState implements SimulationState {
    /** @brief Collection of managed simulation states */
    private ArrayList<SimulationState> states = new ArrayList<>();
    
    /**
     * @brief Adds a new state to the composite
     * @param state The state to add
     */
    public void addState(SimulationState state) {
        states.add(state);
    }
    
    /**
     * @brief Empty implementation - child states handle their own updates
     */
    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {}
    
    /**
     * @brief Empty implementation - child states handle their own input
     */
    @Override
    public void handleInput(Simulation sim, String input) {}

    /**
     * @brief Resets all managed states
     */
    @Override
    public void reset() {
        for (SimulationState state : states) {
            state.reset();
        }
    }
}