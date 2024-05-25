package combat;

public class Trainer {
    
    private String name;
    private Pokemon pokemon;

    public Trainer(String name) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
