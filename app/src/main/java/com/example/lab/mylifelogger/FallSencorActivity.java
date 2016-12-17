package com.example.lab.mylifelogger;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FallSencorActivity extends AppCompatActivity implements SensorEventListener{

    Sensor AccSensor;
    SensorManager sm;

    TextView Acc_x;
    TextView Acc_y;
    TextView Acc_z;

    TextView fall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_sencor);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        AccSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        Acc_x = (TextView) findViewById(R.id.A_X);
        Acc_y = (TextView) findViewById(R.id.A_Y);
        Acc_z = (TextView) findViewById(R.id.A_Z);
        fall = (TextView) findViewById(R.id.IsF);


    }
    @Override
    public void onResume() {
        super.onResume();

        sm.registerListener(this, AccSensor, SensorManager.SENSOR_DELAY_NORMAL);

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
                if (Math.sqrt((event.values[0]-9.8)*(event.values[0]-9.8) + (event.values[1]-9.8)*(event.values[1]-9.8) + (event.values[2]-9.8)*(event.values[2]-9.8)) > 10) {
                    fall.setText(String.valueOf("떨어짐"));

                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    // 시간 포맷 지정
                    SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH:mm:ss");


                    // 지정된 포맷으로 String 타입 리턴
                    String strCurDate = CurDateFormat.format(date);
                    String strCurTime = CurTimeFormat.format(date);

                    String datetime = strCurDate + " " + strCurTime;
                    GPSInfo gpsinfo = new GPSInfo();
                    String lat = Double.toString(gpsinfo.lat);
                    String lgt = Double.toString(gpsinfo.lon);

                    new InsertData().execute(datetime, lat, lgt);
                }
                else
                    fall.setText(String.valueOf("안떨어짐"));


                break;

        }
    }

    public class GPSInfo implements LocationListener {


        boolean gps_enabled = false;
        boolean network_enabled = false;
        public Double lat;
        public Double lon;



        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lon = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
        }

    }
}
