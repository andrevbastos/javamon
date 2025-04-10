package model.util;

import java.util.HashMap;
import java.util.Map;

public class Types {

    // Tabela hash dos tipos e suas vantagens
    static Map<String, Map<String, Double>> typeChart = new HashMap<>();

    static {
        // Normal
        Map<String, Double> normalMap = new HashMap<>();
            normalMap.put("ROCK", 0.5);
            normalMap.put("STEEL", 0.5);
            normalMap.put("GHOST", 0.0);
        
        // Fire
        Map<String, Double> fireMap = new HashMap<>();
            fireMap.put("GRASS", 1.5);
            fireMap.put("ICE", 1.5);
            fireMap.put("BUG", 1.5);
            fireMap.put("STEEL", 1.5);
            fireMap.put("WATER", 0.5);
            fireMap.put("FIRE", 0.5);
            fireMap.put("ROCK", 0.5);
            fireMap.put("DRAGON", 0.5);
        
        // Water
        Map<String, Double> waterMap = new HashMap<>();
            waterMap.put("FIRE", 1.5);
            waterMap.put("GROUND", 1.5);
            waterMap.put("ROCK", 1.5);
            waterMap.put("WATER", 0.5);
            waterMap.put("GRASS", 0.5);
            waterMap.put("DRAGON", 0.5);

        // Grass
        Map<String, Double> grassMap = new HashMap<>();
            grassMap.put("WATER", 1.5);
            grassMap.put("GROUND", 1.5);
            grassMap.put("ROCK", 1.5);
            grassMap.put("FIRE", 0.5);
            grassMap.put("GRASS", 0.5);
            grassMap.put("POISON", 0.5);
            grassMap.put("FLYING", 0.5);
            grassMap.put("BUG", 0.5);
            grassMap.put("DRAGON", 0.5);
            grassMap.put("STEEL", 0.5);
        
        // Electric
        Map<String, Double> electricMap = new HashMap<>();
            electricMap.put("WATER", 1.5);
            electricMap.put("FLYING", 1.5);
            electricMap.put("ELECTRIC", 0.5);
            electricMap.put("GRASS", 0.5);
            electricMap.put("DRAGON", 0.5);
            electricMap.put("GROUND", 0.0);
        
        // Ice
        Map<String, Double> iceMap = new HashMap<>();
            iceMap.put("GRASS", 1.5);
            iceMap.put("GROUND", 1.5);
            iceMap.put("FLYING", 1.5);
            iceMap.put("DRAGON", 1.5);
            iceMap.put("FIRE", 0.5);
            iceMap.put("WATER", 0.5);
            iceMap.put("ICE", 0.5);
            iceMap.put("STEEL", 0.5);
        
        // Fighting
        Map<String, Double> fightingMap = new HashMap<>();
            fightingMap.put("NORMAL", 1.5);
            fightingMap.put("ICE", 1.5);
            fightingMap.put("ROCK", 1.5);
            fightingMap.put("DARK", 1.5);
            fightingMap.put("STEEL", 1.5);
            fightingMap.put("POISON", 0.5);
            fightingMap.put("FLYING", 0.5);
            fightingMap.put("PSYCHIC", 0.5);
            fightingMap.put("BUG", 0.5);
            fightingMap.put("FAIRY", 0.5);
            fightingMap.put("GHOST", 0.0);
        
        // Poison
        Map<String, Double> poisonMap = new HashMap<>();
            poisonMap.put("GRASS", 1.5);
            poisonMap.put("FAIRY", 1.5);
            poisonMap.put("POISON", 0.5);
            poisonMap.put("GROUND", 0.5);
            poisonMap.put("ROCK", 0.5);
            poisonMap.put("GHOST", 0.5);
            poisonMap.put("STEEL", 0.0);
        
        // Ground
        Map<String, Double> groundMap = new HashMap<>();
            groundMap.put("FIRE", 1.5);
            groundMap.put("ELECTRIC", 1.5);
            groundMap.put("POISON", 1.5);
            groundMap.put("ROCK", 1.5);
            groundMap.put("STEEL", 1.5);
            groundMap.put("GRASS", 0.5);
            groundMap.put("BUG", 0.5);
            groundMap.put("FLYING", 0.0);
        
        // Flying
        Map<String, Double> flyingMap = new HashMap<>();
            flyingMap.put("GRASS", 1.5);
            flyingMap.put("FIGHTING", 1.5);
            flyingMap.put("BUG", 1.5);
            flyingMap.put("ELECTRIC", 0.5);
            flyingMap.put("ROCK", 0.5);
            flyingMap.put("STEEL", 0.5);
        
        // Psychic
        Map<String, Double> psychicMap = new HashMap<>();
            psychicMap.put("FIGHTING", 1.5);
            psychicMap.put("POISON", 1.5);
            psychicMap.put("PSYCHIC", 0.5);
            psychicMap.put("STEEL", 0.5);
            psychicMap.put("DARK", 0.0);
        
        // Bug
        Map<String, Double> bugMap = new HashMap<>();
            bugMap.put("GRASS", 1.5);
            bugMap.put("PSYCHIC", 1.5);
            bugMap.put("DARK", 1.5);
            bugMap.put("FIRE", 0.5);
            bugMap.put("FIGHTING", 0.5);
            bugMap.put("POISON", 0.5);
            bugMap.put("FLYING", 0.5);
            bugMap.put("GHOST", 0.5);
            bugMap.put("STEEL", 0.5);
            bugMap.put("FAIRY", 0.5);
        
        // Rock
        Map<String, Double> rockMap = new HashMap<>();
            rockMap.put("FIRE", 1.5);
            rockMap.put("ICE", 1.5);
            rockMap.put("FLYING", 1.5);
            rockMap.put("BUG", 1.5);
            rockMap.put("FIGHTING", 0.5);
            rockMap.put("GROUND", 0.5);
            rockMap.put("STEEL", 0.5);
        
        // Ghost
        Map<String, Double> ghostMap = new HashMap<>();
            ghostMap.put("PSYCHIC", 1.5);
            ghostMap.put("GHOST", 1.5);
            ghostMap.put("DARK", 0.5);
            ghostMap.put("NORMAL", 0.0);
        
        // Dragon
        Map<String, Double> dragonMap = new HashMap<>();
            dragonMap.put("DRAGON", 1.5);
            dragonMap.put("STEEL", 0.5);
            dragonMap.put("FAIRY", 0.0);
        
        // Dark
        Map<String, Double> darkMap = new HashMap<>();
            darkMap.put("PSYCHIC", 1.5);
            darkMap.put("GHOST", 1.5);
            darkMap.put("FIGHTING", 0.5);
            darkMap.put("DARK", 0.5);
            darkMap.put("FAIRY", 0.5);
        
        // Steel
        Map<String, Double> steelMap = new HashMap<>();
            steelMap.put("ICE", 1.5);
            steelMap.put("ROCK", 1.5);
            steelMap.put("FAIRY", 1.5);
            steelMap.put("FIRE", 0.5);
            steelMap.put("WATER", 0.5);
            steelMap.put("ELECTRIC", 0.5);
            steelMap.put("STEEL", 0.5);
        
        // Fairy
        Map<String, Double> fairyMap = new HashMap<>();
            fairyMap.put("FIGHTING", 1.5);
            fairyMap.put("DRAGON", 1.5);
            fairyMap.put("DARK", 1.5);
            fairyMap.put("FIRE", 0.5);
            fairyMap.put("POISON", 0.5);
            fairyMap.put("STEEL", 0.5);

        // Adicionando todos os tipos ao typeChart
        typeChart.put("NORMAL", normalMap);
        typeChart.put("FIRE", fireMap);
        typeChart.put("WATER", waterMap);
        typeChart.put("ELECTRIC", electricMap);
        typeChart.put("GRASS", grassMap);
        typeChart.put("ICE", iceMap);
        typeChart.put("FIGHTING", fightingMap);
        typeChart.put("POISON", poisonMap);
        typeChart.put("GROUND", groundMap);
        typeChart.put("FLYING", flyingMap);
        typeChart.put("PSYCHIC", psychicMap);
        typeChart.put("BUG", bugMap);
        typeChart.put("ROCK", rockMap);
        typeChart.put("GHOST", ghostMap);
        typeChart.put("DRAGON", dragonMap);
        typeChart.put("DARK", darkMap);
        typeChart.put("STEEL", steelMap);
        typeChart.put("FAIRY", fairyMap);
    }
        
    // Checar as vantagens a partir do tipo
    public static double checkMultiplier(String typeAttacker, String typeDefender) {
        // Se não tiver o tipo do atacante ou se o tipo do atacante não tiver vantagens contra o defensor retorna 1
        if (!typeChart.containsKey(typeAttacker) || !typeChart.get(typeAttacker).containsKey(typeDefender)) 
            return 1;
        else
            // Retorna o tipo de vantagem do atacante sobre o defensor
            return typeChart.get(typeAttacker).get(typeDefender);   
    }
}