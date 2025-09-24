package br.com.andrebastos.javamon_online.battle;

import java.util.List;
import java.util.ArrayList;

public class BattleLogDto {

    private String winnerName;
    private String loserName;
    private List<String> log;

    public BattleLogDto() {
        this.log = new ArrayList<>();
    }

    // Getters e Setters para todos os campos
    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }

    public void add(String s) {
        this.log.add(s);
    }
}