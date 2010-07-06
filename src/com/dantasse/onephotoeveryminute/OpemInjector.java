package com.dantasse.onephotoeveryminute;

import android.hardware.Camera;

/**
 * Static methods for dependency injection goodness.
 */
public class OpemInjector {
  
  private static MainActivity view = null;
  public static MainActivity getView() {
    return view;
  }
  public static void setView(MainActivity view) {
    OpemInjector.view = view; 
  }

  public static UiController injectUiController() {
    return new UiController(injectUiModel(), view, injectOpemCamera());
  }
  
  public static UiModel injectUiModel() {
    return UiModel.getInstance();
  }
  
  public static OpemCamera injectOpemCamera() {
    return OpemCamera.getInstance();
  }
  
  public static Camera injectCamera() {
    return Camera.open();
  }
  
  public static FileSaver injectFileSaver() {
    return FileSaver.getInstance();
  }
}
