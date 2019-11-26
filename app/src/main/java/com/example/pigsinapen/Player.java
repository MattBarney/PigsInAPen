package com.example.pigsinapen;

public class Player {
    private String name;
    private Integer score;
    private boolean currentPlayer;

    public Player(String name, boolean currentPlayer){
        this.name = name;
        this.score = 0; //only made when game starts so doesn't need to be sent/passed as a parameter
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

    //add a colour constructor that takes a colour from the resource folder and send it as an int
}

