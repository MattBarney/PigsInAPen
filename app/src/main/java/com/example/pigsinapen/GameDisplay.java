package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Siri's Code
 */

public class GameDisplay extends AppCompatActivity implements View.OnClickListener {

  Player player1, player2;
  GameBoard gameBoard;
  ComputerPlayer computerPlayer;
  Integer boardWidth, boardHeight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);

    // Player names in Quick play mode
    player1 = new Player("Player One", Color.RED, true);
    player2 = new Player("Player Two", Color.BLUE, false);

    // default width and height for Quick play mode
    boardWidth = 6;
    boardHeight = 6;

    //set Player names from user inputs
    setGameboardUserInputs();
    setPlayerNameAndScoreInXML();


    gameBoard = new GameBoard(boardWidth,boardHeight,GameDisplay.this, this);
    showGrid(boardWidth,boardHeight);

  }

  /**
   *
   * @param v
   */

  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu

  @Override
  public void onClick(View v) {}

  /**
   *
   * @param row
   * @param col
   * @param orientation
   */

  // ------------game display----------------
  public void runTurn(int row, int col, boolean orientation) {
//    boolean aiToggle;
//    if (aiToggle){
//]]      runTurnWithComputerPlayer(row, col, orientation);}
//    else
       runTurnWithMultiplayer(row, col, orientation);
//    }
//
//    void runTurnWithComputerPlayer(int row, int col, boolean orientation){
//    if (!player1.turn(row,col,orientation, gameBoard))
//      while (computerPlayer.turn(gameBoard))
//        checkGameEnd();
    }

  /**
   *
   * @param row
   * @param col
   * @param orientation
   */

    void runTurnWithMultiplayer(int row, int col, boolean orientation){
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    if (!currentPlayer.turn(row,col,orientation, gameBoard)){
      currentPlayer.setCurrentPlayer(false) ;
      otherPlayer.setCurrentPlayer(true);
      }
    checkGameEnd();

    System.out.println(row + " " + col + " " + orientation + "\t" + gameBoard.checkBoxes(row, col,orientation));
  }

  /**
   *
   * @return
   */

  Player getCurrentPlayer() {
    if (player1.checkCurrentPlayer()) return player1;
    else return player2;
  }

  /**
   *
   * @return
   */

  Player getOtherPlayer() {
    if (player1.checkCurrentPlayer()) return player2;
    else return player1;
  }

  /**
   *
   */

  void checkGameEnd() {
    Integer currentScore = player1.getScore() + player2.getScore();
    if (currentScore == gameBoard.getMaxScore()) displayWinner();
  }

  /**
   *
   */

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


  /**
   * Jared's code
   */
  // ------CALL QUIT and REPLAY--------
  void quit(){

  }

  /**
   * Will display grid in GameDisplay activity
   * @param row amount of rows the user wants
   * @param col amount of cols the user wants
   */
  void showGrid(int row, int col){
    int putHorFenceX = setHorFenceX(row, col);
    int putHorFenceY = setHorFenceY(row, col);
    int putDotX = setDotX(row, col);
    int putDotY = setDotY(row, col);
    int putVertFenceX = setVertFenceX(row, col);
    int putVertFenceY = setVertFenceY(row, col);

    orientateHorizontalFencesAndDots(putHorFenceX,putHorFenceY,putDotX,putDotY,row, col);
    orientateVerticalFences(putVertFenceX,putVertFenceY, row, col);
  }//sizeOfGridToMake

  /**
   * This function will begin to create the horizontal fence and dot visuals
   * @param putHorFenceX initial X value of horizontal fence
   * @param putHorFenceY initial Y value of horizontal fence
   * @param putDotX initial X value of the dot
   * @param putDotY initial Y value of the dot
   * @param amountOfRows amount of rows the grid has
   * @param amountOfCols amount of columns the grid has
   */
  void orientateHorizontalFencesAndDots(int putHorFenceX, int putHorFenceY, int putDotX, int putDotY, int amountOfRows, int amountOfCols){
    for (int row = 0; row < amountOfRows; row += 1) {
      putHorFenceY += 183;
      putDotY += 183;
      displayHorizontalFences(putHorFenceX, putHorFenceY, row, amountOfCols);
      displayDots(putDotX, putDotY, amountOfCols);
    } // for
  }//createHorizontalFencesAndDots

  /**
   * Takes information from displayHorizontalFencesAndDots and produces the horizontal fences
   * @param putHorFenceX starting x value of horizontal fence
   * @param putHorFenceY current y value of horizontal fence
   * @param row what row the horizontal fences are being created in
   */
  void displayHorizontalFences(int putHorFenceX, int putHorFenceY, int row, int amountOfCols) {
    for (int col = 0; col < amountOfCols - 1; col += 1) { // num of hor lines per row
      ConstraintLayout layout = findViewById(R.id.boardGameConstraint);
      gameBoard.getOneFence(row, col, true).getButton().setX(putHorFenceX);
      gameBoard.getOneFence(row, col, true).getButton().setY(putHorFenceY);
      layout.addView( gameBoard.getHorizontalFences(row, col).getButton());
      putHorFenceX += 170;
    } // forHorfences
  }

  /**
   * Takes information from orientateHorizontalFencesAndDots and produces the dot visuals.
   * @param putDotX starting x coordiante of the dot
   * @param putDotY current y value of the dot
   */
  void displayDots(int putDotX, int putDotY, int amountOfCols) {
    for (int col = 0; col < amountOfCols; col += 1) {
      ConstraintLayout layout = findViewById(R.id.boardGameConstraint);
      ImageView dot = new ImageView(this);
      dot.setX(putDotX);
      dot.setY(putDotY);
      dot.setBackgroundResource(R.drawable.dotnew);
      layout.addView(dot);
      android.view.ViewGroup.LayoutParams layoutParams = dot.getLayoutParams();
      layoutParams.width = 35;
      layoutParams.height = 35;
      putDotX += 170;
      dot.setLayoutParams(layoutParams);
    } // fordot
  }

  /**
   * Orientates the vertical fences but does not make them visible yet
   * @param putVertFenceX starting X value of vertiacl fence
   * @param putVertFenceY starting Y value of vertical fence
   * @param amountOfRows amount of rows the grid has
   * @param amountOfCols amount of ols the grid has
   */
  void orientateVerticalFences(int putVertFenceX, int putVertFenceY, int amountOfRows, int amountOfCols) {
    for (int row = 0; row < amountOfRows - 1; row += 1) {
      putVertFenceY += 183;
      displayVertFences(putVertFenceX,putVertFenceY, row, amountOfCols);
      } // innerFor
  } // createVerticalFences

  /**
   * Makes the orientated fences visible
   * @param putVertFenceX starting X value of vertical fence
   * @param putVertFenceY current Y value of vertical fence
   * @param row the row that the fences are going to be placed in
   * @param amountOfCols how many rows are in the grid
   */

  void displayVertFences(int putVertFenceX, int putVertFenceY, int row, int amountOfCols){
    for (int col = 0; col < amountOfCols; col += 1) { // lines per row
      ConstraintLayout layout = findViewById(R.id.boardGameConstraint);

      gameBoard.getOneFence(row, col, false).getButton().setX(putVertFenceX);
      gameBoard.getOneFence(row, col, false).getButton().setY(putVertFenceY);
      putVertFenceX += 170;
      layout.addView(gameBoard.getVerticalFences(row, col).getButton());
    }//for
  }///placeVertFences

  /**
   * sets the initial horizontal X value
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return initial horizontal X value
   */

  int setHorFenceX(int rowSize,int colSize){
    if(rowSize == 4 && colSize == 4){
      return 250;
    }//else if
    else if(rowSize == 5 && colSize == 4){
      return 250;
    }//else if
    else if(rowSize == 5 && colSize == 5){
      return 170;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 170;
    }//else if
    else if(rowSize == 6 && colSize == 6){
      return 90;
    }//else if

    return 0;
  }//setHorFenceX

  /**
   * sets the initial horizontal Y value
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return initial horizontal X value
   */

  int setHorFenceY(int rowSize,int colSize){
    if(rowSize == 4 && colSize == 4){
      return 100;
    }//else if
    else if(rowSize == 5 && colSize == 4){
      return 0;
    }//else if
    else if(rowSize == 5 && colSize == 5){
      return 0;
    }//else if
    else if (rowSize == 6 && colSize == 5) {
      return 0;
    }//else if
    else if(rowSize == 6 && colSize == 6){
      return 0;
    }//else if
    return 0;
  }//setHorFenceY

  /**
   * sets the initial X value for the dot
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return initial X value for the dot
   */

  int setDotX(int rowSize,int colSize){
    if(rowSize == 4 && colSize == 4){
      return 160;
    }//else if
    else if(rowSize == 5 && colSize == 4){
      return 160;
    }//else if
    else if(rowSize == 5 && colSize == 5){
      return 80;
    }//else if
    else if (rowSize == 6 && colSize == 5) {
      return 80;
    }//else if
    else if(rowSize == 6 && colSize == 6){
      return 0;
    }//else if
    return 0;
  }//setDotX

  /**
   * sets the initial Y value for the dot
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return the initial Y value for the dot
   */

  int setDotY(int rowSize,int colSize){
    if(rowSize == 4 && colSize == 4){
      return 144;
    }//else if
    else if(rowSize == 5 && colSize == 4){
      return 44;
    }//else if
    else if(rowSize == 5 && colSize == 5){
      return 44;
    }//else if
    else if (rowSize == 6 && colSize == 5) {
      return 44;
    }//else if
    else if(rowSize == 6 && colSize == 6){
      return 44;
    }//else if
    return 0;
  }//setDotY

  /**
   * sets the initial X value for the vertical fence
   * @param rowSize amount of rows in the grid
   * @param colSize amount of the cols in the grid
   * @return the initial X value for the vertical value
   */

  int setVertFenceX(int rowSize,int colSize){
    if(rowSize == 4 && colSize == 4){
      return 166;
    }//else if
    else if(rowSize == 5 && colSize == 4){
      return 166;
    }//else if
    else if(rowSize == 5 && colSize == 5){
      return 86;
    }//else if
    else if (rowSize == 6 && colSize == 5) {
      return 86;
    }//else if
    else if(rowSize == 6 && colSize == 6){
      return 6;
    }//else if
    return 0;
  }//setVertFenceX

  /**
   * sets the initial Y value for the vertical fence
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return the initial Y value for the vertical fence
   */

  int setVertFenceY(int rowSize,int colSize){
    if(rowSize == 4 && colSize == 4){
      return 189;
    }//else if
    else if(rowSize == 5 && colSize == 4){
      return 89;
    }//else if
    else if(rowSize == 5 && colSize == 5){
      return 89;
    }//else if
    else if (rowSize == 6 && colSize == 5) {
      return 89;
    }//else if
    else if(rowSize == 6 && colSize == 6){
      return 89;
    }//else if
    return 0;
  }//setVertFenceY

  /**
   * Alvee's Code
   */

  /**
   * set players names, grid size from user input from Setting activity
   */
  void setGameboardUserInputs(){
    //  check if previous intent "Setting class" sends values of players name and grid size
    if (getIntent().hasExtra("WIDTH") && getIntent().hasExtra("HEIGHT") && getIntent().hasExtra("PLAYER_ONE_NAME") && getIntent().hasExtra("PLAYER_TWO_NAME")){
      boardWidth = Integer.parseInt(getIntent().getStringExtra("WIDTH"));
      boardHeight = Integer.parseInt(getIntent().getStringExtra("HEIGHT"));
      String playerOneName = getIntent().getStringExtra("PLAYER_ONE_NAME");
      String playerTwoName = getIntent().getStringExtra("PLAYER_TWO_NAME");
      player1 = new Player(playerOneName,Color.RED, true);
      player2 = new Player(playerTwoName, Color.BLUE, false);
    }
  }

  /**
   * set players names, scores to XML code GameDisplay activity
   */
  void setPlayerNameAndScoreInXML(){
    TextView playerOneNameFromXml = findViewById(R.id.playerOneName);
    playerOneNameFromXml.setText(player1.getName());

    TextView playerTwoNameFromXml = findViewById(R.id.playerTwoName);
    playerTwoNameFromXml.setText(player2.getName());

    TextView playerOneScore = findViewById(R.id.playerOneScore);
    playerOneScore.setText(String.valueOf(player1.getScore()));

    TextView playerTwoScore = findViewById(R.id.playerTwoScore);
    playerTwoScore.setText(String.valueOf(player2.getScore()));
  }


}