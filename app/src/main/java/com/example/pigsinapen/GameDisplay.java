/**
 *
 */

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

import com.example.pigsinapen.ComputerPlayer;
import com.example.pigsinapen.GameBoard;
import com.example.pigsinapen.Player;
import com.example.pigsinapen.R;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;
/** Siri's Code */

/**
 * GameDisplay.java
 *
 * This java class consists of the desired layout and display of our working game board.
 * It is an event-driven architecture which allows for the flow of user control from
 * the game play to other screens.
 * It also includes the game loop which runs the players turns, the dynamic set up of
 * dots on the screen, and the vertical and horizontal fences which are interactive
 * to facilitate the game play.
 * In addition, it consists of tallying the scores of the individual players and
 * determines the winner of the game.
 *
 */

public class GameDisplay extends AppCompatActivity implements View.OnClickListener {

  Player player1, player2;
  ComputerPlayer computer;
  GameBoard gameBoard;
  Boolean aiToggle;

  Integer boardWidth, boardHeight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);

    // Quick play is only against computer player
    aiToggle = true;

    // Player names in Quick play mode
    player1 = new Player("Player One", Color.RED, true);
    computer = new ComputerPlayer("Computer", Color.BLUE, false);

    // default width and height for Quick play mode
    boardWidth = 5;
    boardHeight = 5;

    // set Player names from user inputs
    setGameboardUserInputs();
    setPlayerNameAndScoreInXML();

    gameBoard = new GameBoard(boardWidth, boardHeight, GameDisplay.this, this);
    showGrid(boardWidth, boardHeight);
  }

  /** @param v */

  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu

  @Override
  public void onClick(View v) {}

  /**
   * @param row the row index of the fence
   * @param col the column index of the fence
   * @param orientation the horizontal or vertical lines of the fence
   *
   * The runTurn() function takes the index and orientation of the fences as inputs to check if
   * the game is against another player or against the computer and calls on the corresponding
   * methods which run the respective game plays.
   */

  public void runTurn(int row, int col, Boolean orientation) {
    if (aiToggle) {
      runTurnWithComputerPlayer(row, col, orientation);
    }
    else
      runTurnWithMultiplayer(row, col, orientation);
  }

  /**
   * @param row the row index of the fence
   * @param col the column index of the fence
   * @param orientation the horizontal or vertical lines of the fence
   *
   * The runTurnWithComputerPlayer() function takes the index and orientation of the fences as
   * inputs and ensures the game play is against a computer and updates the corresponding scores
   * after which available dots on the board are checked in order to determine if the game is over.
   * This function also helps dictate the players' and computer's turns.
   */

  private void runTurnWithComputerPlayer(Integer row, Integer col, Boolean orientation) {
    if (!player1.turn(row, col, orientation, gameBoard)) {
      while (computer.turn(gameBoard)) {
        updateScores();
        checkGameEnd();
      }
    } else {
      updateScores();
      checkGameEnd();
    }
  }

  /**
   * @param row the row index of the fence
   * @param col the column index of the fence
   * @param orientation the horizontal or vertical lines of the fence
   *
   * The runTurnWithMultiplayer() function takes the index and orientation of the fences
   * as inputs and ensures the game play is against another player and updates the corresponding
   * scores after which available dots on the board are checked in order to determine if the
   * game is over. This function also helps dictate the two players' turns.
   */

  private void runTurnWithMultiplayer(Integer row, Integer col, Boolean orientation) {
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    if (!currentPlayer.turn(row, col, orientation, gameBoard)) {
      currentPlayer.setCurrentPlayer(false);
      otherPlayer.setCurrentPlayer(true);
      updateTurnIndicator();
    }
    updateScores();
    checkGameEnd();
  }

  /** Updates the score TextViews */
  private void updateScores() {
    TextView playerOneScore = findViewById(R.id.playerOneScore);
    TextView playerTwoScore = findViewById(R.id.playerTwoScore);
    playerOneScore.setText(player1.getScore().toString());
    if (aiToggle) {
      playerTwoScore.setText(computer.getScore().toString());
    } else {
      playerTwoScore.setText(player2.getScore().toString());
    }
  }

  /** Changes the indicator TextView to display which player's turn it is */
  private void updateTurnIndicator() {
    TextView turnIndicator = findViewById(R.id.turnIndicator);
    Player currentPlayer = getCurrentPlayer();

    String playerName = currentPlayer.getName();
    if (playerName.charAt(playerName.length() - 1) == 's') {
      turnIndicator.setText(currentPlayer.getName() + "' Turn");
    } else {
      turnIndicator.setText(currentPlayer.getName() + "'s Turn");
    }
    turnIndicator.setTextColor(currentPlayer.getColor());
  }

  /**
   * @return
   */
  Player getCurrentPlayer() {
    if (player1.checkCurrentPlayer())
      return player1;
    else
      return player2;
  }

  /**
   * @return
   */
  Player getOtherPlayer() {
    if (player1.checkCurrentPlayer())
      return player2;
    else
      return player1;
  }

  /**
   *
   *
   */
  public void checkGameEnd() {
    Integer currentScore;
    if (aiToggle) {
      currentScore = player1.getScore() + computer.getScore();
    } else {
      currentScore = player1.getScore() + player2.getScore();
    }

    if (currentScore == gameBoard.getMaxScore()) {
      displayWinner();
    }
  }

  /**
   *
   */
  // game winner display function modified to show the correct winner.
  private void displayWinner() {
    if (aiToggle) {
      displayWinnerComputerMatch();
    } else {
      displayWinnerMultiplayerMatch();
    }
  } // displayWinner

  /**
   * Matt's code
   */
  private void displayWinnerComputerMatch() {
   if (player1.getScore() == computer.getScore()) {
     showPopupWindow("Game Tied !");
   } else if (player1.getScore() > computer.getScore()) {
     showPopupWindow(player1.getName() + " Wins !");
   } else if (player1.getScore() < computer.getScore()) {
     showPopupWindow(computer.getName() + " Wins !");
   }
  }

  /**
   *  Siri and Alvee's code
   */
  private void displayWinnerMultiplayerMatch() {
    if (player1.getScore() == player2.getScore()) {
      showPopupWindow("Game Tied !");
    } else if (player1.getScore() > player2.getScore()) {
      showPopupWindow(player1.getName() + " Wins !");
    } else if (player1.getScore() < player2.getScore()) {
      showPopupWindow(player2.getName() + " Wins !");
    }
  }


  /** Jared's code */

  /**
   * Will display grid in GameDisplay activity
   *
   * @param row amount of rows the user wants
   * @param col amount of cols the user wants
   */
  void showGrid( Integer row,  Integer col) {
    Integer putHorFenceX = setHorFenceX(row, col);
    Integer putHorFenceY = setHorFenceY(row, col);
    Integer putDotX = setDotX(row, col);
    Integer putDotY = setDotY(row, col);
    Integer putVertFenceX = setVertFenceX(row, col);
    Integer putVertFenceY = setVertFenceY(row, col);

    orientateHorizontalFencesAndDots(putHorFenceX, putHorFenceY, putDotX, putDotY, row, col);
    orientateVerticalFences(putVertFenceX, putVertFenceY, row, col);
  }

  /**
   * This function will begin to create the horizontal fence and dot visuals
   *
   * @param putHorFenceX initial X value of horizontal fence
   * @param putHorFenceY initial Y value of horizontal fence
   * @param putDotX initial X value of the dot
   * @param putDotY initial Y value of the dot
   * @param amountOfRows amount of rows the grid has
   * @param amountOfCols amount of columns the grid has
   */
  void orientateHorizontalFencesAndDots(
    Integer putHorFenceX,
    Integer putHorFenceY,
    Integer putDotX,
    Integer putDotY,
    Integer amountOfRows,
    Integer amountOfCols) {
    for (Integer row = 0; row < amountOfRows; row += 1) {
      putHorFenceY += 183;
      putDotY += 183;
      displayHorizontalFences(putHorFenceX, putHorFenceY, row, amountOfCols);
      displayDots(putDotX, putDotY, amountOfCols);
    } // for
  } // createHorizontalFencesAndDots
  
  /**
   * Takes information from displayHorizontalFencesAndDots and produces the horizontal fences
   *
   * @param putHorFenceX starting x value of horizontal fence
   * @param putHorFenceY current y value of horizontal fence
   * @param row what row the horizontal fences are being created in
   */
  void displayHorizontalFences(Integer putHorFenceX, Integer putHorFenceY, Integer row, Integer amountOfCols) {
    for (Integer col = 0; col < amountOfCols - 1; col += 1) { // num of hor lines per row
      ConstraintLayout layout = findViewById(R.id.boardGameConstraint);
      gameBoard.getOneFence(row, col, true).getButton().setX(putHorFenceX);
      gameBoard.getOneFence(row, col, true).getButton().setY(putHorFenceY);
      layout.addView(gameBoard.getHorizontalFences(row, col).getButton());
      putHorFenceX += 170;
    } // forHorfences
  }

  /**
   * Takes information from orientateHorizontalFencesAndDots and produces the dot visuals.
   *
   * @param putDotX starting x coordiante of the dot
   * @param putDotY current y value of the dot
   */
  void displayDots(Integer putDotX, Integer putDotY, Integer amountOfCols) {
    for (Integer col = 0; col < amountOfCols; col += 1) {
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
   *
   * @param putVertFenceX starting X value of vertiacl fence
   * @param putVertFenceY starting Y value of vertical fence
   * @param amountOfRows amount of rows the grid has
   * @param amountOfCols amount of ols the grid has
   */
  void orientateVerticalFences(
    Integer putVertFenceX, Integer putVertFenceY, Integer amountOfRows, Integer amountOfCols) {
    for (Integer row = 0; row < amountOfRows - 1; row += 1) {
      putVertFenceY += 183;
      displayVertFences(putVertFenceX, putVertFenceY, row, amountOfCols);
    } // innerFor
  } // createVerticalFences

  /**
   * Makes the orientated fences visible
   *
   * @param putVertFenceX starting X value of vertical fence
   * @param putVertFenceY current Y value of vertical fence
   * @param row the row that the fences are going to be placed in
   * @param amountOfCols how many rows are in the grid
   */
  void displayVertFences(Integer putVertFenceX, Integer putVertFenceY, Integer row, Integer amountOfCols) {
    for (Integer col = 0; col < amountOfCols; col += 1) { // lines per row
      ConstraintLayout layout = findViewById(R.id.boardGameConstraint);

      gameBoard.getOneFence(row, col, false).getButton().setX(putVertFenceX);
      gameBoard.getOneFence(row, col, false).getButton().setY(putVertFenceY);
      putVertFenceX += 170;
      layout.addView(gameBoard.getVerticalFences(row, col).getButton());
    } // for
  } /// placeVertFences

  /**
   * Sets the initial horizontal X value
   *
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return initial horizontal X value
   */
  Integer setHorFenceX(Integer rowSize, Integer colSize) {
    if (rowSize == 4 && colSize == 4) {
      return 250;
    } // else if
    else if (rowSize == 5 && colSize == 4) {
      return 250;
    } // else if
    else if (rowSize == 5 && colSize == 5) {
      return 170;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 170;
    } // else if
    else if (rowSize == 6 && colSize == 6) {
      return 90;
    } // else if

    return 0;
  } // setHorFenceX

  /**
   * Sets the initial horizontal Y value
   *
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return initial horizontal X value
   */
  Integer setHorFenceY(Integer rowSize, Integer colSize) {
    if (rowSize == 4 && colSize == 4) {
      return 100;
    } // else if
    else if (rowSize == 5 && colSize == 4) {
      return 0;
    } // else if
    else if (rowSize == 5 && colSize == 5) {
      return 0;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 0;
    } // else if
    else if (rowSize == 6 && colSize == 6) {
      return 0;
    } // else if
    return 0;
  } // setHorFenceY

  /**
   * Sets the initial X value for the dot
   *
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return initial X value for the dot
   */
  Integer setDotX(Integer rowSize, Integer colSize) {
    if (rowSize == 4 && colSize == 4) {
      return 160;
    } // else if
    else if (rowSize == 5 && colSize == 4) {
      return 160;
    } // else if
    else if (rowSize == 5 && colSize == 5) {
      return 80;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 80;
    } // else if
    else if (rowSize == 6 && colSize == 6) {
      return 0;
    } // else if
    return 0;
  } // setDotX

  /**
   * Sets the initial Y value for the dot
   *
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return the initial Y value for the dot
   */
  Integer setDotY(Integer rowSize, Integer colSize) {
    if (rowSize == 4 && colSize == 4) {
      return 144;
    } // else if
    else if (rowSize == 5 && colSize == 4) {
      return 44;
    } // else if
    else if (rowSize == 5 && colSize == 5) {
      return 44;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 44;
    } // else if
    else if (rowSize == 6 && colSize == 6) {
      return 44;
    } // else if
    return 0;
  } // setDotY

  /**
   * Sets the initial X value for the vertical fence
   *
   * @param rowSize amount of rows in the grid
   * @param colSize amount of the cols in the grid
   * @return the initial X value for the vertical value
   */
  Integer setVertFenceX(Integer rowSize, Integer colSize) {
    if (rowSize == 4 && colSize == 4) {
      return 166;
    } // else if
    else if (rowSize == 5 && colSize == 4) {
      return 166;
    } // else if
    else if (rowSize == 5 && colSize == 5) {
      return 86;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 86;
    } // else if
    else if (rowSize == 6 && colSize == 6) {
      return 6;
    } // else if
    return 0;
  } // setVertFenceX

  /**
   * Sets the initial Y value for the vertical fence
   *
   * @param rowSize amount of rows in the grid
   * @param colSize amount of cols in the grid
   * @return the initial Y value for the vertical fence
   */
  Integer setVertFenceY(Integer rowSize, Integer colSize) {
    if (rowSize == 4 && colSize == 4) {
      return 189;
    } // else if
    else if (rowSize == 5 && colSize == 4) {
      return 89;
    } // else if
    else if (rowSize == 5 && colSize == 5) {
      return 89;
    } // else if
    else if (rowSize == 6 && colSize == 5) {
      return 89;
    } // else if
    else if (rowSize == 6 && colSize == 6) {
      return 89;
    } // else if
    return 0;
  } // setVertFenceY

  /**
   * Gets the Players names, chosen grid size from User, from Setting Activity class through Intent
   *
   * <p>Sets players names, grid size into current Game Display activity
   *
   * <p>Alvee's code
   */
  private void setGameboardUserInputs() {
    //  check if previous intent "Setting class" sends values of players name and grid size
    if (getIntent().hasExtra("WIDTH")
        && getIntent().hasExtra("HEIGHT")
        && getIntent().hasExtra("PLAYER_ONE_NAME")
        && getIntent().hasExtra("PLAYER_TWO_NAME")) {
      boardWidth = Integer.parseInt(getIntent().getStringExtra("WIDTH"));
      boardHeight = Integer.parseInt(getIntent().getStringExtra("HEIGHT"));
      String playerOneName = getIntent().getStringExtra("PLAYER_ONE_NAME");
      String playerTwoName = getIntent().getStringExtra("PLAYER_TWO_NAME");
      aiToggle = Boolean.parseBoolean(getIntent().getStringExtra("AI_TOGGLE"));
      player1 = new Player(playerOneName, Color.RED, true);
      if (aiToggle) {
        computer = new ComputerPlayer(playerTwoName, Color.BLUE, false);
      } else {
        player2 = new Player(playerTwoName, Color.BLUE, false);
        updateTurnIndicator();
      }
    }
  }

  /**
   * Gets the id values of player1, player2 names and scores from XML attributes
   *
   * <p>Sets players names, scores to XML code in GameDisplay activity
   */
  private void setPlayerNameAndScoreInXML() {
    TextView playerOneNameFromXml = findViewById(R.id.playerOneName);
    playerOneNameFromXml.setText(player1.getName());
    TextView playerOneScore = findViewById(R.id.playerOneScore);
    playerOneScore.setText(String.valueOf(player1.getScore()));

    TextView playerTwoNameFromXml = findViewById(R.id.playerTwoName);
    TextView playerTwoScore = findViewById(R.id.playerTwoScore);

    if (aiToggle) {
      playerTwoNameFromXml.setText(computer.getName());
      playerTwoScore.setText(String.valueOf(computer.getScore()));
    } else {
      playerTwoNameFromXml.setText(player2.getName());
      playerTwoScore.setText(String.valueOf(player2.getScore()));
    }
  }
  /**
   * Shows the popup window based on who wins the game such as player 1, player 2, computer, tied
   *
   * @param winnerName String value
   */
  private void showPopupWindow(String winnerName) {

    Intent indent = new Intent(getApplicationContext(), Popup.class);
    indent.putExtra("player_name", winnerName);
    startActivity(indent);
  }
}
