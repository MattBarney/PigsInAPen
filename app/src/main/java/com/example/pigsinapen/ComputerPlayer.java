/**
 * ComputerPlayer.java
 *
 * <p>Represents a computer player. Contains methods for playing Pigs in a Pen without the input
 * from a user.
 *
 * <p>Matt Barney 1560206 Nov 25, 2019 Unless otherwise specified, all code was written by Matt.
 *
 * <p>Methods:
 *    - turn(GameBoard board)
 *        Runs a turn for the computer player.
 *    - chooseFence(GameBoard board)
 *        Picks an unclicked fence.
 *    - chooseHorizontalFence(GameBoard board, List<Fences>unclickedHorizontalFences)
 *        Selects an unclicked horizontal fence.
 *    - chooseVerticalFence(GameBoardboard, List<Fences> unclickedVerticalFences)
 *        Selects an unclicked vertical fence.
 *    - findUnclickedHorizontalFences(GameBoard board)
 *        Creates a list of the unclicked horizontal fences.
 *    - findUnclickedVerticalFences(GameBoard board)
 *        Creates a list of the unclicked vertical fences.
 *    - checkForScore(GameBoard board)
 *        Checks for a fence of any orientation that would result in a score.
 *    - checkFences(Integer width, Integer height, Boolean horizontal, GameBoard board)
 *        Looks for a fence of a certain orientation that would result in a score.
 */
package com.example.pigsinapen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

  ComputerPlayer(String name, Integer color, boolean currentPlayer) {
    super(name, color, currentPlayer);
  } // constructor

  /**
   * Runs a turn for the computer player.
   *
   * <p>First checks for a fence that would result in a score, if none are found randomly choose any
   * unclicked fence.
   *
   * @param board The game board currently being played on.
   * @return True if the player scores, false otherwise.
   */
  public boolean turn(GameBoard board) {
    if (checkForScore(board)) {
      return true;
    } else {
      chooseFence(board);
      return false;
    }
  }

  /**
   * Picks an unclicked fence.
   *
   * <p>This method will randomly choose between a horizontal or a vertical fence. If there are no
   * unlicked fences of the chosen orientation left it will choose a fence from the other
   * orientation.
   *
   * <p>This method was written with help from Alvee Akash.
   *
   * @param board The game board currently being played on.
   */
  private void chooseFence(GameBoard board) {
    Random generator = new Random();
    List<Fences> unclickedHorizontalFences = findUnclickedHorizontalFences(board);
    List<Fences> unclickedVerticalFences = findUnclickedVerticalFences(board);

    // Chooses either 0 or 1. Zero represents a horizontal fence and one
    // represents a vertical fence.
    Integer choice = generator.nextInt(2);

    // First try to pick a fence of the chosen orientation, if there are no unclicked fences
    // left in that orientation choose the other one. We don't need to worry about both being
    // empty because at that point the game will bo over.
    if (choice == 0) {
      if (!unclickedHorizontalFences.isEmpty()) {
        chooseHorizontalFence(board, unclickedHorizontalFences);
      } else { // No unclicked horizontal fences.
        chooseVerticalFence(board, unclickedVerticalFences);
      }
    } else { // choice == 1
      if (!unclickedVerticalFences.isEmpty()) {
        chooseVerticalFence(board, unclickedVerticalFences);
      } else { // No unclicked vertical fences.
        chooseHorizontalFence(board, unclickedHorizontalFences);
      }
    }
  }

  /**
   * Selects an unclicked horizontal fence.
   *
   * <p>Randomly chooses an unclicked horizontal fence and chooses it as this player's turn.
   *
   * @param board The game board currently be played on.
   * @param unclickedHorizontalFences Collection of unclicked horizontal fences.
   */
  private void chooseHorizontalFence(GameBoard board, List<Fences> unclickedHorizontalFences) {
    Random generator = new Random();

    Fences chosenFence =
        unclickedHorizontalFences.get(generator.nextInt(unclickedHorizontalFences.size()));

    chosenFence.setButtonClicked(true);
    chosenFence.changeColor(getColor());
    chosenFence.getButton().setEnabled(false);

  }

  /**
   * Selects an unclicked vertical fence.
   *
   * <p>Randomly chooses an unclicked vertical fence and chooses it as this player's turn.
   *
   * @param board The game board currently be played on.
   * @param unclickedVerticalFences Collection of unclicked vertical fences.
   */
  private void chooseVerticalFence(GameBoard board, List<Fences> unclickedVerticalFences) {
    Random generator = new Random();

    Fences chosenFence =
        unclickedVerticalFences.get(generator.nextInt(unclickedVerticalFences.size()));

    chosenFence.setButtonClicked(true);
    chosenFence.changeColor(getColor());
    chosenFence.getButton().setEnabled(false);

  }

  /**
   * Creates a list of the unclicked horizontal fences.
   *
   * <p>Iterates through the horizontal fences on the board and collects the ones that have not been
   * clicked yet in a list.
   *
   * @param board The game board currently being played on.
   * @return An ArrayList containing the unclicked horizontal fences.
   */
  private List<Fences> findUnclickedHorizontalFences(GameBoard board) {
    List<Fences> unclickedHorizontalFences = new ArrayList<>();

    for (int row = 0; row < board.getHeight(); row++) {
      for (int col = 0; col < board.getWidth() - 1; col++) {
        Fences fenceBeingChecked = board.getOneFence(row, col, true);
        if (!fenceBeingChecked.isButtonClicked()) {
          unclickedHorizontalFences.add(fenceBeingChecked);
        }
      }
    }

    return unclickedHorizontalFences;
  }

  /**
   * Creates a list of the unclicked vertical fences.
   *
   * <p>Iterates through the vertical fences on the board and collects the ones that have not been
   * clicked yet in a list.
   *
   * @param board The game board currently being played on.
   * @return An ArrayList containing the unclicked vertical fences.
   */
  private List<Fences> findUnclickedVerticalFences(GameBoard board) {
    List<Fences> unclickedVerticalFences = new ArrayList<>();

    for (int row = 0; row < board.getHeight() - 1; row++) {
      for (int col = 0; col < board.getWidth(); col++) {
        Fences fenceBeingChecked = board.getOneFence(row, col, false);
        if (!fenceBeingChecked.isButtonClicked()) {
          unclickedVerticalFences.add(fenceBeingChecked);
        }
      }
    }

    return unclickedVerticalFences;
  }

  /**
   * Checks for a fence of any orientation that would result in a score.
   *
   * @param board The game board currently being played on.
   * @return True if a fence resulting in a score was found, false otherwise.
   */
  private Boolean checkForScore(GameBoard board) {
    // Check horizontal fences.
    // We have to subtract one from the width because there is one fence for every two dots, so
    // there is one less fence then there are dots in each row.
    if (checkFences(board.getWidth() - 1, board.getHeight(), true, board)) {
      return true;

      // Check vertical fences.
      // Same idea as above, there is one less fence than there are dots in each column.
    } else if (checkFences(board.getWidth(), board.getHeight() - 1, false, board)) {
      return true;

    } else { // No fence resulting in a score was found.
      return false;
    }
  }

  /**
   * Looks for a fence of a certain orientation that would result in a score.
   *
   * <p>Goes through the the specified fence array and checks to see if picking a fence would result
   * in gaining a point. If a fence results in a score, that fence will be made unclickable and have
   * its colour changed to this player's colour
   *
   * @param width The number of rows present in the specified fence array.
   * @param height The number of columns present in the specified fence array.
   * @param horizontal Specifies which fence array to check. If this value is true check the
   *     horizontal fence array, otherwise check the vertical fence array.
   * @param board The game board currently being played on.
   * @return True if a fence results in a score, false otherwise.
   */
  private Boolean checkFences(Integer width, Integer height, Boolean horizontal, GameBoard board) {
    Integer boxesClosed;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Fences chosenFence = board.getOneFence(i, j, horizontal);
        boxesClosed = board.checkBoxes(i, j, horizontal);
        if (boxesClosed > 0 && !(chosenFence.isButtonClicked())) {
          chosenFence.setButtonClicked(true);
          chosenFence.changeColor(getColor());
          chosenFence.getButton().setEnabled(false);
          this.addToScore(boxesClosed);
          return true;
        }
      }
    }

    return false;
  }
}
