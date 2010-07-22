package com.dantasse.onephotoeveryminute;

import java.text.NumberFormat;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;

public class OpemCamera {

  private Camera camera;
  private FileSaver fileSaver;
  private NumberFormat numberFormat = NumberFormat.getIntegerInstance();
  
  private int counter = 0;
  // Called when the picture's jpeg data is available.
  private PictureCallback jpegCallback = new PictureCallback() {
    public void onPictureTaken(byte[] data, Camera callbackCamera) {
      numberFormat.setMinimumIntegerDigits(3);
      fileSaver.save(data, numberFormat.format(counter) + ".jpg");
      counter++;
    }
  };

  /**
   * There should only be one OpemCamera.
   */
  private static OpemCamera instance = null;
  public static OpemCamera getInstance() {
    if (instance == null) {
      instance = new OpemCamera(new FileSaver());
    }
    return instance;
  }

  // visible for testing
  OpemCamera(FileSaver fileSaver) {
    this.fileSaver = fileSaver;
  }
  
  /** Called on app resume, needed because the camera is released on pause. */
  public void setUp() {
    if (camera == null) {
      camera = Camera.open();
    }
    // one camera = one fileSaver.
    fileSaver = new FileSaver();
    // You must call startPreview() even if you don't want a preview so the
    // camera can determine focus and exposure.
    // http://code.google.com/p/android/issues/detail?id=1702
    camera.startPreview();
  }
  
  /** Called on pause. */
  public void tearDown() {
    // TODO(dantasse) if you start and stop the app a couple times, this throws
    // an exception.
    camera.stopPreview();
    camera.release();
    // null it out because apparently there's no way to tell that it's been
    // released (and is therefore unusable)
    camera = null;
    // one camera = one fileSaver.
    fileSaver = null;
  }
  
  public void takePhoto() {
    // TODO(dantasse) add flash/focus/something?
    camera.takePicture(null, null, jpegCallback);
  }
}
