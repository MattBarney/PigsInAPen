package com.example.pigsinapen;

public class Player {
    private String name;
    private Integer score;
    private boolean currentPlayer;

    public Player(String name, boolean currentPlayer){
        this.name = name;
        this.score = 0;
        this.currentPlayer = currentPlayer;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public boolean checkCurrentPlayer() {
        return currentPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToScore(Integer score) {
        this.score += score;
    }


        //Button[] button = new button [...];
        //if (currentPlayer == ?)
        //    button[i].setBackgroundColor(Color.BLUE);
        //else
        //    button[i].setBackgroundColor(Color.GREEN);
        //OR
        //colset name = bool [with (new_false, new_true)];
}
