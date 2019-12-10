package com.example.pigsinapen;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class Sound {
  private Context context;
  private MediaPlayer scoreSound;
  private MediaPlayer buttonClick;
  private MediaPlayer backGroundMusic;
  private MediaPlayer gameComplete;
  private boolean soundToggle = true;

 public Sound(Context context){
   this.context = context;
 }//Sound
  public void disableSound(View v){
    soundToggle = false;
  }
  public void enableSound(View v){
   soundToggle = true;
  }
  public void initializePointScore(){
    scoreSound = MediaPlayer.create(context, R.raw.pointscored);
  }//pointScoredSound

  public void pointScore(){
    if (soundToggle == true) {
      scoreSound.start();
    }//if
  }

  public void backGroundMusic(){
    final MediaPlayer scoreSound = MediaPlayer.create(context, R.raw.pointscored);
    scoreSound.start();
    scoreSound.release();
  }//backGroundMusic

  public void initializeButtonClick(){
    buttonClick = MediaPlayer.create(context, R.raw.buttonclicked3);
  }//initializeButtonClick
  public void buttonClick(){
    if (soundToggle == true) {
      buttonClick.start();
    }
  }//buttonClick

  public void initializeGameComplete(){
   gameComplete = MediaPlayer.create(context, R.raw.gameoversound);
  }//initializeGameComplete
  public void gameComplete(){
    if (soundToggle == true) {
      gameComplete.start();
    }

  }//gameComplete
}//Sound
