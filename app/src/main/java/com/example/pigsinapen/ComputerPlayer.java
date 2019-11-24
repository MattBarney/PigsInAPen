/** */
package com.example.pigsinapen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

  ComputerPlayer(String name, boolean currentPlayer) {
    super(name, currentPlayer);
  } // constructor

  /**
   * Returns the index and orientation of a fence in an array.
   *
   * <p>Generates row and column indexes for a horizontal fence until an unclicked one is found,
   * once the fence is found store the row and column index, as well as the orientation in an
   * Integer array.
   *
   * @param board The game board currently being played on.
   * @return An integer array containing the row index, column index, and orientation. Orientation
   *     is represented as a 0 for horizontal.
   */
  private Integer[] generateHorizontalFenceIndex(GameBoard board) {
    Random generator = new Random();

    // Continuously generate the index for a horizontal fence until an unclicked one is found.
    // This method does not get called unless there is still unclicked horizontal fences,
    // so there is no need to check that condition here.
    do {
      Integer rowIndex = generator.nextInt(board.getWidth() - 1);
      Integer colIndex = generator.nextInt(board.getHeight());
    } while (board.getHorizontalFence(rowIndex, colIndex).isButtonClicked());

    return new Integer[] {rowIndex, colIndex, 0};
  }

  /**
   * Selects an unclicked horizontal fence.
   *
   * <p>Randomly chooses an unclicked horizontal fence and chooses it as this player's turn.
   *
   * @param board The game board currently be played on.
   */
  private void chooseHorizontalFence(GameBoard board) {
    Random generator = new Random();
    List<Fences> untappedHorizontalFences = findUntappedHorizontalFences(board);

    Fences chosenFence =
        untappedHorizontalFences.get(generator.nextInt(untappedHorizontalFences.size()));

    // Set the chosen fence to this player's colour
    // Make the fence unclickable

  }

  /**
   * Selects an unclicked vertical fence.
   *
   * <p>Randomly chooses an unclicked vertical fence and chooses it as this player's turn.
   *
   * @param board The game board currently be played on.
   */
  private void chooseVerticalFence(GameBoard board) {
    Random generator = new Random();
    List<Fences> untappedVerticalFences = findUntappedVerticalFences(board);

    Fences chosenFence =
        untappedVerticalFences.get(generator.nextInt(untappedVerticalFences.size()));

    // Set the chosen fence to this player's colour
    // Make the fence unclickable

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
  private List<Fences> findUntappedHorizontalFences(GameBoard board) {
    List<Fences> untappedHorizontalFences = new ArrayList<>();

    for (int row = 0; row < board.getWidth() - 1; row++) {
      for (int col = 0; col < board.getHeight; col++) {
        Fences fenceBeingChecked = board.getHorizontalFence(row, col);
        if (fenceBeingChecked.isButtonClicked()) {
          untappedHorizontalFences.add(fenceBeingChecked);
        }
      }
    }

    return untappedHorizontalFences;
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
  private List<Fences> findUntappedVerticalFences(GameBoard board) {
    List<Fences> untappedVerticalFences = new ArrayList<>();

    for (int row = 0; row < board.getWidth(); row++) {
      for (int col = 0; col < board.getHeight - 1; col++) {
        Fences fenceBeingChecked = board.getVerticalFence(row, col);
        if (fenceBeingChecked.isButtonClicked()) {
          untappedVerticalFences.add(fenceBeingChecked);
        }
      }
    }

    return untappedVerticalFences;
  }

  /**
   * Checks for a fence that would result in a score.
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
