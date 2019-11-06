package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();

    }//showPopup

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
             default:
                 Intent goToBoard = new Intent(getApplicationContext(), Settings.class);
                 this.startActivity(goToBoard);
                 return true;

        }//switch

    }//onMenuItemClick

}
