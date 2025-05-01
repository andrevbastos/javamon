package controller;

import java.awt.Graphics2D;
import view.Panel;

/**
 * @interface SimulationState
 * @brief Defines the interface for all simulation states.
 * This interface represents the State design pattern for managing
 * different simulation states (Selection, Combat, Stats).
 * 
 * @details
 * All concrete states must implement these methods to:
 * - Handle rendering updates
 * - Process user input
 * - Reset their internal state
 * 
 * @see controller.Simulation
 * @see controller.SelectionState
 * @see controller.CombatState
 * @see controller.StatsState
 */
public interface SimulationState {
    /**
     * @brief Updates and renders the state
     * @param sim The Simulation controller
     * @param panel The view panel
     * @param g2d Graphics context
     */
    void update(Simulation sim, Panel panel, Graphics2D g2d);
    
    /**
     * @brief Handles user input
     * @param sim The Simulation controller
     * @param input The user input
     */
    void handleInput(Simulation sim, String input);
    
    /**
     * @brief Resets the state
     */
    void reset();
}