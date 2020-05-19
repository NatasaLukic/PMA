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

    private boolean clicked = false;

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

        TextView date = (TextView) findViewById(R.id.reg);
        TextView nDoors = (TextView) findViewById(R.id.numOfDoors);
        TextView nSeats = (TextView) findViewById(R.id.numOfSeats);

        Vehicle vehicle = Vehicles.getVehicles().get(position);

        TextView numOfBags = (TextView) findViewById(R.id.numOfBags);
        numOfBags.setText(Integer.toString(vehicle.getNumOfCases()));

        TextView fuel = (TextView) findViewById(R.id.fuel);
        fuel.setText(vehicle.getFuel());

        TextView yearOfProd = (TextView) findViewById(R.id.yearOfProd);
        yearOfProd.setText(Integer.toString(vehicle.getYearOfProd()));

        date.setText(vehicle.getRegistratedUntil());
        nDoors.setText(Integer.toString(vehicle.getNumOfDoors()));
        nSeats.setText(Integer.toString(vehicle.getNumOfSeats()));

        ImageView airC = (ImageView) findViewById(R.id.airC);
        TextView airC_text = (TextView) findViewById(R.id.airC_text);

        TextView auto_text = (TextView) findViewById(R.id.auto_text);

        final ImageView notClickedImage = (ImageView) findViewById(R.id.notClickedImage);
        final ImageView clickedImage = (ImageView) findViewById(R.id.clickedImage);

        notClickedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notClickedImage.setVisibility(View.GONE);
                clickedImage.setVisibility(View.VISIBLE);

            }
        });

        clickedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notClickedImage.setVisibility(View.VISIBLE);
                clickedImage.setVisibility(View.GONE);

            }
        });

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
