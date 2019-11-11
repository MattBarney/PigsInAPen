/**
 * Settings.java
 *
 * <p>Activity for changing the type of game, names of the players, and size of the game board.
 *
 * <p>File and UI originally created by Jared Matson.
 *
 * <p>Instance variables, methods, and UI updates done by Matt Barney.
 *
 * <p>Methods
 */
package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.pigsinapen.R;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

  private boolean aiToggle;

  private String playerOneName;

  private String playerTwoName;

  private int width;

  private int height;

  // Collection of grid sizes the player can choose from
  private final String[] gridSizes = {"3x3", "4x3", "5x3", "4x4", "5x4"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    TextView gridSize = findViewById(R.id.gridSizeText);

    gridSize.setText(gridSizes[2]);
  }

  // BUTTON METHODS \\

  /**
   * Changes activity to MainActivity
   *
   * @param backButton The button that called this method
   */
  public void back(View backButton) {
    Intent goToMain = new Intent(this, MainActivity.class);
    startActivity(goToMain);
    finish();
  }

  /**
   * Switches the opponent to human or computer.
   *
   * <p>Checks which toggle button was pressed. If the human toggle button was pressed, toggle the
   * computer button off, make the player two name field visible, and sets the AI toggle to false.
   *
   * <p>If the computer toggle button is pressed, toggle the human button off, make the player two
   * name field invisible, and set the AI toggle to true.
   *
   * @param buttonPressed - Toggle button that called this method, in this case either humanToggle
   *     or computerToggle.
   */
  public void toggleOpponent(View buttonPressed) {
    ToggleButton toggleHuman = findViewById(R.id.humanToggle);
    ToggleButton toggleComputer = findViewById(R.id.computerToggle);
    EditText playerTwoName = findViewById(R.id.enterPlayerTwoName);

    if (buttonPressed == toggleHuman) {
      toggleHuman.setChecked(true);
      toggleComputer.setChecked(false);
      playerTwoName.setVisibility(View.VISIBLE);
    } else { // buttonPressed == toggleComputer
      toggleHuman.setChecked(false);
      toggleComputer.setChecked(true);
      playerTwoName.setVisibility(View.INVISIBLE);
    }
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
  }

  // HELPER METHODS \\

  /** Gets the location of the current grid size in gridSizes[]. */
  private int getCurrentSizeIndex() {
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
  }

  /**
   * Sets the AI toggle.
   *
   * <p>Checks which toggle button is currently activated and sets the ai toggle appropriately.
   */
  private void setAIToggle() {
    ToggleButton humanToggle = findViewById(R.id.humanToggle);

    if (humanToggle.isChecked()) {
      aiToggle = false;
    } else { // Computer toggle is checked.
      aiToggle = true;
    }
  }

  /** Sets width and height from the chosen size. */
  private void setGridSize() {
    TextView currentGridSize = findViewById(R.id.gridSizeText);

    CharSequence size = currentGridSize.getText();

    width = Character.getNumericValue(size.charAt(0));
    height = Character.getNumericValue(size.charAt(2));
  }
}
