package com.dantasse.onephotoeveryminute;

import android.graphics.Bitmap;

public class UiModel {

  public enum State {
    NOT_TAKING_PHOTOS,
    TAKING_PHOTOS
  }
  
  private MainActivity view;
  private State currentState = State.NOT_TAKING_PHOTOS;
  private int photoCount = 0;
  private Bitmap currentImage;
  
  public UiModel(MainActivity view) {
    this.view = view;
  }
  
  public void setCurrentState(State newState) {
    this.currentState = newState;
    view.update();
  }
  public State getCurrentState() {
    return currentState;
  }
  
  public void incrementPhotoCount() {
    photoCount++;
    view.update();
  }
  public int getPhotoCount() {
    return photoCount;
  }
  
  public void setCurrentImage(Bitmap newImage) {
    this.currentImage = newImage;
    view.update();
  }
  public Bitmap getCurrentImage() {
    return currentImage;
  }
}
