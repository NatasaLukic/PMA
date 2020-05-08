package com.example.findacar.mockupData;

import com.example.findacar.R;
import com.example.findacar.model.Vehicle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Vehicles {

    public static List<Vehicle> getVehicles()  {

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        Vehicle v1 = new Vehicle("Toyota Aygo", 4, 2, "Economy",true,
                true, R.drawable.toyota_aygo, "12/11/2020");
        Vehicle v2 = new Vehicle("Dacia Sandero", 4, 4, "Medium", false,
                false, R.drawable.dacia_sandero, "18/07/2021");
        Vehicle v3 = new Vehicle("Dacia Logan", 5, 4, "Medium", true,
                false, R.drawable.dacia_logan, "26/03/2021");

        v1.getImages().add(R.drawable.toyota_aygo);
        v1.getImages().add(R.drawable.toyota_aygo2);
        v1.getImages().add(R.drawable.toy_ayi);
        v1.getImages().add(R.drawable.toyota_aygo_seats);

        v1.setFuel("Petrol");
        v2.setFuel("Diesel");
        v3.setFuel("Petrol");

        v1.setNumOfCases(1);
        v2.setNumOfCases(2);
        v3.setNumOfCases(2);

        v1.setYearOfProd(2017);
        v2.setYearOfProd(2017);
        v3.setYearOfProd(2016);

        v2.getImages().add(R.drawable.dacia_sandero);
        v2.getImages().add(R.drawable.dacia_sandero_2);
        v2.getImages().add(R.drawable.dacia_sandero_4);
        v2.getImages().add(R.drawable.dacia_sandero_3);

        v3.getImages().add(R.drawable.dacia_logan);
        v3.getImages().add(R.drawable.dacia_logan_2);
        v3.getImages().add(R.drawable.dacia_logan_3);

        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);

        return vehicles;

    }
}
