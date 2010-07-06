package com.dantasse.onephotoeveryminute;

import com.dantasse.onephotoeveryminute.UiModel.State;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Main activity for OnePhotoEveryMinute.  Also serves as the View class in this
 * Model-View-Controller app.
 * 
 * @author dantasse
 */
public class MainActivity extends Activity implements OnClickListener {

  private UiModel model;
  private UiController controller;
  private Button startButton;
  private Button stopButton;
  private TextView text01;
  private ImageView image01;
  private NumberPicker minutePicker;
  private NumberPicker secondPicker;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    // This is a little kludgey; ideally, the injector shouldn't really have
    // state, but it's the one "init" method you have to do, so it's not so bad.
    OpemInjector.setView(this);

    model = OpemInjector.injectUiModel();
    controller = OpemInjector.injectUiController();

    startButton = (Button) findViewById(R.id.StartButton);
    startButton.setOnClickListener(this);
    stopButton = (Button) findViewById(R.id.StopButton);
    stopButton.setOnClickListener(this);
    text01 = (TextView) findViewById(R.id.TextView01);
    image01 = (ImageView) findViewById(R.id.ImageView01);
    minutePicker = (NumberPicker) findViewById(R.id.MinutePicker);
    secondPicker = (NumberPicker) findViewById(R.id.SecondPicker);
  }

  @Override
  public void onPause() {
    super.onPause();
    controller.tearDown();
  }

  public void onClick(View v) {
    if (v.equals(startButton)) {
      controller.startTakingPhotos();
    } else if (v.equals(stopButton)) {
      controller.stopTakingPhotos();
    }
  }

  public int getDurationSeconds() {
    return minutePicker.getCurrent() * 60 + secondPicker.getCurrent();
  }
  
  public void update() {
    text01.setText("State: " + model.getCurrentState().toString() + 
        "\nPhotos taken: " + model.getPhotoCount());
    image01.setImageBitmap(model.getCurrentImage());
    boolean isTakingPhotos = (model.getCurrentState().equals(
        State.TAKING_PHOTOS));
    minutePicker.setEnabled(!isTakingPhotos);
    secondPicker.setEnabled(!isTakingPhotos);
    startButton.setEnabled(!isTakingPhotos);
  }
}