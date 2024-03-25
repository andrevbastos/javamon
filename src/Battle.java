import java.util.Random;

public class Battle {
    private Trainer p1;
    private Trainer p2;

    public Battle(Trainer player, Trainer enemy) {
        this.p1 = player;
        this.p2 = enemy;
    }

    public void batalhar() {
        Trainer primeiro;
        Trainer segundo;
        int i = 1;
        boolean stop = false;

        while (!stop) {

            // Mostrar vida dos dois
            System.out.println("\n- Round " + i + "\n\n" + p1.getName() + "`s " + p1.getPokemon().getName() + ": " + p1.getPokemon().getHp()
             + "\n" + p2.getName() + "`s "  + p2.getPokemon().getName() + ": " + p2.getPokemon().getHp());

            // Setar qual pokemon vai agir primeiro e ataque de cada um em ordem
            if (p1.getPokemon().getSpeed() >= p2.getPokemon().getSpeed()) {
                primeiro = p1;
                segundo = p2;

                useMove(primeiro, segundo);            
                
                // Segundo só ataca se sobreviver o do primeiro
                if (segundo.getPokemon().getHp() != 0)
                    useMove(segundo, primeiro);

            } else {
                primeiro = p2;
                segundo = p1;

                useMove(primeiro, segundo);           
                
                // Segundo só ataca se sobreviver o do primeiro
                if (segundo.getPokemon().getHp() != 0)
                    useMove(segundo, primeiro);
            }

            i++;

            if (p1.getPokemon().getHp() == 0 || p2.getPokemon().getHp() == 0)
                stop = true;
        }
    }

    public void useMove(Trainer atacante, Trainer alvo) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1; // D100
        int i = rn.nextInt(3); // ataque aleatório
        System.out.println("\n" + atacante + "`s " + atacante.getPokemon() + " uses " + atacante.getPokemon().getMoves(i).getName() + "!");
        
        // Checagem se acertou o ataque
        if (ataque <= atacante.getPokemon().getAccuracy())
            takeMove(atacante.getPokemon().getMoves(i), atacante.getPokemon(), alvo.getPokemon());
        else
            System.out.println(atacante + "`s " + atacante.getPokemon()  + " missed.");
    }

    // Receber ataque
    public void takeMove(Moves move, Pokemon atacante, Pokemon alvo) {
        String category = move.getCategory();
        String status = move.getName();
        double dano = 0;

        // Checagem do type do ataque antes de receber
        switch (category) {
        case "PHYSICAL":
            dano = (int) ((move.getPower() * atacante.getAttack() / alvo.getDefense()) / 5) + 2;
            break;
        case "SPECIAL":
            dano = (int) ((move.getPower() * atacante.getSpattack() / alvo.getSpdefense()) / 5) + 2;
            break;
        
        // Type de ataques de status
        case "STATUS":
            switch (status) {
            case "GROWL": 
                if (move.getPower() != 3) {
                    alvo.setAttack(-5);
                    move.setPower(1);
                    System.out.println(alvo.getName() + "`s Attack fell.");
                } else 
                    System.out.println(alvo.getName() + "`s Attack can`t be reduced any further.");
                break;
            case "SMOKESCREEN":
                if (move.getPower() != 3) {
                    alvo.setAccuracy(-5);
                    move.setPower(1);
                    System.out.println(alvo.getName() + "`s Accuracy fell.");
                } else 
                    System.out.println(alvo.getName() + "`s Accuracy can`t be reduced any further.");
                break;
            case "TAIL WHIP":
                if (move.getPower() != 3) {
                    alvo.setDefense(-5);
                    move.setPower(1);
                    System.out.println(alvo.getName() + "`s Defense fell.");
                } else 
                    System.out.println(alvo.getName() + "`s Defense can`t be reduced any further.");
                break;
            case "GROWTH":
                if (move.getPower() != 2) {
                    atacante.setAttack(5);
                    atacante.setSpAttack(2);
                    move.setPower(1);
                    System.out.println(atacante.getName() + "`s Attack and Sp. Attack rose.");
                } else 
                    System.out.println(atacante.getName() + "`s Attack and Sp. Attack can`t go any further.");
                break;
            case "WITHDRAW":
                if (move.getPower() != 3) {
                    atacante.setDefense(5);
                    move.setPower(1);
                    System.out.println(atacante.getName() + "`s Defense rose.");
                } else 
                    System.out.println(atacante.getName() + "`s Defense can`t go any further.");
                break;
            }
        }

        // Receber o dano depois da checagem de categoria
        if (category != "STATUS") {
            // if (type == this.desvantagem) {
            //     dano = dano * 1.5;
            //     System.out.println("It was super effective!");
            // } else if (type == this.vantagem) {
            //     dano = dano * 0.5;
            //     System.out.println("It was not very effective...");
            // } else {
            //     System.out.println("It was effective.");
            // }
            
            if (alvo.getHp() - dano < 0)
                alvo.setHp(0);
            else
                alvo.setHp(alvo.getHp() - dano);
        }
    }

    public int typeCheck(int dano, Moves move, Pokemon atacante, Pokemon alvo) {

        

        return dano;
    }
}
