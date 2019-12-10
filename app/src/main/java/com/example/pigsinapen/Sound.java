package com.example.pigsinapen;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Represents sound throughout the whole application
 *
 * @author Jared Matson
 *     <p>Methods: Sound(Context conttext);
 *     Constructor isSoundEnabled(); returns if sound is on or not
 *     enableSound(); Enables sound throughout the application
 *     disableSound(); Disables sound throughout the application
 *     initializePointScore(); Initializes pointScore sound mediaplayer
 *     pointScore(); play the pointScore sound
 *     initializeButtonClick(); Initializes buttonClick sound
 *     mediaplayer buttonClick(); play the buttonClick sound
 *     initalizeGameComplete(); Initializes gameComplete sound mediaplayer
 *     gameComplete(); play the gameCompelte sound;
 */
public class Sound {
  private Context context;
  private MediaPlayer scoreSound;
  private MediaPlayer buttonClick;
  private MediaPlayer gameComplete;
  private static boolean soundToggle = true;

  /**
   * Constructor
   *
   * @param context sets which activity sound is being initialized in
   */
  public Sound(Context context) {
    this.context = context;
  } // Sound

  /**
   * Will return if sound is enabled
   *
   * @return soundToggle
   */
  public boolean isSoundEnabled() {
    return soundToggle;
  } // isSoundEnabled

  /** will disable sound throughout the application */
  public void disableSound() {
    soundToggle = false;
  } // disableSound

  /** will enable sound throughout the application */
  public void enableSound() {
    soundToggle = true;
  } // enableSound

  /** Will initialize the pointScore MediaPlayer */
  public void initializePointScore() {
    scoreSound = MediaPlayer.create(context, R.raw.pointscored);
  } // initializePointScoredSound

  /** Will play the pointScore sound */
  public void pointScore() {
    if (soundToggle == true) {
      scoreSound.start();
    } // if
  } // pointScore

  /** Will initialize the buttonClick MediaPlayer */
  public void initializeButtonClick() {
    buttonClick = MediaPlayer.create(context, R.raw.buttonclicked3);
  } // initializeButtonClick

  /** Will play the buttonClick sound */
  public void buttonClick() {
    if (soundToggle == true) {
      buttonClick.start();
    } // if
  } // buttonClick

  /** Will initalize the gameComplete MediaPlayer */
  public void initializeGameComplete() {
    gameComplete = MediaPlayer.create(context, R.raw.gameoversound);
  } // initializeGameComplete

  /** Will complete the gameComplete sound */
  public void gameComplete() {
    if (soundToggle == true) {
      gameComplete.start();
    } // if
  } // gameComplete
} // Sound
