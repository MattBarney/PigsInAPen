package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameDisplay extends AppCompatActivity {


  Player player1, player2;
  GameBoard gameBoard;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);
    gameBoard = new GameBoard(6,6,GameDisplay.this, this);
    createHorizontalFencesAndDots();
    createVerticalFences();


    player1 = new Player("Player 1", true);
    player2 = new Player("Player 2", false);


  }

  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu


  public void playerTurn(int row, int col, boolean horizontal) {
    System.out.println(row + " " + col + " " + horizontal);
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    Integer closedBoxes = gameBoard.checkBoxes(row, col, horizontal);
    if (closedBoxes > 0) currentPlayer.addToScore(closedBoxes);
    else currentPlayer = getCurrentPlayer();
    otherPlayer = getOtherPlayer();
    checkGameEnd();
    System.out.println(row + " " + col + " " + horizontal + "\t" + closedBoxes);
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
    if (player1.getScore() > player2.getScore()){
    //if(true){
      Toast.makeText(GameDisplay.this, player1.getName() + "wins", Toast.LENGTH_SHORT).show();
      Intent indent = new Intent(getApplicationContext(), Popup.class);
      indent.putExtra("player_name" , player1.getName());
      startActivity(indent);
    }
    else if (player1.getScore() < player2.getScore()) {
      Toast.makeText(GameDisplay.this, player2.getName() + "wins", Toast.LENGTH_SHORT).show();
      Intent indent = new Intent(getApplicationContext(), Popup.class);
      indent.putExtra("player_name" , player2.getName());
      startActivity(indent);
    }
    else {
//      Intent indent = new Intent(getApplicationContext(), Popup.class);
//      indent.putExtra("player_name", "AI ");
//      startActivity(indent);
}
    //displayWinner
  }

  // ------CALL QUIT and REPLAY--------
  void quit(){

  }

  /**
   * Creates the horizontal fences and dots in GameDisplay *will need to take
   * grid size inputs in the future
   */
  void createHorizontalFencesAndDots() {
    int putHorFenceX = 0;
    int putHorFenceY = 0;
    int putButtonX = 0;
    int putButtonY = 44;

    for (int row = 0; row < 6; row += 1) { // total amount of rows
      putHorFenceX = 95; // + 150;
      putHorFenceY += 183;
      putButtonX = 0; // + 150;
      putButtonY += 183;
      createHorizontalFences(putHorFenceX, putHorFenceY, row);
      createDots(putButtonX, putButtonY);
    } // for
  }

  /**
   * Takes information from CreateHorizontalFencesAndDots and produces the horizontal fences
   * @param putHorFenceX starting x coordinate of horizontal fence
   * @param putHorFenceY starting y coordinate of horizontal fence
   * @param row what row the horizontal fences are being created in
   */
  void createHorizontalFences(int putHorFenceX, int putHorFenceY, int row) {
    for (int col = 0; col < 5; col += 1) { // num of hor lines per row
      ConstraintLayout layout = findViewById(R.id.boardGameConstraint);

      gameBoard.getOneFence(row, col, true).getButton().setX(putHorFenceX);
      gameBoard.getOneFence(row, col, true).getButton().setY(putHorFenceY);
      layout.addView( gameBoard.getHorizontalFences(row, col).getButton());
      putHorFenceX += 170;
    } // forHorfences
  }

  /**
   * Takes information from CreateHorizontalFencesAndDots and produces the dot visuals.
   * @param putButtonX starting x coordiante of dot button
   * @param putButtonY starting y coordinate of dot button
   */
  void createDots(int putButtonX, int putButtonY) {
    for (int i = 0; i < 6; i += 1) { // dots per row
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
  }

  /**
   * Creates the vertical fences in GameDisplay *will need to take
   * grid size inputs in the future *
   */
  void createVerticalFences() {
    int putVertFenceX = 0;
    int putVertFenceY = 89;

    for (int row = 0; row < 5; row += 1) { // num rows
      putVertFenceX = 10; // + 150;
      putVertFenceY += 183;
      for (int col = 0; col < 6; col += 1) { // lines per row
        ConstraintLayout layout = findViewById(R.id.boardGameConstraint);

        gameBoard.getOneFence(row, col, false).getButton().setX(putVertFenceX);
        gameBoard.getOneFence(row, col, false).getButton().setY(putVertFenceY);

        layout.addView( gameBoard.getVerticalFences(row, col).getButton());
        putVertFenceX += 170;
      } // innerFor
    } // outerFor
  } // createVerticalFences
}
