package combat;

import java.lang.reflect.Method;
import java.util.Random;

import visuals.screens.*;

public class Battle{
    private Pokemon p1;
    private Pokemon p2;
    private Combat c;

    public Battle(Combat c) {
        this.c = c;
        
    }

    public void setP1(Pokemon p1) {
        this.p1 = p1;
    }

    public void setP2(Pokemon p2) {
        this.p2 = p2;
    }

    public String run() {
        Pokemon first;
        Pokemon second;
        Pokemon winner = null;
        boolean stop = false;
        c.addToHistory("(" + p1.getName() + " x " + p2.getName() + ": ");

        while (!stop) {

            first = (p1.getSpeed() >= p2.getSpeed()) ? p1 : p2;
            second = (first == p1) ? p2 : p1;

            useMove(first, second);
            
            if (second.getHp() != 0) {
                useMove(second, first);
            }

            winner = (p1.getHp() == 0) ? p2 : (p2.getHp() == 0) ? p1 : null;
            stop = (winner != null);

        }

        p1.heal();
        p2.heal();
        c.addToHistory(");\n");
        return winner.getName();
        
    }

    public void useMove(Pokemon attacker, Pokemon defender) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1;
        int i = rn.nextInt(3);
        String txt;

        txt = " " + attacker + " uses " + attacker.getMoves(i).getName() + ";";
        c.addToHistory(txt);
        
        if (ataque <= attacker.getAccuracy())
            takeMove(attacker.getMoves(i), attacker, defender);

    }

    // Receber ataque
    public void takeMove(Moves move, Pokemon attacker, Pokemon defender) {
        String category = move.getCategory();
        double damage = 0;
        double multiplier = Types.checkMultiplier(move.getType(), defender.getType());

        switch (category) {
        case "PHYSICAL":
            damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) / 5) + 2;
            break;

        case "SPECIAL":
            damage = (int) ((move.getPower() * attacker.getSpAttack() / defender.getSpDefense()) / 5) + 2;
            break;
        
        case "STATUS1":
            status(move, defender, -1);
            break;

        case "STATUS2":
            status(move, attacker, 1);
            break;
            
        }

        damage = damage * multiplier;

        if (category != "STATUS1" && category != "STATUS2") {
            if (defender.getHp() - damage < 0)
                defender.setHp(0);
            else
                defender.setHp(defender.getHp() - damage);
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
