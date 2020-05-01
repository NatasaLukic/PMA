package com.example.findacar.model;

public class Vehicle {

    private String name;
    private int numOfSeats;
    private int numOfDoors;
    private String type;
    private boolean airCond;
    private boolean automTrans;
    private int image;

    public Vehicle(String name, int numOfSeats, int numOfDoors, String type, boolean airCond, boolean automTrans, int image) {
        this.name = name;
        this.numOfSeats = numOfSeats;
        this.numOfDoors = numOfDoors;
        this.type = type;
        this.airCond = airCond;
        this.automTrans = automTrans;
        this.image = image;
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
}
