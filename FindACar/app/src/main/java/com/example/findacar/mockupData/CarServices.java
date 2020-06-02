package com.example.findacar.mockupData;

import com.example.findacar.R;
import com.example.findacar.model.Address;
import com.example.findacar.model.CarService;

import java.util.ArrayList;
import java.util.List;

public class CarServices {

    public static List<CarService> getCarServices(){

        ArrayList<CarService> carServices = new ArrayList<CarService>();

        CarService c1 = new CarService("Servis 1", new Address("f","f","f","d"));
        CarService c2 = new CarService("Servis 2", new Address("f","f","f","d"));
        CarService c3 = new CarService("Servis 3", new Address("f","f","f","d"));

        carServices.add(c1);
        carServices.add(c2);
        carServices.add(c3);

        return carServices;

    }
}
