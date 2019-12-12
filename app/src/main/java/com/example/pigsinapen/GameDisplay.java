/**
 * Methods:
 *
 *    - onCreate(Bundle savedInstanceState)
 *
 *    - disableVolume(View v)
 *
 *    - enableVolume(View v)
 *
 *    - GoBackToMenu(View v)
 *
 *    - runTurn(int row, int col, Boolean orientation)
 *
 *    - runTurnWithComputerPlayer(Integer row, Integer col, Boolean orientation)
 *
 *    - delayComputerTurn()
 *
 *    - run()
 *
 *    - runTurnWithMultiplayer(Integer row, Integer col, Boolean orientation)
 *
 *    - updateScores()
 *
 *    - updateTurnIndicator(String name, int color)
 *
 *    - getCurrentPlayer()
 *
 *    - getOtherPlayer()
 *
 *    - checkGameEnd()
 *
 *    - displayWinner()
 *
 *    - displayWinnerComputerMatch()
 *
 *    - displayWinnerMultiplayerMatch()
 *
 *    - showGrid(Integer row, Integer col)
 *
 *    - orientateHorizontalFencesAndDots(
 *
 *    - displayHorizontalFences(
 *
 *    - displayDots(Integer putDotX, Integer putDotY, Integer amountOfCols)
 *
 *    - orientateVerticalFences(
 *
 *    - displayVertFences(
 *
 *    - setHorFenceX(Integer rowSize, Integer colSize)
 *
 *    - setHorFenceY(Integer rowSize, Integer colSize)
 *
 *    - setDotY(Integer rowSize, Integer colSize)
 *
 *    - setVertFenceX(Integer rowSize, Integer colSize)
 *
 *    - setVertFenceY(Integer rowSize, Integer colSize)
 *
 *    - setGameboardUserInputs()
 *
 *    - setPlayerNameAndScoreInXML()
 *
 *    - showPopupWindow(String winnerName)
 *
 */
package com.example.pigsinapen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
/** Siri's Code */

/**
 * GameDisplay.java
 *
 * <p>This java class consists of the desired layout and display of our working game board. It is an
 * event-driven architecture which allows for the flow of user control from the game play to other
 * screens. It also includes the game loop which runs the players turns, the dynamic set up of dots
 * on the screen, and the vertical and horizontal fences which are interactive to facilitate the
 * game play. In addition, it consists of tallying the scores of the individual players and
 * determines the winner of the game.
 *
 */
public class GameDisplay extends AppCompatActivity {

  private final Integer MILLISECOND_DELAY = 1000;
  Player playerOne, playerTwo;
  ComputerPlayer computer;
  GameBoard gameBoard;
  Boolean aiToggle;
  Integer boardWidth, boardHeight;
  Sound sound;
  private ImageView disableSoundButton2;
  private ImageView enableSoundButton2;
  /**
   *
   * When GameDisplay activity starts, it can be reached from two different activities, namely,
   * MainActivity and Settings classes. Based on the respective class, it initiates this method, and
   * will set up the GameDisplay board, user names, and scores. 1) Default set up for Quick Play is
   * Player One vs Computer, with a grid size of 5x5 for the GameBoard, or 2) It will set up the
   * names for multiplayers i.e, player one and two, and chooses the GameBoard size from the
   * Settings class
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);

    sound = new Sound(this);
    sound.initializeGameComplete();
    sound.initializePointScore();
    sound.initializeButtonClick();
    disableSoundButton2 = findViewById(R.id.disableSoundButton2);
    enableSoundButton2 = findViewById(R.id.enableSoundButton2);
    enableSoundButton2.setVisibility(View.INVISIBLE);
    // Quick play is only against computer player
    aiToggle = true;

    // Player names in Quick play mode
    playerOne = new Player("Player One", playerOneColour(), true, sound);
    computer = new ComputerPlayer("Computer", computerColour(), false);

    // default width and height for Quick play mode
    boardWidth = 5;
    boardHeight = 5;
    // set Player names from user inputs
    setGameboardUserInputs();
    setPlayerNameAndScoreInXML();

    // Display that its player one's turn
    updateTurnIndicator(playerOne.getName(), playerOne.getColor());

    gameBoard = new GameBoard(boardWidth, boardHeight, GameDisplay.this, this);
    showGrid(boardWidth, boardHeight);
    //Keeping enable/disable buttons consistent throughout application
    if(sound.isSoundEnabled() == true){
      disableSoundButton2.setVisibility(View.VISIBLE);
      enableSoundButton2.setVisibility(View.INVISIBLE);
    }//if
    else{
      disableSoundButton2.setVisibility(View.INVISIBLE);
      enableSoundButton2.setVisibility(View.VISIBLE);
    }//else
  }


  /**
   * Returns to MainActivity activity screen.
   *
   * @param v View object value
   */
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
    finish();
  } // goBackToMenu

  /**
   * @param row the row index of the fence
   * @param col the column index of the fence
   * @param orientation the horizontal or vertical lines of the fence
   *     <p>The runTurn() function takes the index and orientation of the fences as inputs to check
   *     if the game is against another player or against the computer and calls on the
   *     corresponding methods which run the respective game plays.
   */
  public void runTurn(int row, int col, Boolean orientation) {
    sound.buttonClick();
    if (aiToggle) {
      runTurnWithComputerPlayer(row, col, orientation);
    } else runTurnWithMultiplayer(row, col, orientation);
  }

  /**
   * @param row the row index of the fence
   * @param col the column index of the fence
   * @param orientation the horizontal or vertical lines of the fence
   *     <p>The runTurnWithComputerPlayer() function takes the index and orientation of the fences
   *     as inputs and ensures the game play is against a computer and updates the corresponding
   *     scores after which available dots on the board are checked in order to determine if the
   *     game is over. This function also helps dictate the players' and computer's turns.
   */
  private void runTurnWithComputerPlayer(Integer row, Integer col, Boolean orientation) {
    if (!playerOne.turn(row, col, orientation, gameBoard)) {
      updateTurnIndicator(computer.getName(), computer.getColor());
      gameBoard.makeFencesUnclickable();
      delayComputerTurn();
    } else {
      updateScores();
      checkGameEnd();
    }
  }

  /**
   * Delays the computer turn.
   *
   * <p>Waits and then executes the computer player's turn. This makes games against the computer
   * easier to follow and helps establish a pace to the game.
   *
   * <p>Idea for code taken from: https://stackoverflow.com/a/28173911
   *
   * <p>Written by Matt.
   */
  private void delayComputerTurn() {
    new Handler(Looper.getMainLooper()).postDelayed(new ComputerTurn(), MILLISECOND_DELAY);
  }

  /**
   * Class that runs the computer player's turn.
   *
   * <p>By using a separate class to run a computer turn we can add a delay for better pacing in
   * games against a computer.
   *
   * <p>Written by Matt.
   */
  private class ComputerTurn implements Runnable {

    /**
     * Runs a turn for the computer player.
     *
     * <p>Runs a computer player's turn, if the computer scores then it gets another turn. If the
     * computer does not score the fence buttons are made clickable and the turn indicator is
     * updated.
     */
    @Override
    public void run() {
      if (computer.turn(gameBoard)) {
        sound.pointScore();
        updateScores();
        checkGameEnd();
        delayComputerTurn();
      } else {
        gameBoard.makeUnclickedFencesClickable();
        updateTurnIndicator(playerOne.getName(), playerOne.getColor());
      }
    }
  } // ComputerTurn

  /**
   * @param row the row index of the fence
   * @param col the column index of the fence
   * @param orientation the horizontal or vertical lines of the fence
   *     <p>The runTurnWithMultiplayer() function takes the index and orientation of the fences as
   *     inputs and ensures the game play is against another player and updates the corresponding
   *     scores after which available dots on the board are checked in order to determine if the
   *     game is over. This function also helps dictate the two players' turns.
   */
  private void runTurnWithMultiplayer(Integer row, Integer col, Boolean orientation) {
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    if (!currentPlayer.turn(row, col, orientation, gameBoard)) {
      currentPlayer.setCurrentPlayer(false);
      otherPlayer.setCurrentPlayer(true);
      updateTurnIndicator(otherPlayer.getName(), otherPlayer.getColor());
    }
    updateScores();
    checkGameEnd();
  }

  /** Updates the score TextViews for all types of players */
  private void updateScores() {
    TextView playerOneScore = findViewById(R.id.playerOneScore);
    TextView playerTwoScore = findViewById(R.id.playerTwoScore);
    playerOneScore.setText(playerOne.getScore().toString());
    if (aiToggle) {
      playerTwoScore.setText(computer.getScore().toString());
    } else {
      playerTwoScore.setText(playerTwo.getScore().toString());
    }
  }

  /**
   * Updates turn indicator with current player's name and color.
   *
   * <p>Takes a player's name and color as input and sets the turnIndicator's text to use that
   * player's name and color.
   *
   * @param name The name of a current player.
   * @param color The color representing a current player.
   */
  private void updateTurnIndicator(String name, int color) {
    TextView turnIndicator = findViewById(R.id.turnIndicator);

    if (name.charAt(name.length() - 1) == 's') {
      turnIndicator.setText(name + "'s Turn");
    } else {
      turnIndicator.setText(name + "'s Turn");
    }
    turnIndicator.setTextColor(color);
  }

  /**
   * The getCurrentPlayer() function helps determine which player is currently playing
   *
   * @return returns the current player i.e., the player whose turn it is
   */
  Player getCurrentPlayer() {
    if (playerOne.checkCurrentPlayer()) return playerOne;
    else return playerTwo;
  }

  /**
   * The getotherPlayer() function helps determine which player is currently waiting to take their
   * turn
   *
   * @return returns the other player, i.e., the player who is waiting on their turn
   */
  Player getOtherPlayer() {
    if (playerOne.checkCurrentPlayer()) return playerTwo;
    else return playerOne;
  }

  /**
   * The checkGameEnd() function tallies the scores for the two players of the two different
   * GameBoard types and checks to see if either player has attained the maximum possible score for
   * the corresponding grid size and if this is the case, it calls on displayWinner() to display the
   * winner
   */
  public void checkGameEnd() {
    Integer currentScore;
    if (aiToggle) {
      currentScore = playerOne.getScore() + computer.getScore();
    } else {
      currentScore = playerOne.getScore() + playerTwo.getScore();
    }

    if (currentScore == gameBoard.getMaxScore()) {
      displayWinner();
    }
  }

  /**
   * The displayWinner() function checks to see which game mode is being played and calls on the
   * appropriate functions to display the winner of the game
   */
  private void displayWinner() {
    sound.gameComplete();
    if (aiToggle) {
      displayWinnerComputerMatch();
    } else {
      displayWinnerMultiplayerMatch();
    }

  } // displayWinner

  /**
   * Matt's code
   *
   * <p>Displays the winner or informs the game has been tied in a Computer Player game play mode.
   */
  private void displayWinnerComputerMatch() {
    if (playerOne.getScore() == computer.getScore()) {
      showPopupWindow("Game Tied !");
    } else if (playerOne.getScore() > computer.getScore()) {
      showPopupWindow(playerOne.getName() + " Wins!");
    } else if (playerOne.getScore() < computer.getScore()) {
      showPopupWindow(computer.getName() + " Wins!");
    }
  }

  /**
   * Siri's and Alvee's code
   *
   * <p>Displays the winner or informs the game has been tied as a Popup in a Multiplayer game play
   * mode.
   */
  private void displayWinnerMultiplayerMatch() {
    if (playerOne.getScore() == playerTwo.getScore()) {
      showPopupWindow("Game Tied !");
    } else if (playerOne.getScore() > playerTwo.getScore()) {
      showPopupWindow(playerOne.getName() + " Wins!");
    } else if (playerOne.getScore() < playerTwo.getScore()) {
      showPopupWindow(playerTwo.getName() + " Wins !");
    }
  }

  /** Jared's code */

  /**
   *  Disables the volume throughout the application
   * @param v the disable volume image being tapped
   */
  public void disableVolume(View v) {
    sound.disableSound();
    disableSoundButton2.setVisibility(View.INVISIBLE);
    enableSoundButton2.setVisibility(View.VISIBLE);
  } // disableVolume

  /**
   * Enables the volume throughout the application
   * @param v the enable volume image being tapped
   */
  public void enableVolume(View v) {
    sound.enableSound();
    disableSoundButton2.setVisibility(View.VISIBLE);
    enableSoundButton2.setVisibility(View.INVISIBLE);
  } // enableVolume
  /**
   * Will display grid in GameDisplay activity
   *
   * @param row amount of rows the user wants
   * @param col amount of cols the user wants
   */
  void showGrid(Integer row, Integer col) {
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
  void displayHorizontalFences(
      Integer putHorFenceX, Integer putHorFenceY, Integer row, Integer amountOfCols) {
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
  void displayVertFences(
      Integer putVertFenceX, Integer putVertFenceY, Integer row, Integer amountOfCols) {
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
   * Alvee's code
   *
   * <p>Gets the Players names, chosen grid size from User, from Setting Activity class through
   * Intent Sets players names, grid size into current Game Display activity
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
      playerOne = new Player(playerOneName, playerOneColour(), true, sound);
      if (aiToggle) {
        computer = new ComputerPlayer(playerTwoName, computerColour(), false);
      } else {
        playerTwo = new Player(playerTwoName, playerTwoColour(), false, sound);
      }
    }
  }

  /**
   * Gets the id values of playerOne, playerTwo names and scores from XML attributes
   *
   * <p>Sets players names, scores to XML code in GameDisplay activity
   */
  private void setPlayerNameAndScoreInXML() {
    TextView playerOneNameFromXml = findViewById(R.id.playerOneName);
    playerOneNameFromXml.setText(playerOne.getName());
    TextView playerOneScore = findViewById(R.id.playerOneScore);
    playerOneScore.setText(String.valueOf(playerOne.getScore()));

    TextView playerTwoNameFromXml = findViewById(R.id.playerTwoName);
    TextView playerTwoScore = findViewById(R.id.playerTwoScore);

    if (aiToggle) {
      playerTwoNameFromXml.setText(computer.getName());
      playerTwoScore.setText(String.valueOf(computer.getScore()));
    } else {
      playerTwoNameFromXml.setText(playerTwo.getName());
      playerTwoScore.setText(String.valueOf(playerTwo.getScore()));
    }
  }
  /**
   * Starts Popup class as a popup window on top of the GameDisplay class
   *
   * @param winnerName String value
   */
  private void showPopupWindow(String winnerName) {

    Intent indent = new Intent(getApplicationContext(), Popup.class);
    indent.putExtra("player_name", winnerName);
    startActivity(indent);
  }
  /**
   * Returns Computer color to set up
   *
   * @return Integer value color code
   */
  private Integer computerColour(){
    return Color.BLACK;
  }
  /**
   * Returns Player One color to set up
   *
   * @return Integer value color code
   */
  private Integer playerOneColour(){
    return Color.CYAN;
  }
  /**
   * Returns Player Two color to set up
   *
   * @return Integer value color code
   */
  private Integer playerTwoColour(){
    return Color.DKGRAY;
  }

}
