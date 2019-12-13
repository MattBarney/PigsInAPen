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
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  Sound sound;
  private ImageView disableSoundButton3;
  private ImageView enableSoundButton3;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    sound = new Sound(this);
    sound.initializeButtonClick();
    disableSoundButton3 = findViewById(R.id.disableSoundButton3);
    enableSoundButton3 = findViewById(R.id.enableSoundButton3);
    if (sound.isSoundEnabled() == true) {
      disableSoundButton3.setVisibility(View.VISIBLE);
      enableSoundButton3.setVisibility(View.INVISIBLE);
    } else {
      disableSoundButton3.setVisibility(View.INVISIBLE);
      enableSoundButton3.setVisibility(View.VISIBLE);
    } // else
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
  /**
   * Disables the volume throughout the application.
   *
   * @param v the disable volume image being tapped
   */
  public void disableVolume(View v) {
    sound.disableSound();
    disableSoundButton3.setVisibility(View.INVISIBLE);
    enableSoundButton3.setVisibility(View.VISIBLE);
  } // disableVolume

  /**
   * Enables the volume throughout the application.
   *
   * @param v the enable volume image being tapped
   */
  public void enableVolume(View v) {
    sound.enableSound();
    disableSoundButton3.setVisibility(View.VISIBLE);
    enableSoundButton3.setVisibility(View.INVISIBLE);
  } // enableVolume
}
