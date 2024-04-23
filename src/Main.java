import app.combat.*;
import app.visuals.*;

public class Main {
    public static void main(String[] args) throws Exception {

        //Inicia o JFRAME
        MyFrame frame = new MyFrame();
        
        //Inicia a simulação
        Controller.startSimulation();

    }
}