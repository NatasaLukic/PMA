package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.fragments.AboutServiceFragment;
import com.example.findacar.fragments.FilterFragment;
import com.example.findacar.fragments.VehicleListFragment;
import com.example.findacar.model.CarService;
import com.example.findacar.model.FilterVehicles;
import com.example.findacar.modelDTO.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.example.findacar.utils.ICarServiceDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarServiceDetailsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ICarServiceDetails {

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView bottomNavigationViewFilter;
    private Fragment currentFragment;
    private CarService carService;
    private List<Vehicle> vehicles;


    private String nameOfPhoto = "photo_";

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

        carService = (CarService) getIntent().getSerializableExtra("carService");
        getSupportActionBar().setTitle(carService.getName());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentFragment instanceof FilterFragment || currentFragment instanceof AboutServiceFragment){
                    Fragment f = new VehicleListFragment(vehicles);

                    bottomNavigationView.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                            .replace(R.id.listOfVehicles, f).commit();
                    currentFragment = f;
                } else if (currentFragment instanceof VehicleListFragment) {

                    finish();
                }
            }
        });

        final SearchVehiclesDTO searchVehiclesDTO = (SearchVehiclesDTO) getIntent().getSerializableExtra("searchForVehicles");
        Call<List<Vehicle>> call = ServiceUtils.findACarService.searchDates(searchVehiclesDTO);

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                System.out.println("nesto");
                if(response.isSuccessful()){
                    System.out.println("usao");

                    int counter = 0;
                    response.body();
                    Gson gson = new Gson();
                    vehicles = response.body();
                    /*
                    //copy list
                    ArrayList<Vehicle> vehicles1 = new ArrayList<Vehicle>(vehicles);
                    for(int i=0; i<vehicles.size(); i++){
                        counter++;
                        try {
                            String urPath = returnUri(vehicles.get(i).getImageFile(), counter);
                            vehicles1.get(i).setImageFile(null);
                            vehicles1.get(i).setImagePath(urPath);
                            Log.e("putanja", vehicles1.get(i).getImagePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
*/
                    Fragment fragment = new VehicleListFragment(vehicles);
                    currentFragment = fragment;
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                            .replace(R.id.listOfVehicles, fragment);

                    ft.commit();

                } else {

                }

            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Toast.makeText(CarServiceDetailsActivity.this, "Error in API Call", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "Error occurred...", t);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
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

    @Override
    public void filterList(FilterVehicles filterVehicles) {
        ArrayList<Vehicle> filtered = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (checkVehicle(v, filterVehicles)) {
                filtered.add(v);
            }
        }
        navigateToVehicleList(filtered);
    }

    @Override
    public void navigateToVehicleList(ArrayList<Vehicle> vehicles) {
        Fragment fragment = new VehicleListFragment(vehicles);
        currentFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                .replace(R.id.listOfVehicles, fragment);

        ft.commit();
    }

    private boolean checkVehicle(Vehicle vehicle, FilterVehicles filter) {

        if (!filter.getMotor().isEmpty() && !(filter.getMotor().contains(vehicle.getFuel()))) {
            return false;
        }

        if (!filter.getNumOfBags().isEmpty() && vehicle.getCases() < 2) {
            return false;
        }

        /*if (filter.getNumOfBags().isEmpty() && vehicle.getCases() > 2) {
            return false;
        }*/

        if (!filter.getTransmission().isEmpty()) {
            if (vehicle.isAutom()) {
                if (filterByTransmissionManual(filter.getTransmission()) && !filterByTransmissionAutomatic(filter.getTransmission()) ) {
                    return false;
                }
            } else {
                if (!filterByTransmissionManual(filter.getTransmission()) && filterByTransmissionAutomatic(filter.getTransmission()) ) {
                    return false;
                }
            }

        }

        if (!filter.getVahicleType().isEmpty() && !(filter.getVahicleType().contains(vehicle.getType()))) {
            return false;
        }

        if (filter.isAirCond() && !vehicle.isAirCond()) {
            return false;
        }
        if (!filter.isAirCond() && vehicle.isAirCond()) {
            return false;
        }

        return true;
    }

    private boolean filterByTransmissionAutomatic(ArrayList<String> filter) {
        return filter.contains("Automatic") || filter.contains("Automatski");
    }

    private boolean filterByTransmissionManual(ArrayList<String> filter){
       return filter.contains("Manual") || filter.contains("Rucni");
    }
}
