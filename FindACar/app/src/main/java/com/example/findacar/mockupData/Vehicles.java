package com.example.findacar.mockupData;

import com.example.findacar.R;
import com.example.findacar.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Vehicles {

    public static List<Vehicle> getVehicles(){

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        Vehicle v1 = new Vehicle("Toyota Aygo", 4, 2, "Economy",true,
                true, R.drawable.toyota_aygo);
        Vehicle v2 = new Vehicle("Dacia Sandero", 4, 4, "Medium", true,
                false, R.drawable.dacia_sandero);
        Vehicle v3 = new Vehicle("Dacia Logan", 5, 4, "Medium", true,
                false, R.drawable.dacia_logan);

        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);

        return vehicles;

    }
}
