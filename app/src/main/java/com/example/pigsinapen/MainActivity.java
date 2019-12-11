package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  Sound sound;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    sound = new Sound(this);
    sound.initializeButtonClick();

  }// onCreate


  /**
   * This activity shows the Settings activity
   * @param v Button View
   */
  public void goToSettings(View v) {
    sound.buttonClick();
    Intent goToSettings = new Intent(getApplicationContext(), Settings.class);
    startActivity(goToSettings);
    finish();
  } // goToSettings

  /**
   * This activity shows the QuickPlay game mode activity
   * @param v Button View
   */
  public void goToGameDisplay(View v) {
    sound.buttonClick();
    Intent goToGameDisplay = new Intent(getApplicationContext(), GameDisplay.class);
    startActivity(goToGameDisplay);
    finish();
  } // goToGameDisplay

  /**
   * This activity shows the statistics activity
   * @param v Button View
   */
  public void goToStatistics(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), ActivityStatistics.class);
    startActivity(goBackToMainMenu);
    finish();
  } // goBackToStatistics
}
