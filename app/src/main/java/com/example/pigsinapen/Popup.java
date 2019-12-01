/**
 * Popup.java
 *
 * This class shows the pop up window in the screen whoever wins the game, could also be tied game.
 *
 * @version 1.0
 * @since 2019-11-27
 */
package com.example.pigsinapen;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Popup extends Activity {
  /**
   * goBackMainMenu button is to go back to main menu
   */
  Button goBackMain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_popup);

    // whoWon TextView is to show the winner's name
    TextView whoWon = findViewById(R.id.whoWon);
    String name = getIntent().getStringExtra("player_name");
    whoWon.setText(name);

    // goBackMain button is getting its button id from XML code.
    goBackMain = (Button) findViewById(R.id.gobackMain);
    goBackMain.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
      }
    });

    ConstraintLayout layoutPopupWindow = findViewById(R.id.layoutPopupWindow);
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    float width = dm.widthPixels;
    float height = dm.heightPixels;

    // sets the popup window in the middle of the screen and a size defined by half of height and 1/10th of width
    getWindow().setLayout((int)(width * .9), (int)(height * .5));

    WindowManager.LayoutParams params = getWindow().getAttributes();
    params.gravity = Gravity.CENTER;

    getWindow().setAttributes(params);


  }
}
