package com.example.pigsinapen;

public class ComputerPlayer extends Player {

  ComputerPlayer(String name, boolean currentPlayer) {
    super(name, currentPlayer);
  }//constructor

  /**
   *
   * @return
   */
  private Boolean checkFences(Integer width, Integer height, Boolean horizontal) {
    Integer boxesClosed;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        boxesClosed = board.checkBoxes(i, j, true);
        if (boxesClosed > 0) {
          //Set the button chosen to the 2nd player colour
          //Make the button un-clickable
          this.addToScore(boxesClosed);
          return true;
        }
      }
    }

    return false;
  }

}
