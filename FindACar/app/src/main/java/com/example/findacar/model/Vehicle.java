package com.example.findacar.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

@Entity(tableName="Vehicle")
public class Vehicle implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long vehicleId;

    @SerializedName("id")
    @Expose
    @Ignore
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

    @SerializedName("imageFile")
    @Expose
    private String imageFile;

    private String imagePath;

    @SerializedName("reviews")
    @Expose
    @Ignore
    private List<Review> reviews;

    // private ArrayList<Integer> images;


    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public double getPriceForDays() {
        return priceForDays;
    }

    public void setPriceForDays(double priceForDays) {
        this.priceForDays = priceForDays;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}