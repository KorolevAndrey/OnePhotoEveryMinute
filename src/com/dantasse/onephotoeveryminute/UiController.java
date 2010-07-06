package com.dantasse.onephotoeveryminute;

import android.graphics.Bitmap;
import android.os.Handler;

public class UiController {

  // new Handler() grabs the current thread.
  private Handler handler = new Handler();
  private OpemCamera camera = new OpemCamera(new FileSaver(),
      OpemInjector.injectCamera());
  private UiModel model;
  private MainActivity view;  
  
  private Runnable takePhotoTask = new Runnable() {
    public void run() {
      camera.takePhoto();
      model.incrementPhotoCount();
      handler.postDelayed(this, model.getDurationSeconds() * 1000);
    }
  };
  
  public UiController(UiModel model, MainActivity view) {
    this.model = model;
    this.view = view;
  }
  
  public void tearDown() {
    handler.removeCallbacks(takePhotoTask);
    camera.tearDown();
  }

  public void startTakingPhotos() {
    int durationSeconds = view.getDurationSeconds();
    if (durationSeconds <= 0) {
      return; //TODO(dantasse) display some error
    }
    model.setDurationSeconds(durationSeconds);
    model.setCurrentState(UiModel.State.TAKING_PHOTOS);
    handler.removeCallbacks(takePhotoTask);
    handler.post(takePhotoTask);
  }

  public void stopTakingPhotos() {
    model.setCurrentState(UiModel.State.NOT_TAKING_PHOTOS);
    handler.removeCallbacks(takePhotoTask);
  }

  public void displayImage(Bitmap image) {
    model.setCurrentImage(image);
  }
}
