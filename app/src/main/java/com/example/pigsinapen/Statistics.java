/**
 * Statistics.java
 *
 * <p>This activity is for showing the statistics based on grid size. Every board has their own
 * statistics result. It will show the Player name and score on the screen
 *
 * <p>It includes a method that is taken from Settings class which displays grid sizes on the screen
 */
package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;


public class Statistics extends AppCompatActivity {

  //
  private static final String SHARED_PREF_NAME = "Statistics";

  //a constructor that passes the context to other classes
  private Context context;
  Statistics(Context context) {
    this.context = context;
  }

  /**
   * The function getBoardSizeKey() grabs the numerical values that correspond to all available
   * board sizes and returns it in String format.
   *
   * @param width the number of horizontal dots
   * @param height the number of vertical dots
   * @return the sizeKey, which is a string that displays the size of the board
   */
  private String getBoardSizeForKey(Integer width, Integer height) {
    StringBuilder key = new StringBuilder();
    key.append(width);
    key.append("x");
    key.append(height);
    return key.toString();
  }//getBoardSizeForKey

  /**
   * The function getGamesWonKey() displays the string of the total number of games won
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the string of the number of games won
   */
  private String getGamesWonKey(String sizeKey) {
    StringBuilder gamesWonKey = new StringBuilder();
    gamesWonKey.append(sizeKey);
    gamesWonKey.append("Games Won");
    return gamesWonKey.toString();
  }//getGamesWonKey

  /**
   * The function getGamesLostKey() displays the string of the total number of games lost
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the string of the number of games lost
   */
  private String getGamesLostKey(String sizeKey) {
    StringBuilder gamesLostKey = new StringBuilder();
    gamesLostKey.append(sizeKey);
    gamesLostKey.append("Games Lost");
    return gamesLostKey.toString();
  }//getGamesLostKey

  /**
   * The getGamesPlayedKey() displays the string of the total number of games played
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return a string of the total number of games played
   */
  private String getGamesPlayedKey(String sizeKey) {
    StringBuilder gamesPlayedKey = new StringBuilder();
    gamesPlayedKey.append(sizeKey);
    gamesPlayedKey.append("Games Played");
    return gamesPlayedKey.toString();
  }//getGamesPlayedKey

  /**
   * The function getHighScoreKey() displays the string of the high scores
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the high score value
   */
  private String getHighScoreKey(String sizeKey) {
    StringBuilder highScoreKey = new StringBuilder();
    highScoreKey.append(sizeKey);
    highScoreKey.append("High Score");
    return highScoreKey.toString();
  }//getHighScoreKey

  /**
   * The function incrementGamesWon() is like a counter that keeps track of the total number of games
   * that have been won. It computes this using the shared preferences library.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   */
  private void incrementGamesWon(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getGamesWonKey(sizeKey);
    Integer gamesWon = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(key, gamesWon + 1);
    editor.apply();
  }//incrementGamesWon

  /**
   * The function incrementGamesLost() is like a counter that keeps track of the total number of
   * games that have been lost. It computes this using the shared preferences library.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   */
  private void incrementGamesLost(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String gamesLost = getGamesLostKey(sizeKey);
    Integer score = stats.getInt(gamesLost, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(gamesLost, score + 1);
    editor.apply();
  }//incrementGamesLost

  /**
   * The function incrementGamesPlayed() is like a counter that keeps track of the total number of
   * games that have been played. It computes this using the shared preferences library.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   */
  private void incrementGamesPlayed(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getGamesPlayedKey(sizeKey);
    Integer gamesPlayed = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(key, gamesPlayed + 1);
    editor.apply();
  }//incrementGamesPlayed

  /**
   * The function changeHighScore() updates the player's high scores at the end of a game if the
   * points scored is higher or the highest compared to the rest of the scores recorded.
   * It computes this using the shared preferences library.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @param score
   */
  private void changeHighScore(String sizeKey, Integer score) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getHighScoreKey(sizeKey);
    Integer highScore = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    if(score > highScore) {
      editor.putInt(key, score);
      editor.apply();
    }
  }//changeHighScore

  /**
   * The function changeStats() updates the Statistics menu based on whether player one lost or won
   * or tied the game. It computes this using the Shared Preferences library.
   *
   * @param width the number of horizontal dots
   * @param height the number of vertical dots
   * @param didPlayerOneWin a Boolean to follow a specific set of actions if Player 1 won the game
   * @param playerOneScore Player 1's score from the game that just ended
   * @param didGameTie a Boolean to follow a specific set of actions if the game was tied
   */
  public void changeStats(Integer width, Integer height, Boolean didPlayerOneWin,
                           Integer playerOneScore, Boolean didGameTie) {
    String sizeKey = getBoardSizeForKey(width, height);
    incrementGamesPlayed(sizeKey);
    changeHighScore(sizeKey, playerOneScore);
    if(!didGameTie) {
      if (didPlayerOneWin){
        incrementGamesWon(sizeKey);
      } else {
        incrementGamesLost(sizeKey);
      }
    }
  }//changeStats

  /**
   * The function getGamesWon() computes  and tracks the total number of games won.
   * It does so using the Shared Preferences library for Statistics.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the number of games won
   */
  public Integer getGamesWon(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesWonKey(sizeKey), 0);
  }//getGamesWon

  /**
   * The function getGamesLost() computes and tracks the total number of games lost.
   * It does so using the Shared Preferences library for Statistics.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the number of games lost
   */
  public Integer getGamesLost(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesLostKey(sizeKey), 0);
  }//getGamesLost

  /**
   * The function getGamesPlayed() computes and tracks the total number of games played.
   * It does so using the Shared Preferences library for Statistics.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the total number of games played
   */
  public Integer getGamesPlayed(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesPlayedKey(sizeKey), 0);
  }//getGamesPlayed

  /**
   * The function getHighScore() computes the high score of the player.
   * It does so using the Shared Preferences library for Statistics.
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return the high score
   */
  public Integer getHighScore(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getHighScoreKey(sizeKey), 0);
  }//getHighScore
}
