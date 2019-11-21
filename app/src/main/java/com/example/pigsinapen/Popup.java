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

  Button gobackMain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_popup);

    TextView whoWon = findViewById(R.id.whoWon);
    String name = getIntent().getStringExtra("player_name");
    name = name + " Wins !";
    whoWon.setText(name);

    gobackMain = (Button) findViewById(R.id.gobackMain);
    gobackMain.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

      }
    });

    ConstraintLayout layout = findViewById(R.id.layout);
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    float width = dm.widthPixels;
    float height = dm.heightPixels;

    getWindow().setLayout((int)(width * .8), (int)(height * .4));


    WindowManager.LayoutParams params = getWindow().getAttributes();

    params.gravity = Gravity.CENTER;


    getWindow().setAttributes(params);


  }
}
