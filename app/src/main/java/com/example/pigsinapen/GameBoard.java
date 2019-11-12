package com.example.pigsinapen;

import android.content.Context;


public class GameBoard {
  private Fences [][] verticalFences;
  private Fences [][] horizontalFences;
  private int row;
  private int col;
  
  int maxScore;
  
  
  public GameBoard(int row, int col, Context context){
    this.row = row;
    this.col = col;
    this.verticalFences = new Fences[row - 1][col];
    this.horizontalFences = new Fences[row][col - 1];
    
    
    for (int i = 0; i < row-1; i++){ // row
      for (int j = 0; j < col; j++) { // col
        verticalFences[i][j] = new Fences(i, j, true, context);
      }
    }
    //
    for (int i = 0; i < row; i++){
      for (int j = 0; j < col - 1; j++){
        horizontalFences[i][j] = new Fences(i, j, false, context);
      }
    }
  }
  
  
  

}
