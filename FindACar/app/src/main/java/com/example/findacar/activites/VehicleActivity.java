package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.adapters.VehiclePhotosAdapter;
import com.example.findacar.mockupData.Vehicles;
import com.example.findacar.model.Vehicle;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Car details");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        int position = getIntent().getIntExtra("position", 0);

        ViewPager vp = findViewById(R.id.slider);
        VehiclePhotosAdapter vpa = new VehiclePhotosAdapter(this, position);
        vp.setAdapter(vpa);

        TextView name = (TextView) findViewById(R.id.name);

        TextView date = (TextView) findViewById(R.id.date);
        TextView nDoors = (TextView) findViewById(R.id.numOfDoors);
        TextView nSeats = (TextView) findViewById(R.id.numOfSeats);

        Vehicle vehicle = Vehicles.getVehicles().get(position);

        date.setText(vehicle.getRegistratedUntil());
        nDoors.setText(Integer.toString(vehicle.getNumOfDoors()));
        nSeats.setText(Integer.toString(vehicle.getNumOfSeats()));

        ImageView airC = (ImageView) findViewById(R.id.airC);
        TextView airC_text = (TextView) findViewById(R.id.airC_text);

        TextView auto_text = (TextView) findViewById(R.id.auto_text);

        if(vehicle.isAirCond() == false) {
            airC.setVisibility(View.INVISIBLE);
            airC_text.setVisibility(View.INVISIBLE);
        }

        if(vehicle.isAutomTrans() == true){
            auto_text.setText("Automatic");
        } else {
            auto_text.setText("Manual");
        }

        name.setText(getIntent().getStringExtra("name"));
    }

}
