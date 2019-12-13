/**
 * Popup.java
 *
 * <p>This class shows the pop up window in the screen whoever wins the game, could also display
 * game tied.
 *
 * @version 1.0
 * @since 2019-11-27
 */

package com.example.pigsinapen;

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
  /** goBackMainMenu button is to go back to main menu. */
  Button goBackMain;

  /**
   * When program starts. 1) Set up String object to show what to display from Game Display activity
   * 2) show the String a window along with Game Over TextView and Main Menu Button
   *
   * @param savedInstanceState
   */
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
    goBackMain.setOnClickListener(
        new View.OnClickListener() {

          @Override
          public void onClick(View view) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
          }
        });

    // Get User's Phone screen size such as width and height
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    float width = dm.widthPixels;
    float height = dm.heightPixels;

    // sets the popup window in the middle of the screen and
    // a size is defined by 1/2 of height and 2/10th of width
    getWindow().setLayout((int) (width * .9), (int) (height * .4));

    WindowManager.LayoutParams params = getWindow().getAttributes();
    params.gravity = Gravity.CENTER;

    getWindow().setAttributes(params);
  }
}
