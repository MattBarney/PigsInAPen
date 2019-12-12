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
   *
   * @param width
   * @param height
   * @return
   */
  private String getBoardSizeForKey(Integer width, Integer height) {
    StringBuilder key = new StringBuilder();
    key.append(width);
    key.append("x");
    key.append(height);
    return key.toString();
  }//getBoardSizeForKey

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  private String getGamesWonKey(String sizeKey) {
    StringBuilder gamesWonKey = new StringBuilder();
    gamesWonKey.append(sizeKey);
    gamesWonKey.append("Games Won");
    return gamesWonKey.toString();
  }//getGamesWonKey

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  private String getGamesLostKey(String sizeKey) {
    StringBuilder gamesLostKey = new StringBuilder();
    gamesLostKey.append(sizeKey);
    gamesLostKey.append("Games Lost");
    return gamesLostKey.toString();
  }//getGamesLostKey

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  private String getGamesPlayedKey(String sizeKey) {
    StringBuilder gamesPlayedKey = new StringBuilder();
    gamesPlayedKey.append(sizeKey);
    gamesPlayedKey.append("Games Played");
    return gamesPlayedKey.toString();
  }//getGamesPlayedKey

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  private String getHighScoreKey(String sizeKey) {
    StringBuilder highScoreKey = new StringBuilder();
    highScoreKey.append(sizeKey);
    highScoreKey.append("High Score");
    return highScoreKey.toString();
  }//getHighScoreKey

  /**
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
   *
   * @param width
   * @param height
   * @param didPlayerOneWin
   * @param playerOneScore
   * @param didGameTie
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
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  public Integer getGamesWon(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesWonKey(sizeKey), 0);
  }//getGamesWon

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  public Integer getGamesLost(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesLostKey(sizeKey), 0);
  }//getGamesLost

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  public Integer getGamesPlayed(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesPlayedKey(sizeKey), 0);
  }//getGamesPlayed

  /**
   *
   * @param sizeKey a string of number x number format that depicts the size of each board type
   * @return
   */
  public Integer getHighScore(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getHighScoreKey(sizeKey), 0);
  }//getHighScore
}
