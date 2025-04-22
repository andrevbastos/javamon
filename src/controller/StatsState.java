package controller;

import java.awt.Graphics2D;
import view.Panel;

public class StatsState implements SimulationState {
    @Override
    public void update(Simulation sim, Panel panel, Graphics2D g2d) {
        g2d.setFont(panel.pkmn.deriveFont(20f));

        g2d.drawString("E to create logs", 10, panel.height - 70);
        g2d.drawString("Q to battle again", 10, panel.height - 40);
        g2d.drawString("R to restart", 10, panel.height - 10);
    }

    @Override
    public void handleInput(Simulation sim, String input) {
        try {
            if (input.equals("E")) {
                sim.createLog();
            }
            if (input.equals("Q")) {
                sim.setState(sim.getCombatState());
            }
            if (input.equals("R")) {
                sim.reset();
                sim.setState(sim.getSelectionState());
            }
        } catch (NumberFormatException e) {
            System.out.println("Input inválido!");
        }
    }

}
