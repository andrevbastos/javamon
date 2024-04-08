import java.util.HashMap;
import java.util.Map;

public class TypeMap {

    // Tabela hash dos tipos e suas vantagens
    static Map<String, Map<String, Double>> typeChart = new HashMap<>();

    static{
        // Criar as vantagens para cada tipo
        Map<String, Double> fireMap = new HashMap<>();
            fireMap.put("GRASS", 2.0);
            fireMap.put("WATER", 0.5);
    
        Map<String, Double> waterMap = new HashMap<>();
            waterMap.put("FIRE", 2.0);
            waterMap.put("GRASS", 0.5);
    
        Map<String, Double> grassMap = new HashMap<>();
            grassMap.put("WATER", 2.0);
            grassMap.put("FIRE", 0.5);
    
        // Colocar os tipos e suas vantagens no typeChart
        typeChart.put("FIRE", fireMap);
        typeChart.put("WATER", waterMap);
        typeChart.put("GRASS", grassMap);
    }
        
    // Checar as vantagens a partir do tipo
    public double checkMultiplier(String typeAttacker, String typeDefender) {
        // Se não tiver o tipo do atacante ou se o tipo do atacante não tiver vantagens contra o defensor retorna 1
        if (!typeChart.containsKey(typeAttacker) || !typeChart.get(typeAttacker).containsKey(typeDefender)) 
            return 1;
        else
            // Retorna o tipo de vantagem do atacante sobre o defensor
            return typeChart.get(typeAttacker).get(typeDefender);
    }
}
