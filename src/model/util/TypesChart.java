package model.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for managing the type advantages and disadvantages
 * in the game. It uses a hash table to store the types and their respective
 * advantages against other types.
 * 
 * @see model.util.Type
 */
public class TypesChart {
    static Map<Type, Map<Type, Float>> typeChart = new HashMap<>();

    static {
        // Normal
        Map<Type, Float> normalMap = new HashMap<>();
            normalMap.put(Type.ROCK, 0.5f);
            normalMap.put(Type.STEEL, 0.5f);
            normalMap.put(Type.GHOST, 0.0f);
        
        // Fire
        Map<Type, Float> fireMap = new HashMap<>();
            fireMap.put(Type.GRASS, 1.5f);
            fireMap.put(Type.ICE, 1.5f);
            fireMap.put(Type.BUG, 1.5f);
            fireMap.put(Type.STEEL, 1.5f);
            fireMap.put(Type.WATER, 0.5f);
            fireMap.put(Type.FIRE, 0.5f);
            fireMap.put(Type.ROCK, 0.5f);
            fireMap.put(Type.DRAGON, 0.5f);
        
        // Water
        Map<Type, Float> waterMap = new HashMap<>();
            waterMap.put(Type.FIRE, 1.5f);
            waterMap.put(Type.GROUND, 1.5f);
            waterMap.put(Type.ROCK, 1.5f);
            waterMap.put(Type.WATER, 0.5f);
            waterMap.put(Type.GRASS, 0.5f);
            waterMap.put(Type.DRAGON, 0.5f);

        // Grass
        Map<Type, Float> grassMap = new HashMap<>();
            grassMap.put(Type.WATER, 1.5f);
            grassMap.put(Type.GROUND, 1.5f);
            grassMap.put(Type.ROCK, 1.5f);
            grassMap.put(Type.FIRE, 0.5f);
            grassMap.put(Type.GRASS, 0.5f);
            grassMap.put(Type.POISON, 0.5f);
            grassMap.put(Type.FLYING, 0.5f);
            grassMap.put(Type.BUG, 0.5f);
            grassMap.put(Type.DRAGON, 0.5f);
            grassMap.put(Type.STEEL, 0.5f);
        
        // Electric
        Map<Type, Float> electricMap = new HashMap<>();
            electricMap.put(Type.WATER, 1.5f);
            electricMap.put(Type.FLYING, 1.5f);
            electricMap.put(Type.ELECTRIC, 0.5f);
            electricMap.put(Type.GRASS, 0.5f);
            electricMap.put(Type.DRAGON, 0.5f);
            electricMap.put(Type.GROUND, 0.0f);
        
        // Ice
        Map<Type, Float> iceMap = new HashMap<>();
            iceMap.put(Type.GRASS, 1.5f);
            iceMap.put(Type.GROUND, 1.5f);
            iceMap.put(Type.FLYING, 1.5f);
            iceMap.put(Type.DRAGON, 1.5f);
            iceMap.put(Type.FIRE, 0.5f);
            iceMap.put(Type.WATER, 0.5f);
            iceMap.put(Type.ICE, 0.5f);
            iceMap.put(Type.STEEL, 0.5f);
        
        // Fighting
        Map<Type, Float> fightingMap = new HashMap<>();
            fightingMap.put(Type.NORMAL, 1.5f);
            fightingMap.put(Type.ICE, 1.5f);
            fightingMap.put(Type.ROCK, 1.5f);
            fightingMap.put(Type.DARK, 1.5f);
            fightingMap.put(Type.STEEL, 1.5f);
            fightingMap.put(Type.POISON, 0.5f);
            fightingMap.put(Type.FLYING, 0.5f);
            fightingMap.put(Type.PSYCHIC, 0.5f);
            fightingMap.put(Type.BUG, 0.5f);
            fightingMap.put(Type.FAIRY, 0.5f);
            fightingMap.put(Type.GHOST, 0.0f);
        
        // Poison
        Map<Type, Float> poisonMap = new HashMap<>();
            poisonMap.put(Type.GRASS, 1.5f);
            poisonMap.put(Type.FAIRY, 1.5f);
            poisonMap.put(Type.POISON, 0.5f);
            poisonMap.put(Type.GROUND, 0.5f);
            poisonMap.put(Type.ROCK, 0.5f);
            poisonMap.put(Type.GHOST, 0.5f);
            poisonMap.put(Type.STEEL, 0.0f);
        
        // Ground
        Map<Type, Float> groundMap = new HashMap<>();
            groundMap.put(Type.FIRE, 1.5f);
            groundMap.put(Type.ELECTRIC, 1.5f);
            groundMap.put(Type.POISON, 1.5f);
            groundMap.put(Type.ROCK, 1.5f);
            groundMap.put(Type.STEEL, 1.5f);
            groundMap.put(Type.GRASS, 0.5f);
            groundMap.put(Type.BUG, 0.5f);
            groundMap.put(Type.FLYING, 0.0f);
        
        // Flying
        Map<Type, Float> flyingMap = new HashMap<>();
            flyingMap.put(Type.GRASS, 1.5f);
            flyingMap.put(Type.FIGHTING, 1.5f);
            flyingMap.put(Type.BUG, 1.5f);
            flyingMap.put(Type.ELECTRIC, 0.5f);
            flyingMap.put(Type.ROCK, 0.5f);
            flyingMap.put(Type.STEEL, 0.5f);
        
        // Psychic
        Map<Type, Float> psychicMap = new HashMap<>();
            psychicMap.put(Type.FIGHTING, 1.5f);
            psychicMap.put(Type.POISON, 1.5f);
            psychicMap.put(Type.PSYCHIC, 0.5f);
            psychicMap.put(Type.STEEL, 0.5f);
            psychicMap.put(Type.DARK, 0.0f);
        
        // Bug
        Map<Type, Float> bugMap = new HashMap<>();
            bugMap.put(Type.GRASS, 1.5f);
            bugMap.put(Type.PSYCHIC, 1.5f);
            bugMap.put(Type.DARK, 1.5f);
            bugMap.put(Type.FIRE, 0.5f);
            bugMap.put(Type.FIGHTING, 0.5f);
            bugMap.put(Type.POISON, 0.5f);
            bugMap.put(Type.FLYING, 0.5f);
            bugMap.put(Type.GHOST, 0.5f);
            bugMap.put(Type.STEEL, 0.5f);
            bugMap.put(Type.FAIRY, 0.5f);
        
        // Rock
        Map<Type, Float> rockMap = new HashMap<>();
            rockMap.put(Type.FIRE, 1.5f);
            rockMap.put(Type.ICE, 1.5f);
            rockMap.put(Type.FLYING, 1.5f);
            rockMap.put(Type.BUG, 1.5f);
            rockMap.put(Type.FIGHTING, 0.5f);
            rockMap.put(Type.GROUND, 0.5f);
            rockMap.put(Type.STEEL, 0.5f);
        
        // Ghost
        Map<Type, Float> ghostMap = new HashMap<>();
            ghostMap.put(Type.PSYCHIC, 1.5f);
            ghostMap.put(Type.GHOST, 1.5f);
            ghostMap.put(Type.DARK, 0.5f);
            ghostMap.put(Type.NORMAL, 0.0f);
        
        // Dragon
        Map<Type, Float> dragonMap = new HashMap<>();
            dragonMap.put(Type.DRAGON, 1.5f);
            dragonMap.put(Type.STEEL, 0.5f);
            dragonMap.put(Type.FAIRY, 0.0f);
            
        // Dark
        Map<Type, Float> darkMap = new HashMap<>();
            darkMap.put(Type.PSYCHIC, 1.5f);
            darkMap.put(Type.GHOST, 1.5f);
            darkMap.put(Type.FIGHTING, 0.5f);
            darkMap.put(Type.DARK, 0.5f);
            darkMap.put(Type.FAIRY, 0.5f);
        
        // Steel
        Map<Type, Float> steelMap = new HashMap<>();
            steelMap.put(Type.ICE, 1.5f);
            steelMap.put(Type.ROCK, 1.5f);
            steelMap.put(Type.FAIRY, 1.5f);
            steelMap.put(Type.FIRE, 0.5f);
            steelMap.put(Type.WATER, 0.5f);
            steelMap.put(Type.ELECTRIC, 0.5f);
            steelMap.put(Type.STEEL, 0.5f);
        
        // Fairy
        Map<Type, Float> fairyMap = new HashMap<>();
            fairyMap.put(Type.FIGHTING, 1.5f);
            fairyMap.put(Type.DRAGON, 1.5f);
            fairyMap.put(Type.DARK, 1.5f);
            fairyMap.put(Type.FIRE, 0.5f);
            fairyMap.put(Type.POISON, 0.5f);
            fairyMap.put(Type.STEEL, 0.5f);

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
    public static Float checkMultiplier(Type typeAttacker, Type typeDefender) {
        // Se não tiver o tipo do atacante ou se o tipo do atacante não tiver vantagens contra o defensor retorna 1
        if (!typeChart.containsKey(typeAttacker) || !typeChart.get(typeAttacker).containsKey(typeDefender)) 
            return 1.0f;
        else
            // Retorna o tipo de vantagem do atacante sobre o defensor
            return typeChart.get(typeAttacker).get(typeDefender);   
    }
}