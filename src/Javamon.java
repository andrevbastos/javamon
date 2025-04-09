import ui.Window;

public class Javamon {
    static private Javamon instance;

    public static Javamon init() {
        if (instance == null) {
            instance = new Javamon();
        }
        return instance;
    }

    private Javamon() {
        Window gm = new Window();
    }
}