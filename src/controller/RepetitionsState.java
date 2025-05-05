package controller;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

import view.Panel;

public class RepetitionsState implements SimulationState {
    /** @brief Number of repetitions for the simulation */
    private int repetitions = 1;

    /**
     * @brief Constructs a RepetitionsState with default repetitions
     * 
     * Initializes the number of repetitions to 1.
     */
    public RepetitionsState() {
        this.repetitions = 1;
    }

    /**
     * @brief Sets the number of repetitions for the simulation
     * 
     * @param repetitions The number of repetitions to set
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * @brief Gets the current number of repetitions
     * 
     * @return The current number of repetitions
     */
    public int getRepetitions() {
        return this.repetitions;
    }

    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));
        g2d.drawString("Select the number of repetitions", 50, 50);
        g2d.drawString("Current: " + repetitions, 50, 80);

        g2d.drawString("1. 1", panel.width / 5, 110);
        g2d.drawString("2. 10", panel.width / 5, 150);
        g2d.drawString("3. 100", panel.width / 5, 190);
        g2d.drawString("4. 1000", panel.width / 5, 230);

        g2d.setFont(panel.pkmn.deriveFont(10f));
        g2d.drawString("Press ENTER to start", 10, panel.height - 10);
        g2d.drawString("Press BACKSPACE to go back", 10, panel.height - 30);
    }

    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("ENTER")) {
                sim.setRepetitions(repetitions);
                sim.setState(sim.getCombatState());
                System.out.println("ENTER pressed");
                return;
            }
            if (input.equals("BACKSPACE")) {
                sim.reset();
                sim.setState(new SelectionState());
                System.out.println("BACKSPACE pressed");
                return;
            }

            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1 -> { this.repetitions = 1; }
                case 2 -> { this.repetitions = 10; }
                case 3 -> { this.repetitions = 100; }
                case 4 -> { this.repetitions = 1000; }
            }
        } catch (NumberFormatException e) {
            sim.addToLog("Invalid input. Please select a valid option.");
        }
    }

    @Override
    public void reset() {
        this.repetitions = 1;
    }
    
}
