package com.example.lab.mylifelogger;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.*;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static Double lat;
    public static Double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSInfo gps_info = new GPSInfo();

        long min_time = 10000;
        float min_distance = 0;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, min_time, min_distance, gps_info);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, min_time, min_distance, gps_info);

    }

    public class GPSInfo implements LocationListener {


        boolean gps_enabled = false;
        boolean network_enabled = false;



        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lon = location.getLongitude();

            showCurrentLocation(lat,lon);


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

    private void showCurrentLocation(final Double latitude, final Double longitude) {
        LatLng Cur = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Cur));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        MarkerOptions opt = new MarkerOptions();
        opt.position(Cur);
        opt.title("현재 위치");
        opt.snippet("Snippet");
        opt.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        mMap.addMarker(opt).showInfoWindow();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("위도", latitude);
                intent.putExtra("경도", longitude);

                startActivity(intent);
                return false;
            }
        });


    }
}

