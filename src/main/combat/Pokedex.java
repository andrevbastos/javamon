package main.combat;

public class Pokedex {
    // Movesets únicos pra cada pokemon
    Moves charmanderMoves[] = {
        new Moves("GROWL", "NORMAL", 0, "STATUS1", "Attack", null),
        new Moves("SCRATCH", "NORMAL", 40, "PHYSICAL"),
        new Moves("EMBER", "FIRE", 40, "SPECIAL"),
        new Moves("SMOKESCREEN", "NORMAL", 0, "STATUS1", "Accuracy", null)
    };

    Moves bulbasaurMoves[] = {
        new Moves("GROWL", "NORMAL", 0, "STATUS1", "Attack", null),
        new Moves("TACKLE", "NORMAL", 40, "PHYSICAL"),
        new Moves("VINE WHIP", "GRASS", 45, "PHYSICAL"),
        new Moves("GROWTH", "NORMAL", 0, "STATUS2", "Attack", "SpAttack")
    };

    Moves squirtleMoves[] = {
        new Moves("TAIL WHIP", "NORMAL", 0, "STATUS1", "Defense", null),
        new Moves("TACKLE", "NORMAL", 40, "PHYSICAL"),
        new Moves("WATER GUN", "WATER", 40, "SPECIAL"),
        new Moves("WITHDRAW", "WATER", 0, "STATUS2", "Defense", null)
    };

    String charmanderSprite = "";
    String squirtleSprite = "";
    String bulbasaurSprite = "";

    // Da uma instância NOVA de pokemon para um objeto trainer
    public void givePokemon(Trainer trainer, int i) {
        switch (i) {
            case 0:
                trainer.setPokemon(new Pokemon("CHARMANDER", "FIRE", 39, 52, 60, 43, 50, 65, 100, charmanderMoves, charmanderSprite));
                System.out.println("\n" + trainer.getName() + " chose a " + trainer.getPokemon());  
                break;

            case 1:
                trainer.setPokemon(new Pokemon("BULBASAUR", "GRASS", 45, 49, 65, 49, 65, 45, 100, bulbasaurMoves, squirtleSprite)); 
                System.out.println("\n" + trainer.getName() + " chose a " + trainer.getPokemon());   
                break;

            case 2:
                trainer.setPokemon(new Pokemon("SQUIRTLE", "WATER", 44, 48, 50, 65, 64, 43, 100, squirtleMoves, bulbasaurSprite)); 
                System.out.println("\n" + trainer.getName() + " chose a " + trainer.getPokemon());      
                break;
                
            default:
                break;
        } 
    }
}
