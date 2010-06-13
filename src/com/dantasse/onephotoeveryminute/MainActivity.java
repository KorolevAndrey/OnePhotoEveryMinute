package com.dantasse.onephotoeveryminute;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
  
  private UiModel model;
  private UiController controller;
  private Button startButton;
  private TextView text01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    model = new UiModel(this);
    controller = new UiController(model);
    
    startButton = (Button) findViewById(R.id.StartButton);
    startButton.setOnClickListener(this);
    text01 = (TextView) findViewById(R.id.TextView01);
  }

  public void onClick(View v) {
    controller.startTakingPhotos();
  }
  
  public void update() {
    text01.setText("State: " + model.getCurrentState().toString() + 
        "\nPhotos taken: " + model.getNumPhotosTaken());
  }
}