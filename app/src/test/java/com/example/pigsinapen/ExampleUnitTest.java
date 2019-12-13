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
  public void noScoreHorizontalFence() {
    gameDisplay = new GameDisplay();
    gameBoard = new GameBoard(1,1,ExampleUnitTest.this, gameDisplay);
    assertEquals(0, (long)gameBoard.checkBoxes(0,0, true));
  }
  @Test
  public void noScoreVerticalFence() {
    gameDisplay = new GameDisplay();
    gameBoard = new GameBoard(3,2,ExampleUnitTest.this, gameDisplay);
    assertEquals(0, (long)gameBoard.checkBoxes(0,0, false));
  }
  

  @Test
  public void testStatisticsGamesLossUpating() {
    Statistics stats = new Statistics(gameDisplay);
    Integer gamesWon = stats.getGamesWon("5x5");
    stats.changeStats(5, 5, false, 0, false);
    assertEquals(gamesWon + 1, (long)stats.getGamesWon("5x5"));
  }

  @Test
  public void testStatisticsGamesWonUpdating() {
    Statistics stats = new Statistics(gameDisplay);
    Integer gamesWon = stats.getGamesWon("5x5");
    stats.changeStats(5, 5, true, 0, false);
    assertEquals(gamesWon + 1, (long) stats.getGamesWon("5x5"));
  }



}
