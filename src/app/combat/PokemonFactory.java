package app.combat;

public class PokemonFactory {
    // Movesets e sprites únicos pra cada pokemon
    static String charmanderSprite = "";
    static Moves charmanderMoves[] = {
        new Moves("GROWL", "NORMAL", 0, "STATUS1", "Attack", null),
        new Moves("SCRATCH", "NORMAL", 40, "PHYSICAL"),
        new Moves("EMBER", "FIRE", 40, "SPECIAL"),
        new Moves("SMOKESCREEN", "NORMAL", 0, "STATUS1", "Accuracy", null)
    };

    static String bulbasaurSprite = "";
    static Moves bulbasaurMoves[] = {
        new Moves("GROWL", "NORMAL", 0, "STATUS1", "Attack", null),
        new Moves("TACKLE", "NORMAL", 40, "PHYSICAL"),
        new Moves("VINE WHIP", "GRASS", 45, "PHYSICAL"),
        new Moves("GROWTH", "NORMAL", 0, "STATUS2", "Attack", "SpAttack")
    };

    static String squirtleSprite = "";
    static Moves squirtleMoves[] = {
        new Moves("TAIL WHIP", "NORMAL", 0, "STATUS1", "Defense", null),
        new Moves("TACKLE", "NORMAL", 40, "PHYSICAL"),
        new Moves("WATER GUN", "WATER", 40, "SPECIAL"),
        new Moves("WITHDRAW", "WATER", 0, "STATUS2", "Defense", null)
    };

    public static Pokemon getPokemon(String p){

        if(p == null){
           return null;
        }		

        if (p.equalsIgnoreCase("CHARMANDER")) {
           return new Pokemon("CHARMANDER", "FIRE", 39, 52, 60, 43, 50, 65, 100, charmanderMoves, charmanderSprite);

        } else if (p.equalsIgnoreCase("BULBASAUR")) {
           return new Pokemon("BULBASAUR", "GRASS", 45, 49, 65, 49, 65, 45, 100, bulbasaurMoves, bulbasaurSprite);
           
        } else if (p.equalsIgnoreCase("SQUIRTLE")) {
           return new Pokemon("SQUIRTLE", "WATER", 44, 48, 50, 65, 64, 43, 100, squirtleMoves, squirtleSprite);

        }
        return null;

    }
}
