import java.util.Random;

public class Battle {
    private Trainer p1;
    private Trainer p2;

    public Battle(Trainer p1, Trainer p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    // public void status(Moves move, Pokemon p) {
    //     try {
    //         Method method = p.getClass().getMethod(move.getAttribute1());
    //         method.invoke(p, 1);

    //     } catch (NoSuchMethodException e) {
    //         e.printStackTrace();
    //     } catch (SecurityException e) {
    //         e.printStackTrace();
    //     } catch (IllegalAccessException e) {
    //         e.printStackTrace();
    //     } catch (InvocationTargetException e) {
    //         e.printStackTrace();
    //     }
    // }

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
                System.out.println("escolheu o primeiro");

                useMove(first, second);    
                System.out.println("usou move");         
                
                // second só ataca se sobreviver o do first
                if (second.getPokemon().getHp() != 0)
                    useMove(second, first);

            } else {
                first = p2;
                second = p1;
                System.out.println("escolheu o primeiro");

                useMove(first, second);    
                System.out.println("usou move");       
                
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
        double damage = 0;

        // Checagem do type do ataque antes de receber
        switch (category) {
        case "PHYSICAL":
            damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) / 5) + 2;
            break;

        case "SPECIAL":
            damage = (int) ((move.getPower() * attacker.getSpattack() / defender.getSpdefense()) / 5) + 2;
            break;
        
        case "STATUS1":
            
            break;

        case "STATUS2":

            break;
            
        }

        // Receber o damage depois da checagem de categoria
        if (category != "STATUS") {
            if (defender.getHp() - damage < 0)
                defender.setHp(0);
            else
                defender.setHp(defender.getHp() - damage);
        }
    }
}
