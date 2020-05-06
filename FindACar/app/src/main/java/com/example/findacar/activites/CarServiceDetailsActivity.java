package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.fragments.VehicleListFragment;

public class CarServiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_service_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(getIntent().getStringExtra("service"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        Fragment fragment = new VehicleListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                .replace(R.id.listOfVehicles, fragment);


        ft.commit();

    }
}
