package com.example.findacar.activites;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.findacar.R;
import com.example.findacar.model.CarService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<CarService> services = new ArrayList<CarService>();
    private ArrayList<LatLng> locationList = new ArrayList<LatLng>();
    private double currentLocationX;
    private double currentLocationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        currentLocationX = getIntent().getDoubleExtra("currentLocationX",0);
        currentLocationY = getIntent().getDoubleExtra("currentLocationY",0);

        Gson gson = new Gson();
        String gsonS = getIntent().getStringExtra("services");

        Type type = new TypeToken<List<CarService>>() {
        }.getType();

        services = gson.fromJson(gsonS, type);

        for (int i = 0; i < services.size(); i++) {
            LatLng location = new LatLng(services.get(i).getAddress().getX(), services.get(i).getAddress().getY());
            locationList.add(location);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //create marker for current location in color azure
        LatLng current = new LatLng(currentLocationX, currentLocationY);
        mMap.addMarker(new MarkerOptions().position(current).title("CurrentLocation").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 13));

        for (int i = 0; i< locationList.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(locationList.get(i)).title("Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationList.get(i), 13));
        }
    }
}
