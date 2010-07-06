package com.dantasse.onephotoeveryminute;

import android.hardware.Camera;

/**
 * Static methods for dependency injection goodness.
 */
public class OpemInjector {

  public static OpemCamera injectOpemCamera() {
    return new OpemCamera(injectCamera(), injectFileSaver());
  }
  
  public static Camera injectCamera() {
    Camera camera = Camera.open();
    // You must call startPreview() even if you don't want a preview so the
    // camera can determine focus and exposure.
    // http://code.google.com/p/android/issues/detail?id=1702
    camera.startPreview();
    return camera;
  }
  
  public static FileSaver injectFileSaver() {
    return new FileSaver();
  }
}
