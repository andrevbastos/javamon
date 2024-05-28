package combat;

import java.lang.reflect.Method;
import java.util.Random;

import visuals.GamePanel;

public class Battle{
    private Trainer t1;
    private Trainer t2;
    private GamePanel gp;

    public Battle(Trainer t1, Trainer t2, GamePanel gp) {
        this.t1 = t1;
        this.t2 = t2;
        this.gp = gp;
        
        updatePokemonInfo();

    }

    public void battle() {
        Trainer first;
        Trainer second;
        Trainer winner = null;
        boolean stop = false;

        while (!stop) {

            first = (t1.getPokemon().getSpeed() >= t2.getPokemon().getSpeed()) ? t1 : t2;
            second = (first == t1) ? t2 : t1;

            useMove(first, second);
            updatePokemonInfo();
            
            // second só ataca se sobreviver o do first
            if (second.getPokemon().getHp() != 0) {
                useMove(second, first);
                updatePokemonInfo();
            }

            winner = (t1.getPokemon().getHp() == 0) ? t2 : (t2.getPokemon().getHp() == 0) ? t1 : null;
            stop = (winner != null);

        }

        System.out.println("\n" + winner.getName() + " won!");
        t1.getPokemon().heal();
        t2.getPokemon().heal();
        
    }

    // Atacar
    public void useMove(Trainer attacker, Trainer defender) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1;    // D100
        int i = rn.nextInt(3);              // ataque aleatório
        String txt;

        txt = attacker.getPokemon() + " uses " + attacker.getPokemon().getMoves(i).getName() + "!";
        txt = (attacker == t2) ? "Foe " + txt : txt;
        updateTextBox(txt);
        
        // Checagem se acertou o ataque
        if (ataque <= attacker.getPokemon().getAccuracy())
            takeMove(attacker.getPokemon().getMoves(i), attacker.getPokemon(), defender.getPokemon());
        else {
            txt = attacker.getPokemon()  + " missed.";
            txt = (attacker == t2) ? "Foe " + txt : txt;
            updateTextBox(txt);
        }
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

        if (category != "STATUS1" && category != "STATUS2") {
            if (defender.getHp() - damage < 0)
                defender.setHp(0);
            else
                defender.setHp(defender.getHp() - damage);
        }

    }

    public void multiplierToText(double multiplier) {
        if (multiplier == 1.0) {
            updateTextBox("It's effective.");
        } else if (multiplier == 1.5) {
            updateTextBox("It's super effective!");
        } else if (multiplier == 0.5) {
            updateTextBox("It's not very effective...");
        }
    }

    public void status(Moves move, Pokemon p, int value) {
        Method m;
		try {
			m = methodTroughName(Pokemon.class, "set" + move.getAttribute1());
            String txt = (String) m.invoke(p, value);
            if (p == t2.getPokemon())
                txt = "Foe " + txt;
			updateTextBox(txt);
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

    private void updatePokemonInfo() {
        gp.updatePokemon1Info(t1.getPokemon().getName(), t1.getPokemon().getHp());
        gp.updatePokemon2Info(t2.getPokemon().getName(), t2.getPokemon().getHp());
    }

    private void updateTextBox(String txt) {
        gp.updateTextBox(txt);
    }

}
