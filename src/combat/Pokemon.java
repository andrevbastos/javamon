package combat;

import java.util.Random;

public class Pokemon {
    private String name;
    private final String type;
    private String vantagem;
    private String desvantagem;
    private final int hpmax;
    private int hp;
    private int attack;
    private int spattack;
    private int defense;
    private int spdefense;
    private int speed;
    private int accuracy;
    private Moves[] moves;
    private final String sprite;

    public Pokemon(String name, String type, int hpmax, int attack, int spattack, int defense, int spdefense, int speed, int accuracy, Moves[] moves, String sprite) {
        this.name = name; 
        this.type = type;
        // Setar as vantagens e desvantagens conforme o type do pokemon
        switch (type) {
        case "FIRE": 
            this.vantagem = "GRASS";
            this.desvantagem = "WATER";
            break;
        case "WATER":
            this.vantagem = "FIRE";
            this.desvantagem = "GRASS";
            break;
        case "GRASS":
            this.vantagem = "WATER";
            this.desvantagem = "FIRE"; 
        }
        this.hpmax = hpmax;
        this.hp = hpmax;
        this.attack = attack;
        this.spattack = spattack;
        this.defense = defense;
        this.spdefense = spdefense;
        this.speed = speed;
        this.accuracy = accuracy;
        this.moves = moves;
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return name + " [" + type + "] ";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVantagem() {
        return vantagem;
    }

    public String getDesvantagem() {
        return desvantagem;
    }

    public int getHpmax() {
        return hpmax;
    }

    public int getHp() {
        // Vida não é enviada como negativa
        if (this.hp > 0) 
            return hp;
        else
            return 0;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpattack() {
        return spattack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpdefense() {
        return spdefense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Moves getMoves(int i) {
        // Pega o move do pokemon dentro do seu vetor de moves
        return moves[i];
    }    
    
    public String getSprite() {
        return sprite;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int i) {
        this.attack += i;
    }

    public void setSpAttack(int i) {
        this.spattack += i;
    }

    public void setDefense(int i) {
        this.defense += i;
    }

    public void setSpDefense(int i) {
        this.spdefense += i;
    }

    public void setSpeed(int i) {
        this.speed += i;
    }

    public void setAccuracy(int i) {
        this.accuracy += i;
    }

    // Usar ataque com escolha
    public void useMove(Pokemon alvo, int i) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1; // D100
        System.out.println("\n" + this + "uses " + this.getMoves(i).getName() + "!");

        // Checagem se acertou o ataque
        if (ataque <= this.getAccuracy()) 
            alvo.takeMove(this.getMoves(i), this);
        else 
            System.out.println(this.getName() + " missed.");
    }    
    
    // Usar ataque sem escolha
    public void useMove(Pokemon alvo) {
        Random rn = new Random();
        int ataque = rn.nextInt(99) + 1; // D100
        int i = rn.nextInt(3); // ataque aleatório
        System.out.println("\nFoe`s " + this + "uses " + this.getMoves(i).getName() + "!");
        
        // Checagem se acertou o ataque
        if (ataque <= this.getAccuracy())
            alvo.takeMove(this.getMoves(i), this);
        else
            System.out.println(this.getName() + " missed.");
    }

    // Receber ataque
    public void takeMove(Moves move, Pokemon atacante) {
        String type = move.getType();
        String category = move.getCategory();
        String status = move.getName();
        double dano = 0;

        // Checagem do type do ataque antes de receber
        switch (category) {
        case "PHYSICAL":
            dano = (int) ((move.getPower() * atacante.getAttack() / this.getDefense()) / 5) + 2;
            break;
        case "SPECIAL":
            dano = (int) ((move.getPower() * atacante.getSpattack() / this.getSpdefense()) / 5) + 2;
            break;
        
        // Type de ataques de status
        case "STATUS":
            switch (status) {
            case "GROWL": 
                if (move.getPower() != 3) {
                    this.setAttack(-5);
                    move.setPower(1);
                    System.out.println(this.getName() + "`s Attack fell.");
                } else 
                    System.out.println(this.getName() + "`s Attack can`t be reduced any further.");
                break;
            case "SMOKESCREEN":
                if (move.getPower() != 3) {
                    this.setAccuracy(-5);
                    move.setPower(1);
                    System.out.println(this.getName() + "`s Accuracy fell.");
                } else 
                    System.out.println(this.getName() + "`s Accuracy can`t be reduced any further.");
                break;
            case "TAIL WHIP":
                if (move.getPower() != 3) {
                    this.setDefense(-5);
                    move.setPower(1);
                    System.out.println(this.getName() + "`s Defense fell.");
                } else 
                    System.out.println(this.getName() + "`s Defense can`t be reduced any further.");
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
            if (type == this.desvantagem) {
                dano = dano * 1.5;
                System.out.println("It was super effective!");
            } else if (type == this.vantagem) {
                dano = dano * 0.5;
                System.out.println("It was not very effective...");
            } else {
                System.out.println("It was effective.");
            }
            
            if (hp - dano < 0)
                hp = 0;
            else
                hp -= dano;
        }
    }
}
