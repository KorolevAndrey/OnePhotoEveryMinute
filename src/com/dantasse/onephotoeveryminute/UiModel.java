package com.dantasse.onephotoeveryminute;

public class UiModel {

  public enum State {
    NOT_TAKING_PHOTOS,
    TAKING_PHOTOS
  }
  
  private MainActivity view;
  private State currentState = State.NOT_TAKING_PHOTOS;
  private int photosTaken = 0;
  
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
  
  public void takePhoto() {
    photosTaken++;
    view.update();
  }
  public int getNumPhotosTaken() {
    return photosTaken;
  }
}
