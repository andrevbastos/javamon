import view.Window;

/**
 * Javamon is a simple simulation that allows users to choose between
 * the classic eeveelutions and simulate battles between them.
 * It is able to output statistics and battle results.
 * Applying Singleton design pattern, it allows only one instance of the simulation
 * to be created and used throughout the application.
 * It initializes the main window and starts the simulation.
 * 
 * @see controller.Simulation
 */
public class Javamon {
    static private Javamon instance;

    public static void init() {
        if (instance == null) {
            instance = new Javamon();
        }
    }

    private Javamon() {
        Window gm = new Window();
    }
}