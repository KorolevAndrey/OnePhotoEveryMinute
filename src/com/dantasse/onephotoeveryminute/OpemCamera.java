package com.dantasse.onephotoeveryminute;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;

public class OpemCamera {

  private Camera camera;
  private FileSaver fileSaver;
  
  private int counter = 0;
  // Called when the picture's jpeg data is available.
  private PictureCallback jpegCallback = new PictureCallback() {
    public void onPictureTaken(byte[] data, Camera callbackCamera) {
      fileSaver.save(data, "picture" + counter + ".jpg");
      counter++;
    }
  };

  /**
   * There should only be one OpemCamera, because it's just a wrapper around
   * the Android Camera.
   */
  private static OpemCamera instance = null;
  public static OpemCamera getInstance() {
    if (instance == null) {
      instance = OpemInjector.injectOpemCamera();
      // You must call startPreview() even if you don't want a preview so the
      // camera can determine focus and exposure.
      // http://code.google.com/p/android/issues/detail?id=1702
      instance.camera.startPreview();
    }
    return instance;
  }

  public OpemCamera(Camera camera, FileSaver fileSaver) {
    this.camera = camera;
    this.fileSaver = fileSaver;
  }
  
  public void tearDown() {
    // TODO(dantasse) if you start and stop the app a couple times, this throws
    // an exception.
    camera.stopPreview();
    camera.release();
  }
  
  public void takePhoto() {
    // first two params are shutterCallback for when the camera's shutter opens
    // and takes a picture, and rawCallback for when the raw photo data is
    // available.
    // TODO(dantasse) add flash/focus/something?
    camera.takePicture(null, null, jpegCallback);
  }
}
