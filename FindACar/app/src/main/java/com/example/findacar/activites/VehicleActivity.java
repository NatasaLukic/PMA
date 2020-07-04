package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.adapters.AdditionalServicesAdapter;
import com.example.findacar.adapters.ReviewsAdapter;
import com.example.findacar.adapters.VehiclePhotosAdapter;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.model.AdditionalService;
import com.example.findacar.model.CarService;
import com.example.findacar.model.UserVehicleCrossRef;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.VehicleWithReviews;
import com.example.findacar.modelDTO.CreateReservationDTO;
import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ConnectionReceiver;
import com.example.findacar.service.NetworkUtils;
import com.example.findacar.service.ServiceUtils;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleActivity extends AppCompatActivity {

    private boolean clicked = false;
    private Vehicle vehicle;
    private CreateReservationDTO reservation = new CreateReservationDTO();
    private UserDatabase userDatabase;
    private ImageView notClickedImage;
    private ImageView clickedImage;
    private ConnectionReceiver connectionReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        notClickedImage = (ImageView) findViewById(R.id.notClickedImage);
        clickedImage = (ImageView) findViewById(R.id.clickedImage);

        userDatabase = UserDatabase.getInstance(this);
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
                if (response.isSuccessful()) {

                    response.body();
                    List<Review> reviews = response.body();

                    for (Review review : reviews) {
                        review.setNameUser(review.getNameUser());
                    }
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

        notClickedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddToFavoritesRequest();
            }
        });

        clickedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRemoveFromFavoritesRequest();
            }
        });


    }

    private void sendAddToFavoritesRequest() {
        Call<ResponseBody> call = ServiceUtils.findACarService
                .addFavorite(getIntent().getStringExtra("email"), vehicle.getId());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(VehicleActivity.this, "Added to list of favorites", Toast.LENGTH_SHORT);
                    String email = getIntent().getStringExtra("email");
                    long userId = userDatabase.userDao().loadSingleByEmail(email);
                    long vehicleId = userDatabase.userDao().insertVehicle(vehicle);

                    UserVehicleCrossRef userVehicleCrossRef = new UserVehicleCrossRef();
                    userVehicleCrossRef.userId = userId;
                    userVehicleCrossRef.vehicleId = vehicleId;
                    userDatabase.userDao().insert(userVehicleCrossRef);
                    notClickedImage.setVisibility(View.GONE);
                    clickedImage.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(VehicleActivity.this, "Could not add vehicle to list of favorites", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(VehicleActivity.this, "Could not add vehicle to list of favorites", Toast.LENGTH_SHORT);
            }
        });
    }

    private void sendRemoveFromFavoritesRequest() {
        Call<ResponseBody> call = ServiceUtils.findACarService
                .removeVehicleFromFavorites(getIntent().getStringExtra("email"), vehicle.getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    String email = getIntent().getStringExtra("email");
                    long userId = userDatabase.userDao().loadSingleByEmail(email);
                    Vehicle dbVehicle = userDatabase.userDao().getVehicleByServerId(vehicle.getId());
                    if (dbVehicle != null) {
                        long vehicleId = dbVehicle.getVehicleId();
                        userDatabase.userDao().deleteVehicle(vehicleId);

                        UserVehicleCrossRef temp = userDatabase.userDao().findOneByUserIdAndVehicleId(userId, vehicleId);
                        userDatabase.userDao().delete(temp);
                        userDatabase.userDao().deleteReviewsForVehicle(vehicleId);

                        notClickedImage.setVisibility(View.VISIBLE);
                        clickedImage.setVisibility(View.GONE);
                        Toast.makeText(VehicleActivity.this, "Removed from list of favorite vehicles.", Toast.LENGTH_SHORT);
                    }

                } else {
                    Toast.makeText(VehicleActivity.this, "Could not remove vehicle from list of favorites", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(VehicleActivity.this, "Could not remove vehicle from list of favorites", Toast.LENGTH_SHORT);
            }
        });

    }



    public void populateVehicleView() {

        final TextView name = (TextView) findViewById(R.id.name);

        TextView date = (TextView) findViewById(R.id.reg);
        TextView nDoors = (TextView) findViewById(R.id.numOfDoors);
        TextView nSeats = (TextView) findViewById(R.id.numOfSeats);

        TextView numOfBags = (TextView) findViewById(R.id.numOfBags);
        numOfBags.setText(Integer.toString(vehicle.getCases()));

        TextView fuel = (TextView) findViewById(R.id.fuel);
        fuel.setText(vehicle.getFuel());

        TextView yearOfProd = (TextView) findViewById(R.id.yearOfProd);
        yearOfProd.setText(Integer.toString(vehicle.getProdYear()));

        String[] strings = vehicle.getRegUntil().split("T");

        date.setText(strings[0]);
        nDoors.setText(Integer.toString(vehicle.getDoors()));
        nSeats.setText(Integer.toString(vehicle.getSeats()));

        final ImageView airC = (ImageView) findViewById(R.id.airC);
        final TextView airC_text = (TextView) findViewById(R.id.airC_text);

        final TextView auto_text = (TextView) findViewById(R.id.auto_text);

        List<AdditionalService> additionalServices = (List<AdditionalService>) getIntent().getSerializableExtra("addServices");

        LinearLayout layout = (LinearLayout) findViewById(R.id.listAdd);

        Log.i("ADD", String.valueOf(additionalServices.size()));

        RelativeLayout add= (RelativeLayout) findViewById(R.id.additional);
        RelativeLayout infoYears = (RelativeLayout) findViewById(R.id.infoYears);

        if(additionalServices.size() == 0){
            add.setVisibility(View.GONE);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) infoYears.getLayoutParams();
            infoYears.setGravity(Gravity.CENTER_HORIZONTAL);
            infoYears.getLayoutParams().height = 200;
            View v = (View) findViewById(R.id.lin2);
            RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) v.getLayoutParams();
            lp2.addRule(RelativeLayout.BELOW, R.id.infoYears);
            v.setLayoutParams(lp2);
        }

        final ListAdapter adapter = new AdditionalServicesAdapter(VehicleActivity.this, additionalServices);

        int adapterCount = adapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }

        setLikePictureForVehicle();

        ViewPager vp = findViewById(R.id.slider);
        VehiclePhotosAdapter vpa = new VehiclePhotosAdapter(this, vehicle.getVehiclePhotos());
        vp.setAdapter(vpa);

        Button btnBook = findViewById(R.id.book);
        btnBook.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                int status = NetworkUtils.getConnectivityStatus(VehicleActivity.this);

                if(status == NetworkUtils.TYPE_WIFI || status == NetworkUtils.TYPE_MOBILE) {

                    CarService carService = (CarService) getIntent().getSerializableExtra("carService");

                    Intent intent = new Intent(VehicleActivity.this, CarReservationActivity.class);
                    intent.putExtra("carService", (Serializable) carService);
                    intent.putExtra("reservation", (Serializable) reservation);
                    intent.putExtra("addServ", ((AdditionalServicesAdapter) adapter).getAdditional());
                    startActivity(intent);
                } else {
                    Toast.makeText(VehicleActivity.this,
                            NetworkUtils.getConnectivityStatusString(VehicleActivity.this),
                            Toast.LENGTH_SHORT).show();
                }
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

    @Override
    protected void onResume() {
        setLikePictureForVehicle();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        connectionReceiver = new ConnectionReceiver();
        registerReceiver(connectionReceiver, intentFilter);
        super.onResume();
    }

    private void setLikePictureForVehicle() {
        String email = getIntent().getStringExtra("email");
        long userId = userDatabase.userDao().loadSingleByEmail(email);

        int counter = 0;

        UserWithVehiclesAndReviews vehicles = userDatabase.userDao().getUserWithVehiclesAndReviews(userId);
        for (VehicleWithReviews vehicleWithReviews : vehicles.vehiclesWithReviews) {

            if (vehicleWithReviews.vehicle.getId() == vehicle.getId()) {
                counter++;
                clickedImage.setVisibility(View.VISIBLE);
                notClickedImage.setVisibility(View.GONE);
                break;
            }
        }

        if(counter==0){
            clickedImage.setVisibility(View.GONE);
            notClickedImage.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onPause(){
        unregisterReceiver(connectionReceiver);
        super.onPause();
    }


}
