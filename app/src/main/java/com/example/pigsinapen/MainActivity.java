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



//    GameBoard gameBoard = new GameBoard(4, 7, MainActivity.this, );
//
//    gameBoard.getHorizontalFences()[0][1].setButtonClicked(true);
//
//    gameBoard.getVerticalFences()[0][1].setButtonClicked(true);
//    gameBoard.getVerticalFences()[0][2].setButtonClicked(true);
//
//    gameBoard.getHorizontalFences()[1][1].setButtonClicked(true);
//
//    int res = gameBoard.checkBoxes(1, 1, true);
//
//    System.out.println("Result is \n\n"+ res);

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
