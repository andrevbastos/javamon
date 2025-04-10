import view.Window;

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