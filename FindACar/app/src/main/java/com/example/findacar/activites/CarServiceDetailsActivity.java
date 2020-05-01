package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.fragments.VehicleList;

import org.w3c.dom.Text;

public class CarServiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_service_details);

        TextView service = (TextView) findViewById(R.id.service);
        service.setText(getIntent().getStringExtra("service"));

        Fragment fragment = new VehicleList();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setTransition((FragmentTransaction.TRANSIT_FRAGMENT_OPEN))
                .replace(R.id.listOfVehicles, fragment);


        ft.commit();

    }
}
