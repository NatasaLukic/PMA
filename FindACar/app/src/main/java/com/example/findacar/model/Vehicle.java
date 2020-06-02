package com.example.findacar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle implements Serializable, Parcelable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("seats")
    @Expose
    private int seats;

    @SerializedName("doors")
    @Expose
    private int doors;

    @SerializedName("cases")
    @Expose
    private int cases;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("airCond")
    @Expose
    private boolean airCond;

    @SerializedName("autom")
    @Expose
    private boolean autom;

    @SerializedName("regUntil")
    @Expose
    private String regUntil;

    @SerializedName("prodYear")
    @Expose
    private int prodYear;

    @SerializedName("fuel")
    @Expose
    private String fuel;

    @SerializedName("deposit")
    @Expose
    private boolean deposit;

    @SerializedName("mileage")
    @Expose
    private String mileage;

    // private int image;
    // private ArrayList<Integer> images;


    public Vehicle(String name, String description, int seats, int doors, int cases,
                   String type, boolean airCond, boolean autom) {
        this.name = name;
        this.description = description;
        this.seats = seats;
        this.doors = doors;
        this.cases = cases;
        this.type = type;
        this.airCond = airCond;
        this.autom = autom;
    }

    protected Vehicle(Parcel in) {
        name = in.readString();
        description = in.readString();
        seats = in.readInt();
        doors = in.readInt();
        cases = in.readInt();
        type = in.readString();
        airCond = in.readByte() != 0;
        autom = in.readByte() != 0;
        regUntil = in.readString();
        prodYear = in.readInt();
        fuel = in.readString();
        deposit = in.readByte() != 0;
        mileage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(seats);
        dest.writeInt(doors);
        dest.writeInt(cases);
        dest.writeString(type);
        dest.writeByte((byte) (airCond ? 1 : 0));
        dest.writeByte((byte) (autom ? 1 : 0));
        dest.writeString(regUntil);
        dest.writeInt(prodYear);
        dest.writeString(fuel);
        dest.writeByte((byte) (deposit ? 1 : 0));
        dest.writeString(mileage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAirCond() {
        return airCond;
    }

    public void setAirCond(boolean airCond) {
        this.airCond = airCond;
    }

    public boolean isAutom() {
        return autom;
    }

    public void setAutom(boolean autom) {
        this.autom = autom;
    }

    public String getRegUntil() {
        return regUntil;
    }

    public void setRegUntil(String regUntil) {
        this.regUntil = regUntil;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public boolean isDeposit() {
        return deposit;
    }

    public void setDeposit(boolean deposit) {
        this.deposit = deposit;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
