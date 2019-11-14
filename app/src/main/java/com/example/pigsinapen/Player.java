package com.example.pigsinapen;

public class Player {
    private String name;
    private Integer score;
    private boolean currentPlayer;

    public Player(String name, Integer score, boolean currentPlayer){
        this.name = name;
        this.score = score;
        this.currentPlayer = currentPlayer;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
        //Button[] button = new button [...];
        //if (currentPlayer == ?)
        //    button[i].setBackgroundColor(Color.BLUE);
        //else
        //    button[i].setBackgroundColor(Color.GREEN);
        //OR
        //colset name = bool [with (new_false, new_true)];
    }
}
