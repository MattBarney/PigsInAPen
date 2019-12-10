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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Statistics extends AppCompatActivity {

  private static final String SHARED_PREF_NAME = "Statistics";


  Context context;

  // Collection of grid sizes the player can choose from
  private final String[] gridSizes = {"4x4", "5x4", "5x5", "6x5", "6x6"};

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
    SharedPreferences stats = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String key = getGamesWonKey(sizeKey);
    Integer gamesWon = stats.getInt(key, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(key, gamesWon + 1);
    editor.apply();
  }//incrementGamesWon

  private void incrementGamesLost(String sizeKey) {
    SharedPreferences stats = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    String gamesLost = getGamesLostKey(sizeKey);
    Integer score = stats.getInt(gamesLost, 0);
    SharedPreferences.Editor editor = stats.edit();
    editor.putInt(gamesLost, score + 1);
    editor.apply();
  }//incrementGamesLost

  

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);

    TextView gridSize = findViewById(R.id.gridSizeText);
    gridSize.setText(gridSizes[2]);
  }//onCreate

  /**
   * Changes activity to MainActivity
   *
   * @param v Button View
   */
  public void goBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  }
  /**
   * Increases or decreases the chosen grid size
   *
   * <p>Increases the grid size if the increaseGrid button was pressed, decreases the grid size if
   * the decreaseGrid button was pressed. If the first or last size option is currently being
   * displayed, hide the respective button to avoid exceptions.
   *
   * @param buttonPressed - The button that called this method, in this case either increaseGrid or
   *     decreaseGrid.
   */
  public void changeGridSize(View buttonPressed) {
    ImageButton increaseSize = findViewById(R.id.increaseGrid);
    ImageButton decreaseSize = findViewById(R.id.decreaseGrid);
    TextView gridSize = findViewById(R.id.gridSizeText);

    int currentSizeIndex = getCurrentSizeIndex();

    if (buttonPressed == increaseSize) {
      gridSize.setText(gridSizes[currentSizeIndex + 1]);

      // The decrease button becomes invisible if it would cause an exception
      // if it were pressed another time, increasing the index gets rid of that risk,
      // so make the button visible again.
      decreaseSize.setVisibility(View.VISIBLE);

      // If increasing the index one more time would go out of bounds, hide the button.
      if (currentSizeIndex + 2 >= gridSizes.length) {
        increaseSize.setVisibility(View.INVISIBLE);
      }

    } else { // buttonPressed == decreaseSize
      gridSize.setText(gridSizes[currentSizeIndex - 1]);

      // Same idea as above.
      increaseSize.setVisibility(View.VISIBLE);

      // If decreasing the index one more time would go out of bounds, hide the button.
      if (currentSizeIndex - 2 < 0) {
        decreaseSize.setVisibility(View.INVISIBLE);
      }
    }
  } // changeGridSize()

  /** Gets the location of the current grid size in gridSizes[]. */
  private Integer getCurrentSizeIndex() {
    TextView gridSize = findViewById(R.id.gridSizeText);

    int sizeIndex = 0;

    // The TextView's contents are set in onCreate from gridSizes[], since these contents are
    // never changed to something not in gridSizes[] we don't need to worry about the size not
    // being found.
    for (int i = 0; i < gridSizes.length; i++) {
      if (gridSize.getText() == gridSizes[i]) {
        sizeIndex = i;
      }
    }

    return sizeIndex;
  } // getCurrentSizeIndex()
}
