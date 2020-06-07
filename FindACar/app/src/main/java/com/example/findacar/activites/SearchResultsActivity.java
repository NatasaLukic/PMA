package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.findacar.R;
import com.example.findacar.fragments.ListResultsFragment;
import com.example.findacar.model.CarService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private Button btnViewMap;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_results);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Search results: " + getIntent().getStringExtra("place"));
        getSupportActionBar().setElevation(0);

        //List of services

        String gsonS = getIntent().getStringExtra("services");
        Type type = new TypeToken<List<CarService>>(){}.getType();

        final ArrayList<CarService> services = gson.fromJson(gsonS, type);

        Fragment fragment = new ListResultsFragment(services);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                .replace(R.id.search_results, fragment);

        ft.commit();
        btnViewMap = findViewById(R.id.btnViewMap);

        btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultsActivity.this, MapsActivity.class);
                intent.putExtra("services", gson.toJson(services));
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
