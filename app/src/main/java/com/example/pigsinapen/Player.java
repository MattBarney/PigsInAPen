package com.example.pigsinapen;


/**
 * Siri's Code
 */
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

    Boolean turn(int row, int col, boolean orientation, GameBoard){
        Fences chosenFence = board.getOnneFence(row, col, orientation);
        chosenFence.setColor(getColor());
        chosenFence.makeUnclickable();
        Integer boxesClosed = board. checkBoxes(row, col, orientation);
        if (boxesClosed > 0){
            addToScore(boxesClosed);
            return true;
            }
        else
            return false;
    }

    //add a colour constructor that takes a colour from the resource folder and send it as an int

}

