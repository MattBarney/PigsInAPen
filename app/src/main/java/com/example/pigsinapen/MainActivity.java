/**
 * MainActivity.java
 *
 * <p>This file contains the code used for the "MainAcitivty" activity, which is
 * the main menu of the game
 *
 * <p>Methods:
 *    - onCreate(Bundle)
 *        Sets up the activity.
 *    - goToSettings(View)
 *        Switches the activity to Settings.
 *    - goToGameDisplay(View)
 *        Switches the activity to GameDisplay.
 *    - goToStatistics(View)
 *        Switches the activity to Statistics.
 */

package com.example.pigsinapen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  Sound sound;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    sound = new Sound(this);
    sound.initializeButtonClick();
  } // onCreate


  /**
   * This activity shows the Settings activity.
   * @param v Button View
   */
  public void goToSettings(View v) {
    sound.buttonClick();
    Intent goToSettings = new Intent(getApplicationContext(), Settings.class);
    startActivity(goToSettings);
    finish();
  } // goToSettings

  /**
   * This activity shows the QuickPlay game mode activity.
   * @param v Button View
   */
  public void goToGameDisplay(View v) {
    sound.buttonClick();
    Intent goToGameDisplay = new Intent(getApplicationContext(), GameDisplay.class);
    startActivity(goToGameDisplay);
    finish();
  } // goToGameDisplay

  /**
   * This activity shows the statistics activity.
   * @param v Button View
   */
  public void goToStatistics(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), ActivityStatistics.class);
    startActivity(goBackToMainMenu);
    finish();
  } // goBackToStatistics
}
