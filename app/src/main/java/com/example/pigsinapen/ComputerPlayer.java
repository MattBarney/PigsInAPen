/**
 *
 */

package com.example.pigsinapen;

public class ComputerPlayer extends Player {

  ComputerPlayer(String name, boolean currentPlayer) {
    super(name, currentPlayer);
  } // constructor

  /**
   * Checks for a fence that would result in a score.
   *
   * @param board The game board currently being played on.
   * @return True if a fence resulting in a score was found, false otherwise.
   */
  private Boolean checkForScore(GameBoard board) {
    //Check horizontal fences.
    //We have to subtract one from the width because there is one fence for every two dots, so
    //there is one less fence then there are dots in each row.
    if (checkFences(board.getWidth() - 1, board.getHeight(), true, board)) {
      return true;

    //Check vertical fences.
    //Same idea as above, there is one less fence than there are dots in each column.
    } else if (checkFences(board.getWidth(), board.getHeight() - 1, false, board)) {
      return true;

    } else { //No fence resulting in a score was found.
      return false;
    }
  }

  /**
   * Looks for a fence that would result in a score.
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

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        boxesClosed = board.checkBoxes(i, j, true);
        if (boxesClosed > 0) {
          // Set the button chosen to the 2nd player colour
          // Make the button un-clickable
          this.addToScore(boxesClosed);
          return true;
        }
      }
    }

    return false;
  }
}
