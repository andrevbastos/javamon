

import app.combat.Battle;
import app.combat.Pokemon;
import app.combat.PokemonFactory;
import app.combat.Trainer;
import app.visuals.GamePanel;
import app.visuals.GameWindow;

public class Controller {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public Controller() {

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        Trainer player = new Trainer("a");
        givePokemon(player, PokemonFactory.getPokemon("CHARMANDER"));

        Trainer inimigo = new Trainer("b");
        givePokemon(inimigo, PokemonFactory.getPokemon("SQUIRTLE"));

        Battle b = new Battle(player, inimigo);
        
        int i = 5;
        while (i != 0) {
            b.battle();
            i--;
        }
    }

    public static void givePokemon(Trainer t, Pokemon p) {
        t.setPokemon(p);
    }
    
}
