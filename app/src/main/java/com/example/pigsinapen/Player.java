package com.example.pigsinapen;

/**
 * Siri's Code
 * The player class 
 */

public class Player {
    private String name;
    private Integer score;
    private Integer color;
    private Boolean currentPlayer;

  /**
   *
   * @param name
   * @param color
   * @param currentPlayer
   */

    public Player(String name, int color, Boolean currentPlayer){
        this.name = name;
        this.color = color;
        this.score = 0; //only made when game starts so doesn't need to be sent/passed as a parameter
        this.currentPlayer = currentPlayer;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public Boolean checkCurrentPlayer() {
        return currentPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToScore(Integer score) {
        this.score += score;
    }

    public void setCurrentPlayer(Boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


  /**
   *
   * @param row
   * @param col
   * @param orientation
   * @param board
   * @return
   */

    Boolean turn(int row, int col, Boolean orientation, GameBoard board){
        Fences chosenFence = board.getOneFence(row, col, orientation);
        chosenFence.changeColor(getColor());
        Integer boxesClosed = board.checkBoxes(row, col, orientation);
        if (boxesClosed > 0){
            addToScore(boxesClosed);
            return true;
            }
        else
            return false;
    }

    public Integer getColor(){
        return color;
    }

}

