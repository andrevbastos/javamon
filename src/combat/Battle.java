package combat;

import visuals.screens.*;

public class Battle{
    private Pokemon p1;
    private Pokemon p2;
    private Combat c;
    private int rounds;

    public Battle(Combat c) {
        this.c = c;
    }

    public void setP1(Pokemon p1) {
        this.p1 = p1;
    }

    public void setP2(Pokemon p2) {
        this.p2 = p2;
    }

    public void run() {
        Pokemon first;
        Pokemon second;
        Pokemon winner = null;
        boolean stop = false;
        rounds = 0;

        c.addToHistory("(" + p1.getName() + " x " + p2.getName() + ":");

        while (!stop) {

            first = (p1.getSpeed() >= p2.getSpeed()) ? p1 : p2;
            second = (first == p1) ? p2 : p1;

            first.useMove(second, c);
            
            if (second.getHp() != 0) {
                second.useMove(first, c);
            }

            winner = (p1.getHp() == 0) ? p2 : (p2.getHp() == 0) ? p1 : null;
            stop = (winner != null);
            rounds++;

        }

        p1.heal();
        p2.heal();

        winner.addVictory();
        winner.setRounds(rounds);

        c.addToHistory(" " + rounds + ");\n");
        c.addWinner(winner.getName() + "; ");
    }
    
}
