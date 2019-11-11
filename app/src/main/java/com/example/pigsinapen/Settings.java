/**
 * Settings.java
 *
 * Activity for changing the type of game, names of the players, and size of the game board.
 * <p>
 * File and UI originally created by Jared Matson.
 * Instance variables, methods, and UI updates done by Matt Barney.
 * <p>
 * Methods
 * -
 * -
 */
package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.pigsinapen.R;

public class Settings extends AppCompatActivity {

  private boolean aiToggle;

  private String playerOneName;

  private String playerTwoName;

  private int width;

  private int height;

  //Collection of grid sizes the player can choose from
  private final String[] gridSizes = {"3x3", "4x3", "5x3", "4x4", "5x4"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
  }

  /**
   * Sets game to versus human.
   *
   * When toggled this sets the game to versus a human, displays the player two name field and
   * toggles the versus computer option off.
   *
   * @param humanToggleButton Toggle button that activates this method.
   */
  public void toggleHumanOpponent(View humanToggleButton) {
    ToggleButton toggleHuman = findViewById(R.id.humanToggle);
    ToggleButton toggleComputer = findViewById(R.id.computerToggle);
    EditText playerTwoName = findViewById(R.id.enterPlayerTwoName);

    toggleHuman.setChecked(true);
    toggleComputer.setChecked(false);
    playerTwoName.setVisibility(View.VISIBLE);
    aiToggle = false;
  }

  /**
   * Sets game to versus computer.
   *
   * When toggled this sets the game to versus a computer, hides the player two name field and
   * toggles the versus human option off.
   *
   * @param computerToggleButton Toggle button that activates this method.
   */
  public void toggleComputerOpponent(View computerToggleButton) {
    ToggleButton toggleComputer = findViewById(R.id.computerToggle);
    ToggleButton toggleHuman = findViewById(R.id.humanToggle);
    EditText playerTwoName = findViewById(R.id.enterPlayerTwoName);

    toggleComputer.setChecked(true);
    toggleHuman.setChecked(false);
    playerTwoName.setVisibility(View.INVISIBLE);
    aiToggle = true;
  }

}
