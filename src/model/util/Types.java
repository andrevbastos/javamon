package model.util;

import java.util.HashMap;
import java.util.Map;

public class Types {

    public enum Type {
        NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, FIGHTING, POISON,
        GROUND, FLYING, PSYCHIC, BUG, ROCK, GHOST, DRAGON, DARK,
        STEEL, FAIRY
    }

    // Tabela hash dos tipos e suas vantagens
    static Map<Type, Map<Type, Double>> typeChart = new HashMap<>();

    static {
        // Normal
        Map<Type, Double> normalMap = new HashMap<>();
            normalMap.put(Type.ROCK, 0.5);
            normalMap.put(Type.STEEL, 0.5);
            normalMap.put(Type.GHOST, 0.0);
        
        // Fire
        Map<Type, Double> fireMap = new HashMap<>();
            fireMap.put(Type.GRASS, 1.5);
            fireMap.put(Type.ICE, 1.5);
            fireMap.put(Type.BUG, 1.5);
            fireMap.put(Type.STEEL, 1.5);
            fireMap.put(Type.WATER, 0.5);
            fireMap.put(Type.FIRE, 0.5);
            fireMap.put(Type.ROCK, 0.5);
            fireMap.put(Type.DRAGON, 0.5);
        
        // Water
        Map<Type, Double> waterMap = new HashMap<>();
            waterMap.put(Type.FIRE, 1.5);
            waterMap.put(Type.GROUND, 1.5);
            waterMap.put(Type.ROCK, 1.5);
            waterMap.put(Type.WATER, 0.5);
            waterMap.put(Type.GRASS, 0.5);
            waterMap.put(Type.DRAGON, 0.5);

        // Grass
        Map<Type, Double> grassMap = new HashMap<>();
            grassMap.put(Type.WATER, 1.5);
            grassMap.put(Type.GROUND, 1.5);
            grassMap.put(Type.ROCK, 1.5);
            grassMap.put(Type.FIRE, 0.5);
            grassMap.put(Type.GRASS, 0.5);
            grassMap.put(Type.POISON, 0.5);
            grassMap.put(Type.FLYING, 0.5);
            grassMap.put(Type.BUG, 0.5);
            grassMap.put(Type.DRAGON, 0.5);
            grassMap.put(Type.STEEL, 0.5);
        
        // Electric
        Map<Type, Double> electricMap = new HashMap<>();
            electricMap.put(Type.WATER, 1.5);
            electricMap.put(Type.FLYING, 1.5);
            electricMap.put(Type.ELECTRIC, 0.5);
            electricMap.put(Type.GRASS, 0.5);
            electricMap.put(Type.DRAGON, 0.5);
            electricMap.put(Type.GROUND, 0.0);
        
        // Ice
        Map<Type, Double> iceMap = new HashMap<>();
            iceMap.put(Type.GRASS, 1.5);
            iceMap.put(Type.GROUND, 1.5);
            iceMap.put(Type.FLYING, 1.5);
            iceMap.put(Type.DRAGON, 1.5);
            iceMap.put(Type.FIRE, 0.5);
            iceMap.put(Type.WATER, 0.5);
            iceMap.put(Type.ICE, 0.5);
            iceMap.put(Type.STEEL, 0.5);
        
        // Fighting
        Map<Type, Double> fightingMap = new HashMap<>();
            fightingMap.put(Type.NORMAL, 1.5);
            fightingMap.put(Type.ICE, 1.5);
            fightingMap.put(Type.ROCK, 1.5);
            fightingMap.put(Type.DARK, 1.5);
            fightingMap.put(Type.STEEL, 1.5);
            fightingMap.put(Type.POISON, 0.5);
            fightingMap.put(Type.FLYING, 0.5);
            fightingMap.put(Type.PSYCHIC, 0.5);
            fightingMap.put(Type.BUG, 0.5);
            fightingMap.put(Type.FAIRY, 0.5);
            fightingMap.put(Type.GHOST, 0.0);
        
        // Poison
        Map<Type, Double> poisonMap = new HashMap<>();
            poisonMap.put(Type.GRASS, 1.5);
            poisonMap.put(Type.FAIRY, 1.5);
            poisonMap.put(Type.POISON, 0.5);
            poisonMap.put(Type.GROUND, 0.5);
            poisonMap.put(Type.ROCK, 0.5);
            poisonMap.put(Type.GHOST, 0.5);
            poisonMap.put(Type.STEEL, 0.0);
        
        // Ground
        Map<Type, Double> groundMap = new HashMap<>();
            groundMap.put(Type.FIRE, 1.5);
            groundMap.put(Type.ELECTRIC, 1.5);
            groundMap.put(Type.POISON, 1.5);
            groundMap.put(Type.ROCK, 1.5);
            groundMap.put(Type.STEEL, 1.5);
            groundMap.put(Type.GRASS, 0.5);
            groundMap.put(Type.BUG, 0.5);
            groundMap.put(Type.FLYING, 0.0);
        
        // Flying
        Map<Type, Double> flyingMap = new HashMap<>();
            flyingMap.put(Type.GRASS, 1.5);
            flyingMap.put(Type.FIGHTING, 1.5);
            flyingMap.put(Type.BUG, 1.5);
            flyingMap.put(Type.ELECTRIC, 0.5);
            flyingMap.put(Type.ROCK, 0.5);
            flyingMap.put(Type.STEEL, 0.5);
        
        // Psychic
        Map<Type, Double> psychicMap = new HashMap<>();
            psychicMap.put(Type.FIGHTING, 1.5);
            psychicMap.put(Type.POISON, 1.5);
            psychicMap.put(Type.PSYCHIC, 0.5);
            psychicMap.put(Type.STEEL, 0.5);
            psychicMap.put(Type.DARK, 0.0);
        
        // Bug
        Map<Type, Double> bugMap = new HashMap<>();
            bugMap.put(Type.GRASS, 1.5);
            bugMap.put(Type.PSYCHIC, 1.5);
            bugMap.put(Type.DARK, 1.5);
            bugMap.put(Type.FIRE, 0.5);
            bugMap.put(Type.FIGHTING, 0.5);
            bugMap.put(Type.POISON, 0.5);
            bugMap.put(Type.FLYING, 0.5);
            bugMap.put(Type.GHOST, 0.5);
            bugMap.put(Type.STEEL, 0.5);
            bugMap.put(Type.FAIRY, 0.5);
        
        // Rock
        Map<Type, Double> rockMap = new HashMap<>();
            rockMap.put(Type.FIRE, 1.5);
            rockMap.put(Type.ICE, 1.5);
            rockMap.put(Type.FLYING, 1.5);
            rockMap.put(Type.BUG, 1.5);
            rockMap.put(Type.FIGHTING, 0.5);
            rockMap.put(Type.GROUND, 0.5);
            rockMap.put(Type.STEEL, 0.5);
        
        // Ghost
        Map<Type, Double> ghostMap = new HashMap<>();
            ghostMap.put(Type.PSYCHIC, 1.5);
            ghostMap.put(Type.GHOST, 1.5);
            ghostMap.put(Type.DARK, 0.5);
            ghostMap.put(Type.NORMAL, 0.0);
        
        // Dragon
        Map<Type, Double> dragonMap = new HashMap<>();
            dragonMap.put(Type.DRAGON, 1.5);
            dragonMap.put(Type.STEEL, 0.5);
            dragonMap.put(Type.FAIRY, 0.0);
            
        // Dark
        Map<Type, Double> darkMap = new HashMap<>();
            darkMap.put(Type.PSYCHIC, 1.5);
            darkMap.put(Type.GHOST, 1.5);
            darkMap.put(Type.FIGHTING, 0.5);
            darkMap.put(Type.DARK, 0.5);
            darkMap.put(Type.FAIRY, 0.5);
        
        // Steel
        Map<Type, Double> steelMap = new HashMap<>();
            steelMap.put(Type.ICE, 1.5);
            steelMap.put(Type.ROCK, 1.5);
            steelMap.put(Type.FAIRY, 1.5);
            steelMap.put(Type.FIRE, 0.5);
            steelMap.put(Type.WATER, 0.5);
            steelMap.put(Type.ELECTRIC, 0.5);
            steelMap.put(Type.STEEL, 0.5);
        
        // Fairy
        Map<Type, Double> fairyMap = new HashMap<>();
            fairyMap.put(Type.FIGHTING, 1.5);
            fairyMap.put(Type.DRAGON, 1.5);
            fairyMap.put(Type.DARK, 1.5);
            fairyMap.put(Type.FIRE, 0.5);
            fairyMap.put(Type.POISON, 0.5);
            fairyMap.put(Type.STEEL, 0.5);

        // Adicionando todos os tipos ao typeChart
        typeChart.put(Type.NORMAL, normalMap);
        typeChart.put(Type.FIRE, fireMap);
        typeChart.put(Type.WATER, waterMap);
        typeChart.put(Type.ELECTRIC, electricMap);
        typeChart.put(Type.GRASS, grassMap);
        typeChart.put(Type.ICE, iceMap);
        typeChart.put(Type.FIGHTING, fightingMap);
        typeChart.put(Type.POISON, poisonMap);
        typeChart.put(Type.GROUND, groundMap);
        typeChart.put(Type.FLYING, flyingMap);
        typeChart.put(Type.PSYCHIC, psychicMap);
        typeChart.put(Type.BUG, bugMap);
        typeChart.put(Type.ROCK, rockMap);
        typeChart.put(Type.GHOST, ghostMap);
        typeChart.put(Type.DRAGON, dragonMap);
        typeChart.put(Type.DARK, darkMap);
        typeChart.put(Type.STEEL, steelMap);
        typeChart.put(Type.FAIRY, fairyMap);
    }
        
    // Checar as vantagens a partir do tipo
    public static double checkMultiplier(Type typeAttacker, Type typeDefender) {
        // Se não tiver o tipo do atacante ou se o tipo do atacante não tiver vantagens contra o defensor retorna 1
        if (!typeChart.containsKey(typeAttacker) || !typeChart.get(typeAttacker).containsKey(typeDefender)) 
            return 1;
        else
            // Retorna o tipo de vantagem do atacante sobre o defensor
            return typeChart.get(typeAttacker).get(typeDefender);   
    }
}