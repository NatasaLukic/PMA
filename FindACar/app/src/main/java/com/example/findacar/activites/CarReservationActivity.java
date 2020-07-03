package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.adapters.AdditionalServicesAdapter;
import com.example.findacar.adapters.AdditionalServicesReservationAdapter;
import com.example.findacar.model.AdditionalService;
import com.example.findacar.model.CarService;
import com.example.findacar.modelDTO.CreateReservationDTO;
import com.example.findacar.service.ServiceUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarReservationActivity extends AppCompatActivity {
    private CreateReservationDTO reservation;
    List<AdditionalService> additionalServicesRes = new ArrayList<AdditionalService>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_reservation);
        reservation = (CreateReservationDTO) getIntent().getSerializableExtra("reservation");
        populateVehicleView();
    }

    public void populateVehicleView(){

        TextView name = (TextView) findViewById(R.id.name);


        TextView nDoors = (TextView) findViewById(R.id.numOfDoors);
        TextView nSeats = (TextView) findViewById(R.id.numOfSeats);

        TextView numOfBags = (TextView) findViewById(R.id.numOfBags);
        numOfBags.setText(Integer.toString(reservation.getVehicle().getCases()));

        TextView fuel = (TextView) findViewById(R.id.fuel);
        fuel.setText(reservation.getVehicle().getFuel());

        TextView pickupDate = (TextView) findViewById(R.id.yearOfProd);
        pickupDate.setText(reservation.getPickUpDate());

        TextView serviceInfo = findViewById(R.id.hint3);
        CarService carService = (CarService) getIntent().getSerializableExtra("carService");

        HashMap<String, Boolean> map = (HashMap<String, Boolean>) getIntent().getSerializableExtra("addServ");

        List<AdditionalService> additionalServices = carService.getAdditionalServices();


       // for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());

        //}

        double total = reservation.getPrice();
        for(int i=0; i<additionalServices.size(); i++){

             if (map.containsKey(additionalServices.get(i).getName())){
                 Log.e("POSTOJI", "DA");
                 additionalServicesRes.add(additionalServices.get(i));
                 total += additionalServices.get(i).getPrice();
             } else {
                 Log.e("POSTOJI", "NE");
             }

        }

        Log.i("ADD", String.valueOf(additionalServices.size()));

        LinearLayout layout = (LinearLayout) findViewById(R.id.listAdd2);
        RelativeLayout add= (RelativeLayout) findViewById(R.id.additional2);
        RelativeLayout infoYears = (RelativeLayout) findViewById(R.id.infoYears2);

        if(additionalServicesRes.size() == 0){
            add.setVisibility(View.GONE);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) infoYears.getLayoutParams();
            infoYears.setGravity(Gravity.CENTER_HORIZONTAL);
            infoYears.getLayoutParams().height = 200;
            View v = (View) findViewById(R.id.lin22);
            RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) v.getLayoutParams();
            lp2.addRule(RelativeLayout.BELOW, R.id.infoYears2);
            v.setLayoutParams(lp2);
        }

        final ListAdapter adapter = new AdditionalServicesReservationAdapter(CarReservationActivity.this, additionalServicesRes);

        int adapterCount = adapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }


        serviceInfo.setText(carService.getAddress().getStreet() + ", "  + carService.getAddress().getCity() + " "+ carService.getAddress().getPostalCode() + ", " + carService.getAddress().getCountry()
        + "\n" + carService.getEmail() +", " + carService.getPhone());

        TextView returnDate = (TextView) findViewById(R.id.reg);
        returnDate.setText(reservation.getReturnDate());

        nDoors.setText(Integer.toString(reservation.getVehicle().getDoors()));
        nSeats.setText(Integer.toString(reservation.getVehicle().getSeats()));


        TextView totalCost = findViewById(R.id.totalCost);
        totalCost.setText(String.valueOf(total) + " RSD");

        ImageView airC = (ImageView) findViewById(R.id.airC);
        TextView airC_text = (TextView) findViewById(R.id.airC_text);

        TextView auto_text = (TextView) findViewById(R.id.auto_text);

        Button btnBook = findViewById(R.id.book);
        btnBook.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                reservation.setIncludedAdditionalServices(additionalServicesRes);
                Call<ResponseBody> call = ServiceUtils.findACarService.createReservation(reservation);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CarReservationActivity.this, "\n" +
                                    "You have successfully booked the car!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CarReservationActivity.this, DashboardActivity.class);
                            intent.putExtra("user", reservation.getUserEmail());
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


        if(reservation.getVehicle().isAirCond() == false) {
            airC.setVisibility(View.INVISIBLE);
            airC_text.setVisibility(View.INVISIBLE);
        }

        if(reservation.getVehicle().isAutom() == true){
            auto_text.setText("Automatic");
        } else {
            auto_text.setText("Manual");
        }

        name.setText(reservation.getVehicle().getName());
        ImageView image = (ImageView)findViewById(R.id.slider);
        String m = reservation.getVehicle().getImageFile();

        Picasso.get().load(ServiceUtils.SERVICE_API_PATH + "search/getImage/" +m)
                .resize(300,300).into(image);
    }
}
