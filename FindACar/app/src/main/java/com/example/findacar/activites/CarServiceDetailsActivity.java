package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.fragments.AboutServiceFragment;
import com.example.findacar.fragments.FilterFragment;
import com.example.findacar.fragments.VehicleListFragment;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Review;
import com.example.findacar.model.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

        final SearchVehiclesDTO searchVehiclesDTO = (SearchVehiclesDTO) getIntent().getSerializableExtra("searchForVehicles");
        Call<List<Vehicle>> call = ServiceUtils.reviewerService.searchDates(searchVehiclesDTO);

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                System.out.println("nesto");
                if(response.isSuccessful()){
                    System.out.println("usao");

                    int counter = 0;
                    response.body();
                    Gson gson = new Gson();
                    List<Vehicle> vehicles = response.body();
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
                    Call<List<Review>> call1 = ServiceUtils.reviewerService.getCarServiceReviews(searchVehiclesDTO.getId());
                    call1.enqueue(new Callback<List<Review>>() {
                        @Override
                        public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                            if(response.isSuccessful()){
                                // TODO Zasto u polje rating ubacuje 0.0????????????????????????
                                List<Review> list = response.body();
                                carService.setReviews(list);
                                //Gson gson = new Gson();
                               // intent1.putExtra("carService", gson.toJson(carService));
                               // startActivity(intent1);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Review>> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });
                    Fragment fragment = new VehicleListFragment(vehicles);
                    currentFragment = fragment;
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                            .replace(R.id.listOfVehicles, fragment);

                    ft.commit();

                } else {
                    System.out.println("usoa fail");
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



    public String returnUri(String image, int counter) throws IOException {
        File f = new File(getCacheDir(), nameOfPhoto+counter);

        FileOutputStream outStream = new FileOutputStream(f);
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0, bytes.length);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outStream);
        outStream.close();

        return f.getAbsolutePath();
    }
}
