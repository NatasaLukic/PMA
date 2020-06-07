package com.example.findacar.activites;

import com.example.findacar.model.FilterVehicles;
import com.example.findacar.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public interface ICarServiceDetails {

    void filterList(FilterVehicles filterVehicles);

    void navigateToVehicleList(ArrayList<Vehicle> vehicles);
}
