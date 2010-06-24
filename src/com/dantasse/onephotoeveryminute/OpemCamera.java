package com.dantasse.onephotoeveryminute;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.util.Log;

public class OpemCamera {

  private UiController controller;
  private Camera camera;
  
  private ShutterCallback shutterCallback = new ShutterCallback() {
    public void onShutter() {
      Log.d("OPEM", "shutter callback!");
    }
  };
  private PictureCallback rawCallback = new PictureCallback() {
    public void onPictureTaken(byte[] data, Camera callbackCamera) {
      Log.d("OPEM", "raw callback!");
    }
  };
  
  // Called when the picture's jpeg data is available.
  private PictureCallback jpegCallback = new PictureCallback() {
    public void onPictureTaken(byte[] data, Camera callbackCamera) {
      Log.d("OPEM", "picture callback!");
      Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
      controller.displayImage(bitmap);
    }
  };

  public OpemCamera(UiController controller) {
    this.controller = controller;
    camera = Camera.open();
    // You must call startPreview() even if you don't want a preview so the
    // camera can determine focus and exposure.
    // http://code.google.com/p/android/issues/detail?id=1702
    camera.startPreview();
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
    Log.d("OPEM", "taking photo");
    camera.takePicture(shutterCallback, rawCallback, jpegCallback);
  }
}
