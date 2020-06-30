package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.findacar.R;
import com.example.findacar.model.CarService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(this);

        mapFragment.getMapAsync(this);

        Gson gson = new Gson();
        String gsonS = getIntent().getStringExtra("services");
        Type type = new TypeToken<List<CarService>>(){}.getType();

        services = gson.fromJson(gsonS, type);
        for (int i = 0; i< services.size(); i++) {
            LatLng location = new LatLng(services.get(i).getAddress().getX(), services.get(i).getAddress().getY());
            locationList.add(location);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i< locationList.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(locationList.get(i)).title("Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationList.get(i), 13));
        }


    }
}
