package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameDisplay extends AppCompatActivity implements View.OnClickListener {

  LinearLayout game_board_layout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);
    //  private Button[][] buttons = new Button[4][4];
    //  for (int i = 0; i < 4; i++){
    //    for (int j = 0; j < 4; j++){
    //      String buttonID = "button_" + i + j;
    //      int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
    //      buttons[i][j] = findViewById(resID);
    //      buttons[i][j].setOnClickListener(this);
    //    }
    //   }
  }


  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu


  @Override
  public void onClick(View v) {
//    if(!((Button)v.)getText().toString().equals("")){
//      return;
//    }
  }

  // public void setFences(Integer height, Integer width){
  //   Button buttonReset = findViewById(R.id.)
  // -----------set horizontal fences------------------
  //   for (int i = 0; i <= height; i++){
  //    for (int j = ; j <= width){
  //
  //    }
  //   }
  //
  // ------------set vertical fences--------------------
  //  for (int i = 0; i <= height-1; i++){
  //    for (int j = 0; j <= width; j++){
  //
  //    }
  //
  //  }
  // }
  // ------------game display----------------
  // public void playerTurn(int row, int col, boolean horizontal){
  //  currentPlayer = getCurrentPlayer();
  //  otherPlayer = getOtherPlayer();
  //  Integer closedBoxes = GameBoard.checkBoxes(row, col, horizontal)
  //  if (closedBoxes > 0)
  //    currentPlayer.addToScore(closedBoxes);
  //  else
  //    currentPlayer.setCurrentPlayer(false);
  //    otherPlayer.setOtherPlayer(true);
  //  checkGameEnd();
  // }
  // player getCurrentPlayer()
  //  if(player1.checkCurrentPlayer() == true)
  //    return player1;
  //  else
  //    return player2;
  // }
  // player getOtherPlayer(){
  //  if(player1.checkCurrentPlayer() == true)
  //    return player2;
  //  else
  //    return player1;
  // }
  // void checkGameEnd(){
  //  currentScore = player1.getScore + player2.getScore;
  //  if (currentScore == GameBoard.getMaxScore())
  //    displayWinner();
  // }
  // void displayWinner(){
  // if (player1.getScore > player2.getScore)
  //  Toast.makeText(context: this, + player1.getName() + "wins", Toast.LENGTH_SHORT).show();
  // else
  //  Toast.makeText(context: this, + player2.getName() + "wins", Toast.LENGTH_SHORT).show();
  // display
  // ------CALL QUIT and REPLAY--------
}
