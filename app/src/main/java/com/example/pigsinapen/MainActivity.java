package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }// onCreate
  
  
  
  public void goToSettings(View v) {
    Intent goToSettings = new Intent(getApplicationContext(), Settings.class);
    startActivity(goToSettings);
  } // goToSettings

  public void goToGameDisplay(View v) {
    Intent goToGameDisplay = new Intent(getApplicationContext(), GameDisplay.class);
    startActivity(goToGameDisplay);
  } // goToGameDisplay


  public void stat(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), Statistics.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu
}
