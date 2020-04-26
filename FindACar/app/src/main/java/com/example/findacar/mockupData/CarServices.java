package com.example.findacar.mockupData;

import com.example.findacar.R;
import com.example.findacar.model.CarService;

import java.util.ArrayList;
import java.util.List;

public class CarServices {

    public static List<CarService> getCarServices(){

        ArrayList<CarService> carServices = new ArrayList<CarService>();

        CarService c1 = new CarService("Servis 1", "Narodnog Fronta 23", R.drawable.ic_directions_car_black_24dp);
        CarService c2 = new CarService("Servis 2", "Janka Cmelika 24", R.drawable.ic_directions_car_black_24dp);
        CarService c3 = new CarService("Servis 3", "Radnicka 10", R.drawable.ic_directions_car_black_24dp);

        carServices.add(c1);
        carServices.add(c2);
        carServices.add(c3);

        return carServices;

    }
}
