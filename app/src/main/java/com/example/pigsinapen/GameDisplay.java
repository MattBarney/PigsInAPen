package com.example.pigsinapen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameDisplay extends AppCompatActivity implements View.OnClickListener {

  LinearLayout game_board_layout;

  Player player1, player2;
  GameBoard gameBoard;
  Fences fence;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_display);



    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    int density  = (int)getResources().getDisplayMetrics().density;
    int screenHeight = dm.heightPixels / density;
    int screenWidth  = dm.widthPixels / density;





    // 1080 width
    // row = 5 col = 6
    int row = 5, col = 6;

    int leftpadding = screenWidth - 15;
    int rightpadding = screenHeight - 15;
    int dots_fences_dots = screenWidth - 30; // 330

    int oneFencesSize = density * (dots_fences_dots - ((col - 1) * 10)) / (col - 1); // is current dot's width size, (radius size maybe)

    System.out.println(oneFencesSize);

    gameBoard = new GameBoard(5,6,GameDisplay.this, this, 30, oneFencesSize);




    RelativeLayout relativeLayout = new RelativeLayout(this);
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    relativeLayout.setLayoutParams(params);
    relativeLayout.setBackgroundColor(Color.WHITE);

    //for (int i = 0; i < (row + (row - 1)); i++) {

      ImageView dot = new ImageView(this);
      dot.setBackgroundResource(R.drawable.dot_10_10);

      RelativeLayout.LayoutParams dot_params =
        new RelativeLayout.LayoutParams(
          RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
      dot_params.addRule(RelativeLayout.ALIGN_LEFT);

      dot.setLayoutParams(dot_params);


      RelativeLayout.LayoutParams horizontal_params =
        gameBoard.getHorizontalFences()[0][2].getFenceButtonParams();
      horizontal_params.addRule(RelativeLayout.ALIGN_RIGHT, dot.getId());
      horizontal_params.setMargins(30, 0, 0, 0);

      gameBoard.getHorizontalFences()[0][0].getFenceButton().setLayoutParams(horizontal_params);







      relativeLayout.addView(dot);
      relativeLayout.addView(gameBoard.getHorizontalFences()[0][2].getFenceButton());





    //gameBoard.getHorizontalFences()[0][0].getFenceButton().setLayoutParams(params1);



//    ImageView dot = new ImageView(this);
//    dot.setBackgroundResource(R.drawable.dot_10_10);
//    RelativeLayout.LayoutParams dot_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//
//    dot_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//    dot.setLayoutParams(dot_params);
//
//
//
//
//    RelativeLayout.LayoutParams hori_params = gameBoard.getHorizontalFences()[0][0].getFenceButtonParams();
//
//    //hori_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
//    hori_params.addRule(RelativeLayout.ALIGN_RIGHT, dot.getId());
//    //hori_params.addRule(RelativeLayout.ALIGN_BASELINE, dot.getId());
//    hori_params.setMargins(30,0,0,0);
//
//
//    gameBoard.getHorizontalFences()[0][0].getFenceButton().setLayoutParams(hori_params);







    //relativeLayout.addView(dot);
    //relativeLayout.addView(gameBoard.getHorizontalFences()[0][0].getFenceButton());


    //relativeLayout.setGravity(3);



    //relativeLayout.addView(fences.getFenceButton());







    // outside constraint layout adds only relative layout.
    ConstraintLayout constraintLayout = findViewById(R.id.layout);
    constraintLayout.addView(relativeLayout);


//    int i = 0, j = 1;
//    Fences fences = new Fences(0,0, true, this, this, 30, 400);
//    Fences fences2 = new Fences(0,0, true, this, this, 30, 400);
//    fences.getButton().setId(i); fences2.getButton().setId(j);
//
//
//    ImageView dot = new ImageView(this);
//    dot.setBackgroundResource(R.drawable.dot_10_10);
//
//    ConstraintLayout layout = findViewById(R.id.layout);
//
//
//    layout.addView(fences.getFenceButton());
//    layout.addView(fences2.getFenceButton());
//    layout.addView(dot);



    //int wid = layout.getMaxHeight()/3;
    //System.out.println("this is the layout width "+wid);

    player1 = new Player("Player 1", 0, true);
    player2 = new Player("Player 2", 0, false);



  }

  //  back button
  public void GoBackToMenu(View v) {
    Intent goBackToMainMenu = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(goBackToMainMenu);
  } // goBackToMenu

  @Override
  public void onClick(View v) {}

  // public void setFences(Integer height, Integer width){
  //   Button buttonReset = findViewById(R.id.)
  // -----------set horizontal fences------------------
  //   for (int i = 0; i <= height; i++){
  //    for (int j = ; j <= width){
  //
  //    }
  //   }
  //
  // ------------set vertical fences--------------------
  //  for (int i = 0; i <= height-1; i++){
  //    for (int j = 0; j <= width; j++){
  //
  //    }
  //
  //  }
  // }
  // ------------game display----------------
  public void playerTurn(int row, int col, boolean horizontal) {
    Player currentPlayer = getCurrentPlayer();
    Player otherPlayer = getOtherPlayer();
    Integer closedBoxes = gameBoard.checkBoxes(row, col, horizontal);
    if (closedBoxes > 0) currentPlayer.addToScore(closedBoxes);
    else currentPlayer = getCurrentPlayer();
    otherPlayer = getOtherPlayer();
    checkGameEnd();
    System.out.println(row + " " + col + " " + horizontal);
  }

  Player getCurrentPlayer() {
    if (player1.checkCurrentPlayer()) return player1;
    else return player2;
  }

  Player getOtherPlayer() {
    if (player1.checkCurrentPlayer()) return player2;
    else return player1;
  }

  void checkGameEnd() {
    Integer currentScore = player1.getScore() + player2.getScore();
    if (currentScore == gameBoard.getMaxScore()) displayWinner();
  }

  void displayWinner() {
    if (player1.getScore() > player2.getScore()){
    //if(true){
      Toast.makeText(GameDisplay.this, player1.getName() + "wins", Toast.LENGTH_SHORT).show();
      Intent indent = new Intent(getApplicationContext(), Popup.class);
      indent.putExtra("player_name" , player1.getName());
      startActivity(indent);
    }
    else if (player1.getScore() < player2.getScore()) {
      Toast.makeText(GameDisplay.this, player2.getName() + "wins", Toast.LENGTH_SHORT).show();
      Intent indent = new Intent(getApplicationContext(), Popup.class);
      indent.putExtra("player_name" , player2.getName());
      startActivity(indent);
    }
    else {
//      Intent indent = new Intent(getApplicationContext(), Popup.class);
//      indent.putExtra("player_name", "AI ");
//      startActivity(indent);
}
    //displayWinner
  }

  // ------CALL QUIT and REPLAY--------
  void quit(){

  }

  void replay(){

  }

/*

  void createHorizontalFencesandDots(){
    int putFenceX = 0;
    int putFenceY = 333;
    int putButtonX = 0;
    int putButtonY = 375;

    for (int j = 0; j < 6; j += 1) {
      putFenceX = 200;
      putFenceY += 175;
      putButtonX = 105;
      putButtonY += 175;
      for (int i = 0; i < 5; i += 1) {
        ConstraintLayout layout = findViewById(R.id.activity_game_display);
        Fences fence = new Fences(j, i, true, GameDisplay.this, this, 15, 120);
        fence.getButton().setX(putFenceX);
        fence.getButton().setY(putFenceY);
        layout.addView(fence.getButton());
        putFenceX += 170;

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
  void createVerticalFences(){
    int putFenceXVert = 0;
    int putFenceYVert = 415;

    for(int j = 0; j < 5; j += 1){
      putFenceXVert = 113;
      putFenceYVert += 175;
      for (int i = 0; i < 6; i += 1) {
        ConstraintLayout layout = findViewById(R.id.activity_game_display);
        Fences fence = new Fences(j, i, false, GameDisplay.this, this, 15, 120);
        fence.getButton().setX(putFenceXVert);
        fence.getButton().setY(putFenceYVert);
        layout.addView(fence.getButton());
        putFenceXVert += 170;

      } // forfences
    }//for
  }
*/


}
/*

    Fences fences = new Fences(0,0, true, this, this, 30, 400);
    Fences fences2 = new Fences(0,0, false, this, this, 30, 400);


 */