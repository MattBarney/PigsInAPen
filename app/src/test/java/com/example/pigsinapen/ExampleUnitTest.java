package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends AppCompatActivity {
  GameBoard gameBoard;
  GameDisplay gameDisplay;
  @Test
  public void isPointRecordedOnFirstMove() {
    gameDisplay = new GameDisplay();
    gameBoard = new GameBoard(1,1,ExampleUnitTest.this, gameDisplay);
    assertEquals(0, (long)gameBoard.checkBoxes(0,0, true));
  }
}
