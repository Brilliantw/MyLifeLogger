package com.example.lab.mylifelogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.Context;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.lab.mylifelogger.R.styleable.View;

public class MainActivity extends AppCompatActivity {



    ToggleButton bt_gps;
    ToggleButton bt_fall;
    Button bt_log;
    Button bt_check;
    Intent intent_map;
    Intent intent_fall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_gps = (ToggleButton) findViewById(R.id.GPSbt);
        bt_fall = (ToggleButton) findViewById(R.id.Fallbt);

        bt_log = (Button) findViewById(R.id.Logbt);
        bt_check = (Button) findViewById(R.id.Checkbt);

        intent_map = new Intent(this, MapsActivity.class);
        intent_fall = new Intent(this, FallSencorActivity.class);

        bt_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( bt_gps.isChecked() ) {
                    startActivity(intent_map);
                }
            }
        });

        bt_fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( bt_fall.isChecked() ) {
                    startActivity(intent_fall);
                }
            }
        });

    }


}
