package com.example.lab.mylifelogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FallSencorActivity extends AppCompatActivity implements SensorEventListener{

    Sensor AccSensor;
    Sensor GyroSensor;
    SensorManager sm;

    TextView Acc_x;
    TextView Acc_y;
    TextView Acc_z;

    TextView Gyr_x;
    TextView Gyr_y;
    TextView Gyr_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_sencor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        AccSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        GyroSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        Acc_x = (TextView) findViewById(R.id.A_X);
        Acc_y = (TextView) findViewById(R.id.A_Y);
        Acc_z = (TextView) findViewById(R.id.A_Z);

        Gyr_x = (TextView) findViewById(R.id.G_X);
        Gyr_y = (TextView) findViewById(R.id.G_Y);
        Gyr_z = (TextView) findViewById(R.id.G_Z);

    }
    @Override
    public void onResume() {
        super.onResume();

        sm.registerListener(this, AccSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, GyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        sm.unregisterListener(this);
        sm.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                Acc_x.setText(String.valueOf("가속도 센서 X : " + event.values[0]));
                Acc_y.setText(String.valueOf("가속도 센서 Y : " + event.values[1]));
                Acc_z.setText(String.valueOf("가속도 센서 Z : " + event.values[2]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                Gyr_x.setText(String.valueOf("자이로 센서 X : " + event.values[0]));
                Gyr_y.setText(String.valueOf("자이로 센서 Y : " + event.values[1]));
                Gyr_z.setText(String.valueOf("자이로 센서 Z : " + event.values[2]));
                break;
        }
    }
}
