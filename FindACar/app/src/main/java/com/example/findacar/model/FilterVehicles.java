package com.example.findacar.model;

import java.util.ArrayList;

public class FilterVehicles {
    private ArrayList<String> vahicleType;
    private ArrayList<String> transmission;
    private ArrayList<String> motor;
    private ArrayList<String> numOfBags;
    private boolean airCond;

    public FilterVehicles() {
        vahicleType = new ArrayList<>();
        transmission = new ArrayList<>();
        motor = new ArrayList<>();
        numOfBags = new ArrayList<>();
    }

    public ArrayList<String> getVahicleType() {
        return vahicleType;
    }

    public void setVahicleType(ArrayList<String> vahicleType) {
        this.vahicleType = vahicleType;
    }

    public ArrayList<String> getTransmission() {
        return transmission;
    }

    public void setTransmission(ArrayList<String> transmission) {
        this.transmission = transmission;
    }

    public ArrayList<String> getMotor() {
        return motor;
    }

    public void setMotor(ArrayList<String> motor) {
        this.motor = motor;
    }

    public ArrayList<String> getNumOfBags() {
        return numOfBags;
    }

    public void setNumOfBags(ArrayList<String> numOfBags) {
        this.numOfBags = numOfBags;
    }

    public boolean isAirCond() {
        return airCond;
    }

    public void setAirCond(boolean airCond) {
        this.airCond = airCond;
    }
}
