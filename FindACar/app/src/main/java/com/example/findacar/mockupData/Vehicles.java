package com.example.findacar.mockupData;

import com.example.findacar.R;
import com.example.findacar.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Vehicles {

    public static List<Vehicle> getVehicles()  {

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        Vehicle v1 = new Vehicle("Renault Clio", 4, 2, "Economy",true,
                true, R.drawable.reno_clio_1, "12/11/2020");
        Vehicle v2 = new Vehicle("Ford Fiesta", 5, 4, "Medium", false,
                false, R.drawable.ford_fiesta_1, "18/07/2021");
        Vehicle v3 = new Vehicle("Dacia Logan", 5, 5, "Medium", true,
                false, R.drawable.dacia_logan, "26/03/2021");
        Vehicle v4 = new Vehicle("Nissan Qashqai", 5, 5, "Large", true,
                false, R.drawable.nissan1, "26/03/2021");

        v1.getImages().add(R.drawable.reno_clio_1);
        v1.getImages().add(R.drawable.reno_clio_2);
        v1.getImages().add(R.drawable.reno_clio_3);
        v1.getImages().add(R.drawable.reno_clio_4);

        v1.setFuel("Petrol");
        v2.setFuel("Diesel");
        v3.setFuel("Petrol");
        v4.setFuel("Diesel");

        v1.setNumOfCases(2);
        v2.setNumOfCases(2);
        v3.setNumOfCases(3);
        v4.setNumOfCases(5);

        v1.setYearOfProd(2020);
        v2.setYearOfProd(2017);
        v3.setYearOfProd(2016);
        v4.setYearOfProd(2018);

        v2.getImages().add(R.drawable.ford_fiesta_1);
        v2.getImages().add(R.drawable.ford_fiesta_2);
        v2.getImages().add(R.drawable.ford_fiesta_3);
        v2.getImages().add(R.drawable.ford_fiesta_4);

        v3.getImages().add(R.drawable.dacia_logan);
        v3.getImages().add(R.drawable.dacia_logan_3);
        v3.getImages().add(R.drawable.dacia_logan_4);

        v4.getImages().add(R.drawable.nissan_1_2);
        v4.getImages().add(R.drawable.nissan2);
        v4.getImages().add(R.drawable.nissan3);
        v4.getImages().add(R.drawable.nissan4);

        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);
        vehicles.add(v4);

        return vehicles;

    }
}
