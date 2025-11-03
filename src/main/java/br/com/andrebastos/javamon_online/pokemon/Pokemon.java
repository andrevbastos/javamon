package br.com.andrebastos.javamon_online.pokemon;

import br.com.andrebastos.javamon_online.ability.Ability;
import br.com.andrebastos.javamon_online.move.Move;
import br.com.andrebastos.javamon_online.shared.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int hpmax;
    private double attack;
    private double defense;
    private double spattack;
    private double spdefense;
    private double speed;

    @ManyToMany
    @JoinTable(
            name = "pokemon_moves",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private Set<Move> moves;

    @ManyToMany
    @JoinTable(
            name = "pokemon_abilities_map",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<Ability> abilities;

    public Pokemon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getHpmax() {
        return hpmax;
    }

    public void setHpmax(int hpmax) {
        this.hpmax = hpmax;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getSpattack() {
        return spattack;
    }

    public void setSpattack(double spattack) {
        this.spattack = spattack;
    }

    public double getSpdefense() {
        return spdefense;
    }

    public void setSpdefense(double spdefense) {
        this.spdefense = spdefense;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Set<Move> getMoves() {
        return moves;
    }

    public void setMoves(Set<Move> moves) {
        this.moves = moves;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }
}