package com.example.pigsinapen;

import android.content.Context;
import android.view.View;
import android.widget.Button;


public class GameBoard {
  private Fences [][] verticalFences;
  private Fences [][] horizontalFences;
  private int row;
  private int col;
  
  private int maxScore;
  
  
  
  // constructor
  public GameBoard(int rowInput, int colInput, Context context){
    this.row = rowInput;
    this.col = colInput;
    this.verticalFences = new Fences[row - 1][col];
    this.horizontalFences = new Fences[row][col - 1];
    
    // vertical fences
    for (int i = 0; i < row-1; i++){ // row
      for (int j = 0; j < col; j++) { // col
        verticalFences[i][j] = new Fences(i, j, true, context);
        verticalFences[i][j].getButton().setId((col) * i + j);
        System.out.println("vertical"+verticalFences[i][j].getButton().getId()+"\n");
      }
    }
    // horizontal fences
    for (int i = 0; i < row; i++){
      for (int j = 0; j < col - 1; j++){
        horizontalFences[i][j] = new Fences(i, j, false, context);
        horizontalFences[i][j].getButton().setId((col - 1) * i + j);
        System.out.println("horizontal"+horizontalFences[i][j].getButton().getId()+"\n");
      }
    }
  }
  
  
  public boolean checkBox(int x, int y, View view){
    Button fence_Button = (Button) view;
    
    if (fence_Button.getRotation() == 90){
      System.out.println("\n\n 0 = horizontal\n\n"+fence_Button.getId());
      if (horizontalFences[1][1].isButtonClicked() == true){
        System.out.println("\n\nHey this is the 1 1 place button clicked from main activity \n\n"+horizontalFences[1][1].getButton().getId()+"\n\n");
      }
    }
    else System.out.println("\n\n 90 = \n\n");
    
    return true;
  }
  
  
  
  
  
  
  
  
  
  
  
  public Fences [][] getVerticalFences(){
    return verticalFences;
  }
  
  public Fences [][] getHorizontalFences(){
    return horizontalFences;
  }
  
  public int getMaxScore(){
    return maxScore;
  }
  
  
  
  
  
  
  
}
