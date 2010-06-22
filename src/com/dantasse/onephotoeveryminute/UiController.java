package com.dantasse.onephotoeveryminute;

import android.graphics.Bitmap;
import android.os.Handler;

public class UiController {

  // new Handler() grabs the current thread.
  private Handler handler = new Handler();
  private OpemCamera camera = new OpemCamera(this);
  
  private Runnable takePhotoTask = new Runnable() {
    public void run() {
      camera.takePhoto();
      model.incrementPhotoCount();
      handler.postDelayed(this, 10000L);
    }
  };
  
  private UiModel model;
  
  public UiController(UiModel model) {
    this.model = model;
  }
  public void tearDown() {
    handler.removeCallbacks(takePhotoTask);
    camera.tearDown();
  }
  
  public void startTakingPhotos() {
    // TODO check model's current state first, to see if it's already taking photos
    model.setCurrentState(UiModel.State.TAKING_PHOTOS);
    handler.removeCallbacks(takePhotoTask);
    handler.post(takePhotoTask);
  }
  public void displayImage(Bitmap image) {
    model.setCurrentImage(image);
  }
}
