package com.example.findacar.modelDTO;

import com.example.findacar.model.AdditionalService;
import com.example.findacar.model.Vehicle;

import java.io.Serializable;
import java.util.List;

public class CreateReservationDTO implements Serializable {
    private String userEmail;
    private Vehicle vehicle;
    private String pickUpDate;
    private String returnDate;
    private double price;
    private List<AdditionalService> includedAdditionalServices;

    public CreateReservationDTO() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<AdditionalService> getIncludedAdditionalServices() {
        return includedAdditionalServices;
    }

    public void setIncludedAdditionalServices(List<AdditionalService> includedAdditionalServices) {
        this.includedAdditionalServices = includedAdditionalServices;
    }
}
