package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Fences wack = new Fences(1,1,false,MainActivity.this);
    Fences wack2 = new Fences(1,1,false,MainActivity.this);
    Fences wack3 = new Fences(1,1,false,MainActivity.this);
    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.activity_main);

    layout.addView(wack.getButton());
    layout.addView(wack2.getButton());
    layout.addView(wack3.getButton());

  }

  public void showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    popup.setOnMenuItemClickListener(this);
    popup.inflate(R.menu.popup_menu);
    popup.show();

  } // showPopup

  @Override
  public boolean onMenuItemClick(MenuItem item) {

    switch (item.getItemId()) {
      default:
        Intent goToBoard = new Intent(getApplicationContext(), Settings.class);
        this.startActivity(goToBoard);
        return true;
    } // switch
  } // onMenuItemClick


}
