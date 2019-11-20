package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameDisplay extends AppCompatActivity implements View.OnClickListener {

  LinearLayout game_board_layout;

  Player player1, player2;
  GameBoard gameBoard;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);

    player1 = new Player("Alvee", 3, true);
    player2 = new Player("Jared", 2, false);

    Button[][] buttons = new Button[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        String buttonID = "button_" + i + j;
        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        buttons[i][j] = findViewById(resID);
        buttons[i][j].setOnClickListener(this);
      }
    }
  }

  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu

  @Override
  public void onClick(View v) {}

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
  public void playerTurn(int row, int col, boolean horizontal) {
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    Integer closedBoxes = gameBoard.checkBoxes(row, col, horizontal);
    if (closedBoxes > 0) currentPlayer.addToScore(closedBoxes);
    else currentPlayer = getCurrentPlayer();
    otherPlayer = getOtherPlayer();
    checkGameEnd();
  }

  Player getCurrentPlayer() {
    if (player1.checkCurrentPlayer()) return player1;
    else return player2;
  }

  Player getOtherPlayer() {
    if (player1.checkCurrentPlayer()) return player2;
    else return player1;
  }

  void checkGameEnd() {
    Integer currentScore = player1.getScore() + player2.getScore();
    if (currentScore == gameBoard.getMaxScore()) displayWinner();
  }

  void displayWinner() {
    if (player1.getScore() > player2.getScore())
      Toast.makeText(GameDisplay.this, player1.getName() + "wins", Toast.LENGTH_SHORT).show();
    else
      Toast.makeText(GameDisplay.this, player2.getName() + "wins", Toast.LENGTH_SHORT).show();
    //displayWinner
  }

  // ------CALL QUIT and REPLAY--------
  void quit(){

  }

  void replay(){

  }
}
