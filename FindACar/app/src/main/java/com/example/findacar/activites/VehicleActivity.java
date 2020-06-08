package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.adapters.ReviewsAdapter;
import com.example.findacar.adapters.VehiclePhotosAdapter;
import com.example.findacar.fragments.ReviewFragment;
import com.example.findacar.model.CarService;
import com.example.findacar.model.CreateReservationDTO;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleActivity extends AppCompatActivity {

    private boolean clicked = false;
    private Vehicle vehicle;
    private CreateReservationDTO reservation = new CreateReservationDTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vehicle = (Vehicle) getIntent().getSerializableExtra("vehicle");
        reservation.setVehicle(vehicle);
        reservation.setPickUpDate(getIntent().getStringExtra("pickupDateTime"));
        reservation.setReturnDate(getIntent().getStringExtra("returnDateTime"));
        reservation.setPrice(vehicle.getPriceForDays());

        String email = getIntent().getStringExtra("email");
        reservation.setUserEmail(email);

        populateVehicleView();

        Call<List<Review>> call1 = ServiceUtils.findACarService.getVehicleReviews(vehicle.getId());
        call1.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful()){

                    response.body();
                    List<Review> reviews = response.body();
                    vehicle.setReviews(reviews);
                    LinearLayout layout = findViewById(R.id.listRev);

                    ListAdapter adapter = new ReviewsAdapter(VehicleActivity.this, vehicle.getReviews());

                    int adapterCount = adapter.getCount();

                    for (int i = 0; i < adapterCount; i++) {
                        View item = adapter.getView(i, null, null);
                        layout.addView(item);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Car details");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


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


        //   getChildFragmentManager().beginTransaction().add(R.id.listReviews, new ReviewFragment()).commit();


    }

    public void populateReviews(){
    //    getSupportFragmentManager().beginTransaction().add(R.id.listRev, new ReviewFragment(vehicle.getReviews())).commit();


    }

    public void populateVehicleView(){

        TextView name = (TextView) findViewById(R.id.name);

        TextView date = (TextView) findViewById(R.id.reg);
        TextView nDoors = (TextView) findViewById(R.id.numOfDoors);
        TextView nSeats = (TextView) findViewById(R.id.numOfSeats);

        TextView numOfBags = (TextView) findViewById(R.id.numOfBags);
        numOfBags.setText(Integer.toString(vehicle.getCases()));

        TextView fuel = (TextView) findViewById(R.id.fuel);
        fuel.setText(vehicle.getFuel());

        TextView yearOfProd = (TextView) findViewById(R.id.yearOfProd);
        yearOfProd.setText(Integer.toString(vehicle.getProdYear()));

        date.setText(vehicle.getRegUntil());
        nDoors.setText(Integer.toString(vehicle.getDoors()));
        nSeats.setText(Integer.toString(vehicle.getSeats()));

        ImageView airC = (ImageView) findViewById(R.id.airC);
        TextView airC_text = (TextView) findViewById(R.id.airC_text);

        TextView auto_text = (TextView) findViewById(R.id.auto_text);

        ViewPager vp = findViewById(R.id.slider);
        VehiclePhotosAdapter vpa = new VehiclePhotosAdapter(this, vehicle.getVehiclePhotos());
        vp.setAdapter(vpa);

        Button btnBook = findViewById(R.id.book);
        btnBook.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CarService carService = (CarService) getIntent().getSerializableExtra("carService");
                Intent intent = new Intent(VehicleActivity.this, CarReservationActivity.class);
                //intent.putExtra("vehicle", (Serializable) vehicle);
                intent.putExtra("reservation", (Serializable) reservation);
                VehicleActivity.this.startActivity(intent);
            }
        });

        if(vehicle.isAirCond() == false) {
            airC.setVisibility(View.INVISIBLE);
            airC_text.setVisibility(View.INVISIBLE);
        }

        if(vehicle.isAutom() == true){
            auto_text.setText("Automatic");
        } else {
            auto_text.setText("Manual");
        }

        name.setText(getIntent().getStringExtra("name"));
    }

}
