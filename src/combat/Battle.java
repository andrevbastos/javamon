package combat;

import java.lang.reflect.Method;
import java.util.Random;

import visuals.GamePanel;

public class Battle{
    private Trainer p1;
    private Trainer p2;

    public Battle(Trainer p1, Trainer p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void battle() {
        Trainer first;
        Trainer second;
        Trainer winner = null;
        boolean stop = false;

        while (!stop) {
            // Mostrar vida dos dois
            // System.out.println("\n\n" + p1.getName() + "`s " + p1.getPokemon().getName() + ": " + p1.getPokemon().getHp()
            //  + "\n" + p2.getName() + "`s "  + p2.getPokemon().getName() + ": " + p2.getPokemon().getHp());

            // Setar qual pokemon vai agir first e ataque de cada um em ordem
            if (p1.getPokemon().getSpeed() >= p2.getPokemon().getSpeed()) {

                first = p1;
                second = p2;

                useMove(first, second);
                
                // second só ataca se sobreviver o do first
                if (second.getPokemon().getHp() != 0) {
                    useMove(second, first);
                }

            } else {
                first = p2;
                second = p1;

                useMove(first, second); 
                
                // second só ataca se sobreviver o do first
                if (second.getPokemon().getHp() != 0) {
                    useMove(second, first);
                }

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
        p1.getPokemon().heal();
        p2.getPokemon().heal();
        
    }

    // Atacar
    public void useMove(Trainer attacker, Trainer defender) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        double damage = 0;
        double multiplier = TypeMap.checkMultiplier(move.getType(), defender.getType());

        // Checagem do type do ataque antes de receber
        switch (category) {
        case "PHYSICAL":
            damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) / 5) + 2;
            multiplierToText(multiplier);
            break;

        case "SPECIAL":
            damage = (int) ((move.getPower() * attacker.getSpAttack() / defender.getSpDefense()) / 5) + 2;
            multiplierToText(multiplier);
            break;
        
        case "STATUS1":
            status(move, defender, -1);
            break;

        case "STATUS2":
            status(move, attacker, 1);
            break;
            
        }

        // Receber o damage depois da checagem de categoria
        damage = damage * multiplier; 
        System.out.println(damage);

        if (defender.getHp() - damage < 0)
            defender.setHp(0);
        else
            defender.setHp(defender.getHp() - damage);

    }

    public void multiplierToText(double multiplier) {
        if (multiplier == 1.0) {
            System.out.println("It's effective.");
        } else if (multiplier == 1.5) {
            System.out.println("It's super effective!");
        } else if (multiplier == 0.5) {
            System.out.println("It's not very effective...");
        }
    }

    public void status(Moves move, Pokemon p, int value) {
        Method m;
		try {
			m = methodTroughName(Pokemon.class, "set" + move.getAttribute1());
			m.invoke(p, value);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    public static Method methodTroughName(Class<?> c, String nome) throws Exception {
		for (Method m : c.getMethods()) {
			if (m.getName().equals(nome)) {
				return m;
			} 
		}
		throw new Exception("Método " + nome + " não encontrado");
	}
}
