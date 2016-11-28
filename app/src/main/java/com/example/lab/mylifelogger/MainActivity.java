package com.example.lab.mylifelogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {



    ToggleButton bt_gps;
    ToggleButton bt_fall;
    Button bt_log;
    Button bt_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_gps = (ToggleButton) findViewById(R.id.GPSbt);
        bt_fall = (ToggleButton) findViewById(R.id.Fallbt);

        bt_log = (Button) findViewById(R.id.Logbt);
        bt_check = (Button) findViewById(R.id.Checkbt);

    }
}
