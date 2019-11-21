package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameDisplay extends AppCompatActivity implements View.OnClickListener {
  LinearLayout game_board_layout;
  Player player1, player2;
  GameBoard gameBoard;
  Fences fence;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);
    gameBoard = new GameBoard(6, 6, GameDisplay.this, this);
    createHorizontalFencesandDots();
    createVerticalFences();
    player1 = new Player("Alvee", 3, true);
    player2 = new Player("Jared", 2, false);

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
    System.out.println(row + " " + col + " " + horizontal);
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    Integer closedBoxes = gameBoard.checkBoxes(row, col, horizontal);
    if (closedBoxes > 0) currentPlayer.addToScore(closedBoxes);
    else currentPlayer = getCurrentPlayer();
    otherPlayer = getOtherPlayer();
    checkGameEnd();
    System.out.println(row + " " + col + " " + horizontal);
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
    else Toast.makeText(GameDisplay.this, player2.getName() + "wins", Toast.LENGTH_SHORT).show();
    // displayWinner
  }

  // ------CALL QUIT and REPLAY--------
  void quit() {}

  void replay() {}

  void createHorizontalFencesandDots() {

    int putFenceX = 0;
    int putFenceY = 0;
    int putButtonX = 0;
    int putButtonY = 44;

    for (int j = 0; j < 6; j += 1) { //total amount of rows
      putFenceX = 95;// + 150;
      putFenceY += 183;
      putButtonX = 0;// + 150;
      putButtonY += 183;
      for (int i = 0; i < 5; i += 1) { //num of hor lines per row
        ConstraintLayout layout = findViewById(R.id.boardGameConstraint);
        Fences fence = new Fences(j, i, true, GameDisplay.this, this);
        fence.getButton().setX(putFenceX);
        fence.getButton().setY(putFenceY);
        layout.addView(fence.getButton());
        putFenceX += 170;
      } // forHorfences
      for (int i = 0; i < 6; i += 1) {//dots per row
        ConstraintLayout layout = findViewById(R.id.boardGameConstraint);
        ImageView dot = new ImageView(this);
        dot.setX(putButtonX);
        dot.setY(putButtonY);
        dot.setBackgroundResource(R.drawable.dotnew);
        layout.addView(dot);
        android.view.ViewGroup.LayoutParams layoutParams = dot.getLayoutParams();
        layoutParams.width = 35;
        layoutParams.height = 35;
        putButtonX += 170;
        dot.setLayoutParams(layoutParams);
      } // fordot
    } // outerfor
  }

  void createVerticalFences() {
    int putFenceXVert = 0;
    int putFenceYVert = 89;

    for (int j = 0; j < 5; j += 1) { //num rows
      putFenceXVert = 10; //+ 150;
      putFenceYVert += 183;
      for (int i = 0; i < 6; i += 1) { //lines per row
        ConstraintLayout layout = findViewById(R.id.boardGameConstraint);
        Fences fence = new Fences(j, i, false, GameDisplay.this, this);
        fence.getButton().setX(putFenceXVert);
        fence.getButton().setY(putFenceYVert);
        layout.addView(fence.getButton());
        putFenceXVert += 170;
      } // forfences
    } // for
  }
}
