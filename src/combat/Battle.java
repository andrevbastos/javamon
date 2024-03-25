package combat;

import java.util.Scanner;

public class Battle {
    private Trainer p1;
    private Trainer p2;

    public Battle(Trainer player, Trainer enemy) {
        this.p1 = player;
        this.p2 = enemy;
    }

    public void batalhar() {
        Scanner teclado = new Scanner(System.in);
        boolean stop = false;
        Trainer primeiro;
        Trainer segundo;

        while (!stop) {

            // Mostrar vida dos dois
            System.out.println("\n-\n\n" + p1.getName() + "`s " + p1.getPokemon().getName() + ": " + p1.getPokemon().getHp() + "\n" + p2.getName() + "`s "  + p2.getPokemon().getName() + ": " + p2.getPokemon().getHp());
            
            // Mostrar ataques do pokemon do player
            System.out.println("\n\t1. " + p1.getPokemon().getMoves(0).getName() + "\t2. " + p1.getPokemon().getMoves(1).getName() + "\t3. " + p1.getPokemon().getMoves(2).getName() + "\t4. " + p1.getPokemon().getMoves(3).getName());
            
            // Checar qual ataque do player
            int i = teclado.nextInt() - 1;

            // Setar qual pokemon vai agir primeiro e ataque de cada um em ordem
            if (p1.getPokemon().getSpeed() >= p2.getPokemon().getSpeed()) {
                primeiro = p1;
                segundo = p2;

                primeiro.getPokemon().useMove(segundo.getPokemon(), i);            
                
                // Segundo só ataca se sobreviver o do primeiro
                if (segundo.getPokemon().getHp() != 0)
                    segundo.getPokemon().useMove(primeiro.getPokemon());

            } else {
                primeiro = p2;
                segundo = p1;

                primeiro.getPokemon().useMove(segundo.getPokemon());            
                
                // Segundo só ataca se sobreviver o do primeiro
                if (segundo.getPokemon().getHp() != 0)
                    segundo.getPokemon().useMove(primeiro.getPokemon(), 1);
            }

            // Acaba se um morrer
            if (p1.getPokemon().getHp() == 0 || p2.getPokemon().getHp() == 0)
                stop = true;
        }

        teclado.close();
    }
}
