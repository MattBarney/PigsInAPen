package com.example.pigsinapen;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Fences class within PigsInAPen
 * The constructor will create a button with given attributed : int row, int col, boolean vertical,
 * Context context
 *
 * Getters = getButton, getRow, getCol, isVisible
 * Setters = setVisible
 *
 */
public class Fences {
  public boolean visible = false;
  private boolean vertical = false;
  private int row;
  private int col;
  private Context context;
  private Button fenceButton;
  
  /* Alvee addedd */
  private boolean buttonClicked;
  

  /**
   * @param row sets what row the fence will be in
   * @param col sets what col the fence be will in
   * @param vertical set line to either vertical or horizontal
   * @param context sets which activity the fence is being created
   */
  public Fences(int row, int col, boolean vertical, Context context) {
    float transparency = 0.80f; //35
    this.row = row;
    this.col = col;
    this.vertical = vertical;
    this.context = context;
    buttonClicked = false;

    fenceButton = new Button(context);
    fenceButton.setBackgroundColor(Color.LTGRAY);
    fenceButton.setLayoutParams(new LinearLayout.LayoutParams(15, 120));
    fenceButton.setAlpha(transparency);

    if (!vertical) { // use horizontal visual
      fenceButton.setRotation(90);
    } // if
  } // Fences
  /**
   * @return the button
   */
  public View getButton() {
    return fenceButton;
  } // getButton

  /**
   * @return what Row the fence is in
   */
  public int getRow() {
    return row;
  } // getRow

  /**
   * @return what Col the fence is in
   */
  public int getCol() {
    return col;
  } // getRow

  /**
   * @return if the fence is visible or not (has it been tapped yet)
   */
  public boolean isVisible() {
    return visible;
  } // isVisible

  /**
   * @param hasItBeenClicked has the fence been tapped, will make fence invisible
   *                        (for other lines to take its spot)
   */
  public void setVisible(boolean hasItBeenClicked) {
    if(hasItBeenClicked == true){
      visible = false;
      fenceButton.setAlpha(0);
    }//hasItBeenClicked
  } // setVisible
  
  
  /**
   * @author  Alvee Hassan Akash
   * @version 1.0
   * @since   2019-11-11
   * To implement GameBoard class, I just added few methods, and an instance variable.
   */
  
  /**
   * Checks the button is vertical or horizontal
   * @param fenceButton Button
   * @return boolean true - vertical , false - horizontal
   */
  public boolean isVertical(Button fenceButton){
    return fenceButton.getRotation() == 0;
  }
  
  /**
   * Sets the Button value true when it is clicked
   * @param buttonClicked Button
   */
  public void setButtonClicked(boolean buttonClicked) {
    this.buttonClicked = buttonClicked;
  }
  
  /**
   * Checks if the Button is clicked or not clicked
   * @return boolean true - clicked, false - not clicked
   */
  public boolean isButtonClicked() {
    return buttonClicked;
  }
  
  /**
   * Returns the Button object
   * @return fenceButton Button
   */
  public Button getFenceButton() {
    return fenceButton;
  }

} // Class Fences
