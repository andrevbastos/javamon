package br.com.andrebastos.javamon_online.common;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonJsonDto {
    private int id;
    private String name;
    private List<String> type;
    @JsonProperty("base")
    private BaseStats base;

    public static class BaseStats {
        @JsonProperty("HP")
        private int hp;
        @JsonProperty("ATK")
        private int attack;
        @JsonProperty("DEF")
        private int defense;
        @JsonProperty("SPATK")
        private int spAttack;
        @JsonProperty("SPDEF")
        private int spDefense;
        @JsonProperty("SPD")
        private int speed;

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getDefense() {
            return defense;
        }

        public void setDefense(int defense) {
            this.defense = defense;
        }

        public int getSpAttack() {
            return spAttack;
        }

        public void setSpAttack(int spAttack) {
            this.spAttack = spAttack;
        }

        public int getSpDefense() {
            return spDefense;
        }

        public void setSpDefense(int spDefense) {
            this.spDefense = spDefense;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public BaseStats getBase() {
        return base;
    }

    public void setBase(BaseStats base) {
        this.base = base;
    }
}