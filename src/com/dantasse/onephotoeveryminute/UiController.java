package com.dantasse.onephotoeveryminute;

import android.os.Handler;

public class UiController {

  // new Handler() grabs the current thread.
  private Handler handler = new Handler();
  
  private Runnable takePhotoTask = new Runnable() {
    public void run() {
      model.takePhoto();
      handler.postDelayed(this, 1000L);
    }
  };
  
  private UiModel model;
  
  public UiController(UiModel model) {
    this.model = model;
  }
  
  public void startTakingPhotos() {
    // TODO check model's current state first, to see if it's already taking photos
    model.setCurrentState(UiModel.State.TAKING_PHOTOS);
    handler.removeCallbacks(takePhotoTask);
    handler.post(takePhotoTask);
  }
}
