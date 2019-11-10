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
    Fences wack = new Fences(1, 1, false, MainActivity.this);
    Fences wack2 = new Fences(1, 1, false, MainActivity.this);
    Fences wack3 = new Fences(1, 1, true, MainActivity.this);
    ConstraintLayout layout = findViewById(R.id.activity_main);

    wack.getButton().setX(50);
    wack.getButton().setY(75);

    wack2.getButton().setX(215);
    wack2.getButton().setY(75);
    wack3.getButton().setX(130);
    wack3.getButton().setY(155);
    layout.addView(wack.getButton());
    layout.addView(wack2.getButton());
    layout.addView(wack3.getButton());
  }

  public void goToSettings(View v) {
    Intent goToSettings = new Intent(getApplicationContext(), Settings.class);
    startActivity(goToSettings);
  } // goToSettings

  public void goToGameDisplay(View v) {
    Intent goToGameDisplay = new Intent(getApplicationContext(), GameDisplay.class);
    startActivity(goToGameDisplay);
  } // goToGameDisplay
}
