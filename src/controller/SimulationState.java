package controller;

import java.awt.Graphics2D;
import view.Panel;

/**
 * SimulationState is an interface that defines the methods
 * that all simulation states must implement. It is used to
 * manage the different states of the simulation (Selection, Combat, Stats).
 * 
 * @see controller.Simulation
 * @see controller.SelectionState
 * @see controller.CombatState
 * @see controller.StatsState
 */
public interface SimulationState {
    void update(Simulation sim, Panel panel, Graphics2D g2d);
    void handleInput(Simulation sim, String input);
    void reset();
}