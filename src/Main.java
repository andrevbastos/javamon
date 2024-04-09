import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        // Iniciar player
        System.out.println("\nWhat`s your name?");
        String Name = teclado.nextLine();
        Trainer player = new Trainer(Name,"");

        // Iniciar inimigo
        System.out.println("\nWhat`s your rival`s name?");
        Name = teclado.nextLine();
        Trainer inimigo = new Trainer(Name, "");

        // Iniciar pokedex
        Pokedex pokedex = new Pokedex();        
        System.out.println("\nChoose your pokemon...\n1. CHARMANDER2. BULBASAUR3. SQUIRTLE");
        int i = teclado.nextInt() - 1;
        pokedex.givePokemon(player, i);
        System.out.println("\nChoose your pokemon...\n1. CHARMANDER2. BULBASAUR3. SQUIRTLE");
        i = teclado.nextInt() - 1;
        pokedex.givePokemon(inimigo, i);
        
        // Iniciar Battle
        Battle game = new Battle(player, inimigo);
        game.battle();
        
        teclado.close();
    }
}