package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.fragments.AboutServiceFragment;
import com.example.findacar.fragments.FilterFragment;
import com.example.findacar.fragments.VehicleListFragment;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarServiceDetailsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView bottomNavigationViewFilter;
    private Fragment currentFragment;
    private CarService carService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_service_details);

        bottomNavigationView = findViewById(R.id.bottom_toolbar);


        //bottomNavigationView.setItemTextColor(Color.WHITE);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    //    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
    //    params.setScrollFlags(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // TODO Iscupati carService objekat iz Intent-a
        Gson gson = new Gson();
        String gsonS = getIntent().getStringExtra("carService");
        Type type = new TypeToken<CarService>(){}.getType();
        carService = gson.fromJson(gsonS, type);
        getSupportActionBar().setTitle(carService.getName());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(currentFragment instanceof FilterFragment || currentFragment instanceof AboutServiceFragment){
                        Fragment f = new VehicleListFragment();

                        bottomNavigationView.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                                .replace(R.id.listOfVehicles, f).commit();
                        currentFragment = f;
                    } else if (currentFragment instanceof VehicleListFragment) {

                        finish();
                    }
            }
        });

        List<Vehicle> vehicles = (List<Vehicle>) getIntent().getSerializableExtra("vehicles");

        Fragment fragment = new VehicleListFragment(vehicles);
        currentFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                .replace(R.id.listOfVehicles, fragment);

        ft.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.filter:
                Fragment f = new FilterFragment();
                currentFragment = f;
                bottomNavigationView.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.listOfVehicles,
                        f).commit();
                break;

            case R.id.about:
                Fragment f2 = new AboutServiceFragment(this.carService);
                bottomNavigationView.setVisibility(View.GONE);
                currentFragment = f2;
                getSupportFragmentManager().beginTransaction().replace(R.id.listOfVehicles,
                        f2).commit();
                break;
        }
        return true;
    }
}
