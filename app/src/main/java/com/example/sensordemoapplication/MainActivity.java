package com.example.sensordemoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {

    private static final String TAG = "Main Activity";
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView x_val, y_val, z_val;
    private Button button;
    private boolean on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registered accelerometer");

        x_val = (TextView) findViewById(R.id.textView4);
        y_val = (TextView) findViewById(R.id.textView5);
        z_val = (TextView) findViewById(R.id.textView6);
        button = (Button) findViewById(R.id.button2);
        button.setText("Start");
        on = false;
        resetText();
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (on) {
                    button.setText("Start");
                    on = false;
                    resetText();
                } else {
                    button.setText("Stop");
                    on = true;
                }
            }
        });
    }

    private void resetText(){
        x_val.setText("Offline");
        y_val.setText("Offline");
        z_val.setText("Offline");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.d(TAG, "onSensorChange: X " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2]);

        if(on){
            x_val.setText(Float.toString(event.values[0]));
            y_val.setText(Float.toString(event.values[1]));
            z_val.setText(Float.toString(event.values[2]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
