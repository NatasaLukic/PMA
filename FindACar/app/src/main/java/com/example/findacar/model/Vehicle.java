package com.example.findacar.model;

import java.util.ArrayList;

public class Vehicle {

    private String name;
    private int numOfSeats;
    private int numOfDoors;
    private String type;
    private boolean airCond;
    private boolean automTrans;
    private int image;
    private String registratedUntil;
    private ArrayList<Integer> images;
    private int yearOfProd;
    private String fuel;
    private int numOfCases;
    private String description;

    public Vehicle(String name, int numOfSeats, int numOfDoors, String type, boolean airCond, boolean automTrans, int image, String registratedUntil) {
        this.name = name;
        this.numOfSeats = numOfSeats;
        this.numOfDoors = numOfDoors;
        this.type = type;
        this.airCond = airCond;
        this.automTrans = automTrans;
        this.image = image;
        this.registratedUntil = registratedUntil;
        this.images = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public boolean isAirCond() {
        return airCond;
    }

    public void setAirCond(boolean airCond) {
        this.airCond = airCond;
    }

    public boolean isAutomTrans() {
        return automTrans;
    }

    public void setAutomTrans(boolean automTrans) {
        this.automTrans = automTrans;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getRegistratedUntil() { return registratedUntil; }

    public void setRegistratedUntil(String registratedUntil) { this.registratedUntil = registratedUntil; }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public int getYearOfProd() { return yearOfProd; }

    public void setYearOfProd(int yearOfProd) { this.yearOfProd = yearOfProd; }

    public String getFuel() { return fuel; }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getNumOfCases() {
        return numOfCases;
    }

    public void setNumOfCases(int numOfCases) {
        this.numOfCases = numOfCases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
