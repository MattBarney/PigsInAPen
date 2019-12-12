/**
 * Statistics.java
 *
 * <p>This activity is for showing the statistics based on grid size. Every board has their own
 * statistics result. It will show the Player name and score on the screen
 *
 * <p>Few methods are taken from Settings class such as - showing grid sizes on the screen
 */
package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;


public class Statistics extends AppCompatActivity {

  private static final String SHARED_PREF_NAME = "Statistics";

  private Context context;

  Statistics(Context context) {
    this.context = context;
  }

  private String getBoardSizeForKey(Integer width, Integer height) {
    StringBuilder key = new StringBuilder();
    key.append(width);
    key.append("x");
    key.append(height);
    return key.toString();
  }//getBoardSizeForKey


  private String getGamesWonKey(String sizeKey) {
    StringBuilder gamesWonKey = new StringBuilder();
    gamesWonKey.append(sizeKey);
    gamesWonKey.append("Games Won");
    return gamesWonKey.toString();
  }//getGamesWonKey


  private String getGamesLostKey(String sizeKey) {
    StringBuilder gamesLostKey = new StringBuilder();
    gamesLostKey.append(sizeKey);
    gamesLostKey.append("Games Lost");
    return gamesLostKey.toString();
  }//getGamesLostKey


  private String getGamesPlayedKey(String sizeKey) {
    StringBuilder gamesPlayedKey = new StringBuilder();
    gamesPlayedKey.append(sizeKey);
    gamesPlayedKey.append("Games Played");
    return gamesPlayedKey.toString();
  }//getGamesPlayedKey


  private String getHighScoreKey(String sizeKey) {
    StringBuilder highScoreKey = new StringBuilder();
    highScoreKey.append(sizeKey);
    highScoreKey.append("High Score");
    return highScoreKey.toString();
  }//getHighScoreKey


  private void incrementGamesWon(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getGamesWonKey(sizeKey);
    Integer gamesWon = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(key, gamesWon + 1);
    editor.apply();
  }//incrementGamesWon


  private void incrementGamesLost(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String gamesLost = getGamesLostKey(sizeKey);
    Integer score = stats.getInt(gamesLost, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(gamesLost, score + 1);
    editor.apply();
  }//incrementGamesLost


  private void incrementGamesPlayed(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getGamesPlayedKey(sizeKey);
    Integer gamesPlayed = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(key, gamesPlayed + 1);
  }//incrementGamesPlayed


  private void changeHighScore(String sizeKey, Integer score) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getHighScoreKey(sizeKey);
    Integer highScore = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    if(score > highScore) {
      editor.putInt(key, score);
    }
  }//changeHighScore


  private void changeStats(Integer width, Integer height, Boolean didPlayerOneWin,
                           Integer playerOneScore, Boolean didGameTie) {
    String sizeKey = getBoardSizeForKey(width, height);
    incrementGamesPlayed(sizeKey);
    changeHighScore(sizeKey, playerOneScore);
    if(!didGameTie) {
      if (didPlayerOneWin){
        incrementGamesWon(sizeKey);
      }
      else{
        incrementGamesLost(sizeKey);
      }
    }
  }//changeStats


  public Integer getGamesWon(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesWonKey(sizeKey), 0);
  }//getGamesWon

  public Integer getGamesLost(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesLostKey(sizeKey), 0);
  }//getGamesLost

  public Integer getGamesPlayed(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getGamesPlayedKey(sizeKey), 0);
  }//getGamesPlayed

  public Integer getHighScore(String sizeKey) {
    SharedPreferences stats = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    return stats.getInt(getHighScoreKey(sizeKey), 0);
  }//getHighScore
}
