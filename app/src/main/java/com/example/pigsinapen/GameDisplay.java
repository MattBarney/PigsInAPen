package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.sql.SQLOutput;


public class GameDisplay extends AppCompatActivity {
  //will have to dynamically initialize each fence before the onCreate I think
  Fences wack4;
  int putFenceYVert = 419;
  int putFenceXVert = 250;
  int putFenceY = 333;
  int putFenceX = 250;
  int putButtonY = 375;
  int putButtonX = 250;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);
    for(int j = 0; j < 5; j += 1){
      putFenceXVert = 113;
      putFenceYVert += 175;
      for (int i = 0; i < 6; i += 1) {
        ConstraintLayout layout = findViewById(R.id.activity_game_display);
        Fences wack = new Fences(j, i, true, GameDisplay.this, this);
        wack.getButton().setX(putFenceXVert);
        wack.getButton().setY(putFenceYVert);
        layout.addView(wack.getButton());
        putFenceXVert += 170;
        //wack.getButton().setOnClickListener(getOnClickDoSomething(wack));
      } // forfences
    }//for
    for (int j = 0; j < 6; j += 1) {
      putFenceX = 200;
      putFenceY += 175;
      putButtonX = 105;
      putButtonY += 175;
      for (int i = 0; i < 5; i += 1) {
        ConstraintLayout layout = findViewById(R.id.activity_game_display);
        Fences wack = new Fences(j, i, false, GameDisplay.this, this);
        wack.getButton().setX(putFenceX);
        wack.getButton().setY(putFenceY);
        layout.addView(wack.getButton());
        putFenceX += 170;
        //wack.getButton().setOnClickListener(getOnClickDoSomething(wack));
      } // forHorfences
      for(int i = 0; i < 6; i += 1){
        ConstraintLayout layout = findViewById(R.id.activity_game_display);
        ImageView dot = new ImageView(this);
        dot.setX(putButtonX);
        dot.setY(putButtonY);
        dot.setBackgroundResource(R.drawable.dotnew);
        layout.addView(dot);
        android.view.ViewGroup.LayoutParams layoutParams = dot.getLayoutParams();
        layoutParams.width = 35;
        layoutParams.height = 35;
        putButtonX += 170;
        dot.setLayoutParams(layoutParams);
      }//fordot
      }//outerfor

  }
  public void playerTurn(int row, int col, boolean isVertical){
    System.out.println(row + " " + col + " " + isVertical);
  }//playerTurn
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu


}
