package com.example.pigsinapen;

/**
 * Siri's Code
 *
 * The Player class is just used to store information regarding the player i.e.,
 * the player name, score, and the color to which the fences change after a player has clicked on it
 *
 * This java class also consists of getters and setters that correspond to the player name, score,
 * color, calculating the score, and determining the current player.
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

    public Player(String name, Integer color, Boolean currentPlayer){
        this.name = name;
        this.color = color;
        this.score = 0;//only made when game starts so doesn't need to be sent/passed as a parameter
        this.currentPlayer = currentPlayer;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getColor(){
    return color;
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
   * @param row the Integer value of the fences' row index
   * @param col the Integer value that the fences' column index
   * @param orientation the desired horizontal or vertical orientation of the fences
   * @param board
   * @return
   *
   * The turn() function takes in the index, orientation and __ of the fences as inputs to
   */
  Boolean turn(Integer row, Integer col, Boolean orientation, GameBoard board) {
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

}

