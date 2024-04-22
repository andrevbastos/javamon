package main.combat;

public class Trainer {
    
    private String name;
    private Pokemon pokemon;
    private String character;

    public Trainer(String name, String character) {
        this.name = name;
        this.character = character;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return this.name;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public String getCharater() {
        return this.character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
