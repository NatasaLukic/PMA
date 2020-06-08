package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.model.CarService;
import com.example.findacar.model.CreateReservationDTO;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarReservationActivity extends AppCompatActivity {
    private Vehicle vehicle;
    private CreateReservationDTO reservation;
    public static final String SERVICE_API_PATH = "http://192.168.0.35:8057/";
    //public static final String SERVICE_API_PATH = "http://192.168.0.15:8057/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_reservation);
        reservation = (CreateReservationDTO) getIntent().getSerializableExtra("reservation");
        vehicle = reservation.getVehicle();
        populateVehicleView();
    }

    public void populateVehicleView(){

        TextView name = (TextView) findViewById(R.id.name);


        TextView nDoors = (TextView) findViewById(R.id.numOfDoors);
        TextView nSeats = (TextView) findViewById(R.id.numOfSeats);

        TextView numOfBags = (TextView) findViewById(R.id.numOfBags);
        numOfBags.setText(Integer.toString(vehicle.getCases()));

        TextView fuel = (TextView) findViewById(R.id.fuel);
        fuel.setText(vehicle.getFuel());

        TextView pickupDate = (TextView) findViewById(R.id.yearOfProd);
        pickupDate.setText(reservation.getPickUpDate());

        TextView serviceInfo = findViewById(R.id.hint3);
        CarService carService = (CarService) getIntent().getSerializableExtra("carService");
        serviceInfo.setText(carService.getAddress().getStreet() + ", "  + carService.getAddress().getCity() + " "+ carService.getAddress().getPostalCode() + ", " + carService.getAddress().getCountry());

        TextView returnDate = (TextView) findViewById(R.id.reg);
        returnDate.setText(reservation.getReturnDate());

        nDoors.setText(Integer.toString(vehicle.getDoors()));
        nSeats.setText(Integer.toString(vehicle.getSeats()));

        TextView totalCost = findViewById(R.id.totalCost);
        totalCost.setText(String.valueOf(reservation.getPrice()) + " RSD");

        ImageView airC = (ImageView) findViewById(R.id.airC);
        TextView airC_text = (TextView) findViewById(R.id.airC_text);

        TextView auto_text = (TextView) findViewById(R.id.auto_text);

        Button btnBook = findViewById(R.id.book);
        btnBook.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = ServiceUtils.findACarService.createReservation(reservation);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CarReservationActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CarReservationActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(CarReservationActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(CarReservationActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button btnCancel = findViewById(R.id.cancelRes);
        btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
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

        name.setText(vehicle.getName());
        ImageView image = (ImageView)findViewById(R.id.slider);
        String m = vehicle.getImageFile();

        Picasso.get().load(SERVICE_API_PATH + "search/getImage/" +m)
                .resize(300,300).into(image);
    }
}
