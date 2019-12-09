package com.example.pigsinapen;

import android.media.MediaPlayer;

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
    private Sound sound;
  /**
   *
   * @param name player's name
   * @param color the color that the fence changes to
   * @param currentPlayer the player whose current turn it is
   */

    public Player(String name, Integer color, Boolean currentPlayer, Sound sound){
        this.name = name;
        this.color = color;
        this.score = 0;//only made when game starts so doesn't need to be sent/passed as a parameter
        this.currentPlayer = currentPlayer;
        this.sound = sound;
    }

    //getters and setters
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
    } //adds to previously recorded value

    public void setCurrentPlayer(Boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

  /**
   * @param row the Integer value of the fences' row index
   * @param col the Integer value that the fences' column index
   * @param orientation the desired horizontal or vertical orientation of the fences
   * @param board the current board that is being used
   * @return an increment to the score based on if a box is closed
   *
   * The turn() function takes in the index, orientation and the game board that is currently being
   * played with in order to highlight the player’s selection of the fence on the board and to change
   * its color accordingly. After this is done, it checks if a box has been closed and indicates that
   * a point is to be added to the scoreboard of that respective player.
   */
  Boolean turn(Integer row, Integer col, Boolean orientation, GameBoard board) {
        Fences chosenFence = board.getOneFence(row, col, orientation);
        chosenFence.changeColor(getColor());
        Integer boxesClosed = board.checkBoxes(row, col, orientation);
        if (boxesClosed > 0){
            sound.pointScore();
            addToScore(boxesClosed);
            return true;
            }
        else
            return false;
    }

}

