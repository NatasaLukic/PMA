package com.example.findacar.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName="Vehicle")
public class Vehicle implements Serializable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;


    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;


    @ColumnInfo(name = "seats")
    @SerializedName("seats")
    @Expose
    private int seats;

    @ColumnInfo(name = "doors")
    @SerializedName("doors")
    @Expose
    private int doors;

    @ColumnInfo(name = "cases")
    @SerializedName("cases")
    @Expose
    private int cases;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    @Expose
    private String type;

    @ColumnInfo(name = "airCond")
    @SerializedName("airCond")
    @Expose
    private boolean airCond;

    @ColumnInfo(name = "autom")
    @SerializedName("autom")
    @Expose
    private boolean autom;

    @ColumnInfo(name = "regUntil")
    @SerializedName("regUntil")
    @Expose
    private String regUntil;

    @ColumnInfo(name = "prodYear")
    @SerializedName("prodYear")
    @Expose
    private int prodYear;

    @ColumnInfo(name = "fuel")
    @SerializedName("fuel")
    @Expose
    private String fuel;

    @ColumnInfo(name = "deposit")
    @SerializedName("deposit")
    @Expose
    private boolean deposit;

    @ColumnInfo(name = "mileage")
    @SerializedName("mileage")
    @Expose
    private String mileage;

    @SerializedName("priceForDays")
    @Expose
    private double priceForDays;

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
        id = in.readLong();
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
        priceForDays = in.readDouble();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPriceForDays() {
        return priceForDays;
    }

    public void setPriceForDays(double priceForDays) {
        this.priceForDays = priceForDays;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
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
        dest.writeDouble(priceForDays);
    }
}
