package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class statistics extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);
  }


  /**
   * Changes activity to MainActivity
   *
   * @param v The button that called this method
   */
  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu
  /**
   * Increases or decreases the chosen grid size
   *
   * <p>Increases the grid size if the increaseGrid button was pressed, decreases the grid size if
   * the decreaseGrid button was pressed. If the first or last size option is currently being
   * displayed, hide the respective button to avoid exceptions.
   *
   * @param buttonPressed - The button that called this method, in this case either increaseGrid or
   *     decreaseGrid.
   */
  public void changeGridSize(View buttonPressed) {
    ImageButton increaseSize = findViewById(R.id.increaseGrid);
    ImageButton decreaseSize = findViewById(R.id.decreaseGrid);
    TextView gridSize = findViewById(R.id.gridSizeText);

    int currentSizeIndex = getCurrentSizeIndex();

    if (buttonPressed == increaseSize) {
      gridSize.setText(gridSizes[currentSizeIndex + 1]);

      // The decrease button becomes invisible if it would cause an exception
      // if it were pressed another time, increasing the index gets rid of that risk,
      // so make the button visible again.
      decreaseSize.setVisibility(View.VISIBLE);

      // If increasing the index one more time would go out of bounds, hide the button.
      if (currentSizeIndex + 2 >= gridSizes.length) {
        increaseSize.setVisibility(View.INVISIBLE);
      }

    } else { // buttonPressed == decreaseSize
      gridSize.setText(gridSizes[currentSizeIndex - 1]);

      // Same idea as above.
      increaseSize.setVisibility(View.VISIBLE);

      // If decreasing the index one more time would go out of bounds, hide the button.
      if (currentSizeIndex - 2 < 0) {
        decreaseSize.setVisibility(View.INVISIBLE);
      }
    }
  }
  /** Gets the location of the current grid size in gridSizes[]. */
  private Integer getCurrentSizeIndex() {
    TextView gridSize = findViewById(R.id.gridSizeText);

    int sizeIndex = 0;

    // The TextView's contents are set in onCreate from gridSizes[], since these contents are
    // never changed to something not in gridSizes[] we don't need to worry about the size not
    // being found.
    for (int i = 0; i < gridSizes.length; i++) {
      if (gridSize.getText() == gridSizes[i]) {
        sizeIndex = i;
      }
    }

    return sizeIndex;
  }
}
