package com.example.pigsinapen;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Fences class within PigsInAPen The constructor will create a button with given attributed : int
 * row, int col, boolean vertical, Context context
 *
 * <p>Getters = getButton, getRow, getCol, isVisible Setters = setVisible
 */
public class Fences {
  public Boolean visible = false;
  private Boolean horizontal = true;
  private Integer row;
  private Integer col;
  private Context context;
  private Button fenceButton;
  private GameDisplay currentDisplay;

  /* Alvee addedd */
  private Boolean buttonClicked;

  /**
   * @param row sets what row the fence will be in
   * @param col sets what col the fence be will in
   * @param horizontal set line to either vertical or horizontal
   * @param context sets which activity the fence is being created
   */
  public Fences(int row, int col, boolean horizontal, Context context, GameDisplay gameDisplay) {
    this.row = row;
    this.col = col;
    this.horizontal = horizontal;
    this.context = context;
    this.currentDisplay = gameDisplay;
    setFenceCharacteristics(horizontal);
  } // Fences

  /**
   * Adds characteristics to the fence that is being created
   *
   * @param horizontal is the fence horizontal
   */
  void setFenceCharacteristics(boolean horizontal) {
    buttonClicked = false;
    fenceButton = new Button(context);
    fenceButton.setBackgroundColor(Color.LTGRAY);
    fenceButton.setLayoutParams(new LinearLayout.LayoutParams(23, 120));
    // LinearLayout automatically rotates the button to be veritcal
    fenceButton.setAlpha(0.80f);
    fenceButton.setOnClickListener(getOnClickDoSomething(fenceButton));

    if (horizontal) {
      fenceButton.setRotation(90);
    } // if
  } // setFenceCharacteristics
  /** @return the button */
  public View getButton() {
    return fenceButton;
  } // getButton

  /** @return what Row the fence is in */
  public int getRow() {
    return row;
  } // getRow

  /** @return what Col the fence is in */
  public int getCol() {
    return col;
  } // getRow

  /** @return if the fence is visible or not (has it been tapped yet) */
  public boolean isVisible() {
    return visible;
  } // isVisible

  /**
   * Changes the color of the fence to a desired color
   *
   * @param color color to change fence too
   */
  public void changeColor(int color) {
    fenceButton.setBackgroundColor(color);
  } // changeColor

  /**
   * Will run playerturn using the dimensions of the fence that is clicked, will also make the
   * button uninteractable
   *
   * @param button the fence that is being clicked
   */
  View.OnClickListener getOnClickDoSomething(final Button button) {
    return new View.OnClickListener() {
      public void onClick(View v) {
        currentDisplay.playerTurn(row, col, horizontal);
        fenceButton.setBackgroundColor(Color.RED);
        fenceButton.setEnabled(false);
        setButtonClicked(true);
        currentDisplay.runTurn(row, col, horizontal);
        //fenceButton.setBackgroundColor(Color.RED);
        //currentDisplay.displayWinner();
      } // onClick
    };
  } // getOnClickDoSomething

  /**
   * @since 2019-11-11 To implement GameBoard class, I just added few methods, and an instance
   *     variable.
   *     <p>Alvee added these methods.
   */

  /**
   * Checks the button is vertical or horizontal
   *
   * @param fenceButton Button
   * @return boolean true - vertical , false - horizontal
   */
  public boolean isVertical(Button fenceButton) {
    return horizontal;
    // return fenceButton.getRotation() == 0;
  } // isVertical

  /**
   * Sets the Button value true when it is clicked
   *
   * @param buttonClicked Button
   */
  public void setButtonClicked(Boolean buttonClicked) {
    this.buttonClicked = buttonClicked;
  } // setButtonClicked

  /**
   * Checks if the Button is clicked or not clicked
   *
   * @return boolean true - clicked, false - not clicked
   */
  public boolean isButtonClicked() {
    return buttonClicked;
  } // isButtonClicked

  /**
   * Returns the Button object
   *
   * @return fenceButton Button
   */
  public Button getFenceButton() {
    return fenceButton;
  } // getFenceButton
} // Class Fences
