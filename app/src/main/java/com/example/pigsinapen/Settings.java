/**
 * Settings.java
 *
 * <p>Activity for changing the type of game, names of the players, and size of the game board.
 *
 * <p>File and UI originally created by Jared Matson.
 *
 * <p>Instance variables, methods, and UI updates done by Matt Barney.
 *
 * <p>Changes to be made: Uncomment lines from play() once GameDisplay is implemented.
 *
 * <p>Methods
 */
package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.pigsinapen.R;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

  private Boolean aiToggle;

  private String playerOneName;

  private String playerTwoName;

  private Integer width;

  private Integer height;

  Sound sound;

  // Collection of grid sizes the player can choose from
  private final String[] gridSizes = {"4x4", "5x4", "5x5", "6x5", "6x6"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    sound = new Sound(this);
    sound.initializeButtonClick();
    TextView gridSize = findViewById(R.id.gridSizeText);
    ToggleButton computerToggle = findViewById(R.id.computerToggle);
    EditText playerTwoNameField = findViewById(R.id.enterPlayerTwoName);

    gridSize.setText(gridSizes[2]);

    // Start this off as true so we don't have to check if the player chose an option or not.
    computerToggle.setChecked(true);
    playerTwoNameField.setVisibility(View.INVISIBLE); // Hide this since we start with computer on
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
    sound.buttonClick();
    if (buttonPressed == toggleHuman) {
      toggleHuman.setChecked(true);
      toggleComputer.setChecked(false);
      toggleHuman.setBackgroundColor(getResources().getColor(R.color.buttonHighlight));
      toggleComputer.setBackgroundColor(getResources().getColor(R.color.buttonColor));
      playerTwoName.setVisibility(View.VISIBLE);
    } else { // buttonPressed == toggleComputer
      toggleHuman.setChecked(false);
      toggleComputer.setChecked(true);
      toggleHuman.setBackgroundColor(getResources().getColor(R.color.buttonColor));
      toggleComputer.setBackgroundColor(getResources().getColor(R.color.buttonHighlight));
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
    sound.buttonClick();
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

  /**
   * Changes the activity to GameDisplay.
   *
   * <p>Sets each instance variable to what is currently input on the activity and prepares them to
   * be sent to GameDisplay.
   *
   * @param playButton The button that called this method.
   */
  public void play(View playButton) {
    Intent startGame = new Intent(this, GameDisplay.class);
    sound.buttonClick();
    // The names are the only thing the user could cause problems with, so if there is an issue
    // don't start the game.
    if (checkPlayerNames()) {
      setAIToggle();
      setPlayerNames();
      setGridSize();
      startGame.putExtra("AI_TOGGLE", aiToggle.toString());
      startGame.putExtra("PLAYER_ONE_NAME", playerOneName);
      startGame.putExtra("PLAYER_TWO_NAME", playerTwoName);
      startGame.putExtra("WIDTH", width.toString());
      startGame.putExtra("HEIGHT", height.toString());
      startActivity(startGame);
      finish();
    }
  }

  // HELPER METHODS \\

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

  /**
   * Checks if the player names are valid.
   *
   * <p>A valid player name is a name that has characters other than whitespace. If there is an
   * invalid name display an error message.
   *
   * @return True if the two names entered are valid, false otherwise.
   */
  private Boolean checkPlayerNames() {
    EditText playerOneNameField = findViewById(R.id.enterPlayerOneName);
    EditText playerTwoNameField = findViewById(R.id.enterPlayerTwoName);
    TextView errorMessage = findViewById(R.id.nameError);

    String possiblePlayerOneName = playerOneNameField.getText().toString();
    String possiblePlayerTwoName = playerTwoNameField.getText().toString();

    if (possiblePlayerOneName.trim().isEmpty()) { // Name is all whitespace.
      // Display the error message
      errorMessage.setVisibility(View.VISIBLE);
      return false;
    } else if (possiblePlayerTwoName.trim().isEmpty()) { // Same idea as above
      errorMessage.setVisibility(View.VISIBLE);
      return false;
    } else {
      // If we get to this point both names had more than just whitespace characters,
      // so we can hide the error message.
      errorMessage.setVisibility(View.INVISIBLE);
      return true;
    }
  }

  /**
   * Sets the player names to what was entered.
   *
   * <p>Checks the name input boxes to set the player names. If a name field is only whitespace the
   * name will be set to the default hint text of that EditText. If the computer is enabled the
   * playerTwoName variable is set to "Computer".
   */
  private void setPlayerNames() {
    EditText playerOneNameField = findViewById(R.id.enterPlayerOneName);
    EditText playerTwoNameField = findViewById(R.id.enterPlayerTwoName);

    if (playerOneNameField.getText().toString().trim().isEmpty()) { // Name is all whitespace
      playerOneName = playerOneNameField.getHint().toString();
    } else { // Name has non-whitespace characters
      playerOneName = playerOneNameField.getText().toString().trim();
    }

    // If the computer player is toggled, set the name to "Computer"
    if (aiToggle) {
      playerTwoName = "Computer";
    } else { // Otherwise get the name for player two
      if (playerTwoNameField.getText().toString().trim().isEmpty()) { // Same idea as above.
        playerTwoName = playerTwoNameField.getHint().toString();
      } else {
        playerTwoName = playerTwoNameField.getText().toString().trim();
      }
    }
  }

  /** Sets width and height from the chosen size. */
  private void setGridSize() {
    TextView currentGridSize = findViewById(R.id.gridSizeText);

    CharSequence size = currentGridSize.getText();

    width = Character.getNumericValue(size.charAt(0));
    height = Character.getNumericValue(size.charAt(2));
  }

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
  }
}
