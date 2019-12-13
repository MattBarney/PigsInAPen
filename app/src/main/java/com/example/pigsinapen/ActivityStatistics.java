/**
 * ActivityStatistics.java
 *
 * <p>This activity is for showing the statistics based on grid size. Every board has their own
 * statistics result. It will show the Games Won, Games Lost, Games Played, Highest Score.
 *
 * <p>ChangedGridSize method is taken from Settings class, other methods are freshly written.
 */

package com.example.pigsinapen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityStatistics extends AppCompatActivity {

  // Collection of grid sizes the player can choose from
  private final String[] gridSizes = {"4x4", "5x4", "5x5", "6x5", "6x6"};

  private Statistics statistics;

  /**
   * When program starts. 1) Set up grid size String to show the current grid size 2) Loads all the
   * strings Games won, games lost, games played, highest score
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);

    this.statistics = new Statistics(this);
    TextView gridSize = findViewById(R.id.gridSizeText);
    gridSize.setText(gridSizes[2]);

    updateStatistics(gridSizes[2]);
  }

  /**
   * Changes current activity to MainActivity activity.
   *
   * @param v Button View
   */
  public void goBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  }

  /**
   * Increases or decreases the chosen grid size.
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
      updateStatistics(gridSizes[currentSizeIndex + 1]);

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
      updateStatistics(gridSizes[currentSizeIndex - 1]);

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

  /**
   * Updates statistics on the screen based on their chosen grid size.
   *
   * @param gridIndex String value
   */
  private void updateStatistics(String gridIndex) {
    setGamesWon(gridIndex);
    setGamesLost(gridIndex);
    setGamesPlayed(gridIndex);
    setHighestScore(gridIndex);
  }

  /**
   * Gets Games Won on a specific grid size.
   *
   * @param gridIndex String value
   */
  private void setGamesWon(String gridIndex) {
    TextView gamesWon = findViewById(R.id.gamesWon);
    gamesWon.setText(statistics.getGamesWon(gridIndex).toString());
  }

  /**
   * Gets Games Lost on on a specific grid size.
   *
   * @param gridIndex String value
   */
  private void setGamesLost(String gridIndex) {
    TextView gamesWon = findViewById(R.id.gamesLost);
    gamesWon.setText(statistics.getGamesLost(gridIndex).toString());
  }

  /**
   * Gets Games Played on a specific grid size.
   *
   * @param gridIndex String value
   */
  private void setGamesPlayed(String gridIndex) {
    TextView gamesWon = findViewById(R.id.gamesPlayed);
    gamesWon.setText(statistics.getGamesPlayed(gridIndex).toString());
  }

  /**
   * Gets Highest Score on a specific grid size.
   *
   * @param gridIndex String value
   */
  private void setHighestScore(String gridIndex) {
    TextView gamesWon = findViewById(R.id.highestScore);
    gamesWon.setText(statistics.getHighScore(gridIndex).toString());
  }
}
