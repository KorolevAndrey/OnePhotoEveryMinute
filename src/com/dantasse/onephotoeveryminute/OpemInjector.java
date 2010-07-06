package com.dantasse.onephotoeveryminute;

import android.hardware.Camera;

/**
 * Static methods for dependency injection goodness.
 */
public class OpemInjector {

  public static OpemCamera injectOpemCamera() {
    return new OpemCamera(OpemInjector.injectCamera(),
        OpemInjector.injectFileSaver());
  }
  
  public static Camera injectCamera() {
    return Camera.open();
  }
  
  public static FileSaver injectFileSaver() {
    return FileSaver.getInstance();
  }
}
