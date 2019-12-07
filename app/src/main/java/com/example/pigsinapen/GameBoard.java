/**
 * GameBoard.java
 *
 * <p>This Class is made of two Fences class and Integer rows, columns.
 *
 * <p>To make fences, One fences class - Horizontal fences has one 2D array, and another fences
 * class - Vertical fences has one 2D array. Rows are defined as height and columns are defined as
 * width. CheckBox method is implemented here.
 *
 * <p>checkboxes method idea is taken from Matt Barney
 *
 * @version 1.0
 * @since 2019-11-11
 */
package com.example.pigsinapen;

import android.content.Context;

public class GameBoard {
  /** verticalFences and horizontalFences are Fences class's object */
  private Fences[][] verticalFences;

  private Fences[][] horizontalFences;

  /**
   * verticalFences and horizontalFences 2D array are made of these height and width of this
   * variables
   */
  private Integer height; // rows

  private Integer width; // cols

  /** max scores of a game is based on given grid size */
  private Integer maxScore;

  /**
   * Sets the display where it is shown in the app. such as this is from Game Display activity
   * class,
   *
   * <p>where all these arrays of fences objects are made.
   */
  private GameDisplay display;

  /**
   * Constructor: Requires two Integer values, one - Vertical, two - Horizontal, Context - where it
   * is creating.
   *
   * @param rowInput Integer value User input of rows
   * @param colInput Integer value User input of columns
   * @param context Context value Sets activity where this constructor is being used
   */
  public GameBoard(Integer rowInput, Integer colInput, Context context, GameDisplay display) {
    this.height = rowInput;
    this.width = colInput;
    this.verticalFences = new Fences[height - 1][width];
    this.horizontalFences = new Fences[height][width - 1];
    this.maxScore = (this.height - 1) * (this.width - 1);
    this.display = display;

    // vertical fences creation
    for (int i = 0; i < height - 1; i++) {
      for (int j = 0; j < width; j++) {
        verticalFences[i][j] = new Fences(i, j, false, context, display);
        verticalFences[i][j].getButton().setId((width) * i + j);
      } // for
    } // for

    // horizontal fences creation
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width - 1; j++) {
        horizontalFences[i][j] = new Fences(i, j, true, context, display);
        horizontalFences[i][j].getButton().setId((width - 1) * i + j);
      } // for
    } // for
  } // GameBoard

  /**
   * Returns a Vertical Fence object based on given row and col input of an array
   *
   * @return verticalFences Fence object
   */
  public Fences getVerticalFences(Integer row, Integer col) {
    return verticalFences[row][col];
  }

  /**
   * Returns a Horizontal Fence object based on given row and col input of an array
   *
   * @return horizontalFences Fence object
   */
  public Fences getHorizontalFences(Integer row, Integer col) {
    return horizontalFences[row][col];
  }

  /**
   * Returns the Fences of the given board indexes such as 3, 3, true --> row = 3, col = 3,
   * horizontal
   *
   * @return Fences object value
   */
  public Fences getOneFence(Integer row, Integer col, Boolean orientation) {
    if (orientation) {
      return horizontalFences[row][col];
    } else return verticalFences[row][col];
  }

  /**
   * Returns the height of the given board size such as 3x3 = 3
   *
   * @return height Integer value
   */
  public Integer getHeight() {
    return height;
  }
  /**
   * Returns the width of the given board size such as 3x3 = 3
   *
   * @return width Integer value
   */
  public Integer getWidth() {
    return width;
  }

  /**
   * Returns the maxScore of the given board size such as 3x3 = 4
   *
   * @return maxScore Integer value
   */
  public Integer getMaxScore() {
    return maxScore;
  }

  /**
   * Returns Integer value if it makes a box on given fences's row and column index
   *
   * @param row Integer Index of rows
   * @param col Integer Index of columns
   * @param horizontal Boolean Checks if the fence is horizontal or vertical
   * @return Integer value
   */
  public Integer checkBoxes(Integer row, Integer col, Boolean horizontal) {
    if (horizontal) return checkHorizontalBoxes(row, col);
    else return checkVerticalBoxes(row, col);
  } // checkBoxes

  /* Two sub methods - checkHorizontalBoxes and checkVerticalBoxes of checkBox method */

  /**
   * Checks the above and below possible boxes of the given horizontal fences
   *
   * @param row Integer value Horizontal fences's row index
   * @param col Integer value Horizontal fences's column index
   * @return closedBoxes Integer value
   */
  private Integer checkHorizontalBoxes(Integer row, Integer col) {
    Integer closedBoxes = 0;
    if (row != height - 1) closedBoxes += checkBoxBelow(row, col);
    if (row != 0) {
      closedBoxes += checkBoxAbove(row, col);
    } // if
    return closedBoxes;
  }

  /* Two sub methods - checkBoxBelow and checkBoxAbove of checkHorizontalBoxes method */

  /**
   * Checks the next below horizontal fences, and left and right vertical fences
   *
   * @param row Integer value Horizontal fences's row index
   * @param col Integer value Horizontal fences's column index
   * @return Integer value
   */
  private Integer checkBoxBelow(Integer row, Integer col) {
    if (!(horizontalFences[row + 1][col].isButtonClicked())) {
      return 0;
    } // if
    else if (!(verticalFences[row][col].isButtonClicked())) {
      return 0;
    } // else if
    else if (!verticalFences[row][col + 1].isButtonClicked()) {
      return 0;
    } // else if
    else return 1;
  }

  /**
   * Checks the next above horizontal fences, and left and right vertical fences
   *
   * @param row Integer value Horizontal fences's row index
   * @param col Integer value Horizontal fences's column index
   * @return Integer value
   */
  private Integer checkBoxAbove(Integer row, Integer col) {
    if (!(horizontalFences[row - 1][col].isButtonClicked())) {
      return 0;
    } // if
    else if (!(verticalFences[row - 1][col].isButtonClicked())) {
      return 0;
    } // else if
    else if (!(verticalFences[row - 1][col + 1].isButtonClicked())) {
      return 0;
    } // else if
    else return 1;
  }

  /**
   * Checks the right and left possible boxes of the given vertical fences
   *
   * @param row Integer value Vertical fences's row index
   * @param col Integer value Vertical fences's column index
   * @return closedBoxes Integer value
   */
  private Integer checkVerticalBoxes(Integer row, Integer col) {
    Integer closedBoxes = 0;
    if (col != 0) {
      closedBoxes += checkBoxLeft(row, col);
    } // if
    if (col != width - 1) {
      closedBoxes += checkBoxesRight(row, col);
    } // if
    return closedBoxes;
  }

  /* Two sub methods - checkBoxLeft and checkBoxesRight of checkVerticalBoxes method */

  /**
   * Checks the next left vertical fences, and above and below horizontal fences
   *
   * @param row Integer value Vertical fences's row index
   * @param col Integer value Vertical fences's column index
   * @return Integer value
   */
  private Integer checkBoxLeft(Integer row, Integer col) {
    if (!(verticalFences[row][col - 1].isButtonClicked())) {
      return 0;
    } // else if
    else if (!(horizontalFences[row][col - 1].isButtonClicked())) {
      return 0;
    } // else if
    else if (!(horizontalFences[row + 1][col - 1].isButtonClicked())) {
      return 0;
    } // else if
    else return 1;
  }

  /**
   * Checks the next right vertical fences, and above and below horizontal fences
   *
   * @param row Integer value Vertical fences's row index
   * @param col Integer value Vertical fences's column index
   * @return Integer value
   */
  private Integer checkBoxesRight(Integer row, Integer col) {
    if (!(verticalFences[row][col + 1].isButtonClicked())) {
      return 0;
    } // if
    else if (!(horizontalFences[row][col].isButtonClicked())) {
      return 0;
    } // else if
    else if (!(horizontalFences[row + 1][col].isButtonClicked())) {
      return 0;
    } // else if
    else return 1;
  }

  /** Makes all fences unclickable by the user. */
  public void makeFencesUnclickable() {
    makeHorizontalFencesUnclickable();
    makeVerticalFencesUnclickable();
  }

  /** Makes all unclicked fences clickable by the user */
  public void makeUnselectedFencesClickable() {
    makeUnclickedHorizontalFencesClickable();
    makeUnclickedVerticalFencesClickable();
  }

  /** Makes all horizontal fences unclickable. */
  private void makeHorizontalFencesUnclickable() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width - 1; j++) {
        Fences currentFence = horizontalFences[i][j];
        currentFence.getButton().setClickable(false);
      }
    }
  }

  /** Makes all vertical fences unclickable. */
  private void makeVerticalFencesUnclickable() {
    for (int i = 0; i < height - 1; i++) {
      for (int j = 0; j < width; j++) {
        Fences currentFence = verticalFences[i][j];
        currentFence.getButton().setClickable(false);
      }
    }
  }

  /** Makes horizontal fences that have not yet been clicked clickable. */
  private void makeUnclickedHorizontalFencesClickable() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width - 1; j++) {
        Fences currentFence = horizontalFences[i][j];
        if (!currentFence.isButtonClicked()) {
          currentFence.getButton().setClickable(true);
        }
      }
    }
  }

  /** Makes vertical fences that have not yet been clicked clickable. */
  private void makeUnclickedVerticalFencesClickable() {
    for (int i = 0; i < height - 1; i++) {
      for (int j = 0; j < width; j++) {
        Fences currentFence = verticalFences[i][j];
        if (!currentFence.isButtonClicked()) {
          currentFence.getButton().setClickable(true);
        }
      }
    }
  }
} // GameBoard
