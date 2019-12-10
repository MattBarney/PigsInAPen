
package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Statistics extends AppCompatActivity {

  private static final String SHARED_PREF_NAME = "Statistics";


  // Collection of grid sizes the player can choose from
  private final String[] gridSizes = {"4x4", "5x4", "5x5", "6x5", "6x6"};

  private static String getBoardSizeForKey(Integer width, Integer height) {
    StringBuilder key = new StringBuilder();
    key.append(width);
    key.append("x");
    key.append(height);
    return key.toString();
  }

  private static String getGamesWonKey(String sizeKey) {
    StringBuilder gamesWonKey = new StringBuilder();
    gamesWonKey.append(sizeKey);
    gamesWonKey.append("Games Won");
    return gamesWonKey.toString();
  }

  private static String getGamesLostKey(String sizeKey) {
    StringBuilder gamesLostKey = new StringBuilder();
    gamesLostKey.append(sizeKey);
    gamesLostKey.append("Games Lost");
    return gamesLostKey.toString();
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);

    TextView gridSize = findViewById(R.id.gridSizeText);
    gridSize.setText(gridSizes[2]);
  }//onCreate


}

