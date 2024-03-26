import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Battle {
    private Trainer p1;
    private Trainer p2;
    
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
    
    public static double checkMultiplier(String typeAttacker, String typeDefender) {
        // Se não tiver o tipo do atacante ou se o tipo do atacante não tiver vantagens contra o defensor retorna 1
        if (!typeChart.containsKey(typeAttacker) || !typeChart.get(typeAttacker).containsKey(typeDefender)) {
            return 1;
        }
        
        // Retorna o tipo de vantagem do atacante sobre o defensor
        return typeChart.get(typeAttacker).get(typeDefender);

    }

    public Battle(Trainer p1, Trainer p2) {
        this.p1 = p1;
        this.p2 = p2;
    }


    public void battle() {
        Trainer first;
        Trainer second;
        Trainer winner = null;
        int i = 0;
        boolean stop = false;

        while (!stop) {
            i++;

            // Mostrar vida dos dois
            System.out.println("\n- Round " + i + "\n\n" + p1.getName() + "`s " + p1.getPokemon().getName() + ": " + p1.getPokemon().getHp()
             + "\n" + p2.getName() + "`s "  + p2.getPokemon().getName() + ": " + p2.getPokemon().getHp());

            // Setar qual pokemon vai agir first e ataque de cada um em ordem
            if (p1.getPokemon().getSpeed() >= p2.getPokemon().getSpeed()) {
                first = p1;
                second = p2;

                useMove(first, second);            
                
                // second só ataca se sobreviver o do first
                if (second.getPokemon().getHp() != 0)
                    useMove(second, first);

            } else {
                first = p2;
                second = p1;

                useMove(first, second);           
                
                // second só ataca se sobreviver o do first
                if (second.getPokemon().getHp() != 0)
                    useMove(second, first);
            }

            if (p1.getPokemon().getHp() == 0) {
                winner = p2;
                stop = true;
            }

            if (p2.getPokemon().getHp() == 0) {
                winner = p1;
                stop = true;
            }
        }

        System.out.println("\n" + winner.getName() + " won!");
    }

    // Atacar
    public void useMove(Trainer attacker, Trainer defender) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1;    // D100
        int i = rn.nextInt(3);              // ataque aleatório
        System.out.println("\n" + attacker + "`s " + attacker.getPokemon() + " uses " + attacker.getPokemon().getMoves(i).getName() + "!");
        
        // Checagem se acertou o ataque
        if (ataque <= attacker.getPokemon().getAccuracy())
            takeMove(attacker.getPokemon().getMoves(i), attacker.getPokemon(), defender.getPokemon());
        else
            System.out.println(attacker + "`s " + attacker.getPokemon()  + " missed.");
    }

    // Receber ataque
    public void takeMove(Moves move, Pokemon attacker, Pokemon defender) {
        String category = move.getCategory();
        String status = move.getName();
        double damage = 0;

        // Checagem do type do ataque antes de receber
        switch (category) {
        case "PHYSICAL":
            damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) / 5) + 2;
            break;
        case "SPECIAL":
            damage = (int) ((move.getPower() * attacker.getSpattack() / defender.getSpdefense()) / 5) + 2;
            break;
        
        // Type de ataques de status
        case "STATUS":
            switch (status) {
            case "GROWL": 
                if (move.getPower() != 3) {
                    defender.setAttack(-5);
                    move.setPower(1);
                    System.out.println(defender.getName() + "`s Attack fell.");
                } else 
                    System.out.println(defender.getName() + "`s Attack can`t be reduced any further.");
                break;
            case "SMOKESCREEN":
                if (move.getPower() != 3) {
                    defender.setAccuracy(-5);
                    move.setPower(1);
                    System.out.println(defender.getName() + "`s Accuracy fell.");
                } else 
                    System.out.println(defender.getName() + "`s Accuracy can`t be reduced any further.");
                break;
            case "TAIL WHIP":
                if (move.getPower() != 3) {
                    defender.setDefense(-5);
                    move.setPower(1);
                    System.out.println(defender.getName() + "`s Defense fell.");
                } else 
                    System.out.println(defender.getName() + "`s Defense can`t be reduced any further.");
                break;
            case "GROWTH":
                if (move.getPower() != 2) {
                    attacker.setAttack(5);
                    attacker.setSpAttack(2);
                    move.setPower(1);
                    System.out.println(attacker.getName() + "`s Attack and Sp. Attack rose.");
                } else 
                    System.out.println(attacker.getName() + "`s Attack and Sp. Attack can`t go any further.");
                break;
            case "WITHDRAW":
                if (move.getPower() != 3) {
                    attacker.setDefense(5);
                    move.setPower(1);
                    System.out.println(attacker.getName() + "`s Defense rose.");
                } else 
                    System.out.println(attacker.getName() + "`s Defense can`t go any further.");
                break;
            }
        }

        // Receber o damage depois da checagem de categoria
        if (category != "STATUS") {
            double multiplier = checkMultiplier(attacker.getType(), defender.getType());

            damage = damage * multiplier;

            if (defender.getHp() - damage < 0)
                defender.setHp(0);
            else
                defender.setHp(defender.getHp() - damage);

            switch (multiplier) {
                case 1.5:
                    System.out.println("It's very effectie!\n");
                    break;
                
                case 0.5:
                    System.out.println("It's not very effectie...\n");
                    break;
                
                case 0.0    :
                    System.out.println("It doesn't affect the opposing " + defender.getName() + ".\n");
                    break;
                    
                default:
                    break;
            }
        }
    }
}
