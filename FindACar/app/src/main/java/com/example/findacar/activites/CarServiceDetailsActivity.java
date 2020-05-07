package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.fragments.AboutServiceFragment;
import com.example.findacar.fragments.FilterFragment;
import com.example.findacar.fragments.VehicleListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CarServiceDetailsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_service_details);

        bottomNavigationView = findViewById(R.id.bottom_toolbar);

        //bottomNavigationView.setItemTextColor(Color.WHITE);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(getIntent().getStringExtra("service"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(currentFragment instanceof FilterFragment || currentFragment instanceof AboutServiceFragment){
                        Fragment f = new VehicleListFragment();
                        getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                                .replace(R.id.listOfVehicles, f).commit();
                        currentFragment = f;
                    } else if (currentFragment instanceof VehicleListFragment) {

                        finish();
                    }
            }
        });

        Fragment fragment = new VehicleListFragment();
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
                getSupportFragmentManager().beginTransaction().replace(R.id.listOfVehicles,
                        f).commit();
                break;

            case R.id.about:
                Fragment f2 = new AboutServiceFragment();
                currentFragment = f2;
                getSupportFragmentManager().beginTransaction().replace(R.id.listOfVehicles,
                        f2).commit();
                break;
        }
        return true;
    }
}
