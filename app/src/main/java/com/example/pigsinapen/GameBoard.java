package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
    }
    public void GoBackToMenu (View v){
        Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goBackToMainMenu);
    }//goBackToMenu
}
