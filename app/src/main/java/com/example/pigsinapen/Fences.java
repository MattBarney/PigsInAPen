package com.example.pigsinapen;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Fences class within PigsInAPen
 * The constuctor will create a button with given attributed : int row, int col, boolean vertical,
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
  private GameDisplay currentDisplay;

  /**
   * @param row sets what row the fence will be in
   * @param col sets what col the fence be will in
   * @param vertical set line to either vertical or horizontal
   * @param context sets which activity the fence is being created
   */
  public Fences(int row, int col, boolean vertical, Context context, GameDisplay gameDisplay) {
    float transparency = 0.35f;
    this.row = row;
    this.col = col;
    this.vertical = vertical;
    this.context = context;
    this.currentDisplay = gameDisplay;

    fenceButton = new Button(context);
    fenceButton.setBackgroundColor(Color.LTGRAY);
    fenceButton.setLayoutParams(new LinearLayout.LayoutParams(15, 120));
    fenceButton.setAlpha(transparency);
    fenceButton.setOnClickListener(getOnClickDoSomething(fenceButton));

    if (vertical == false) { // use horizontal visual
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
      }//if
    }////setVisible
  public boolean isVertical(){
    return vertical;
    }//isVertical

  public void checkBox(int row, int col, boolean vert){
    fenceButton.setBackgroundColor(Color.BLACK);
    fenceButton.setAlpha(1);
  }
  View.OnClickListener getOnClickDoSomething(final Button button)  {
    return new View.OnClickListener() {
      public void onClick(View v) {
        currentDisplay.playerTurn(row, col, vertical);
      }
    };
  }
} // Class Fences
