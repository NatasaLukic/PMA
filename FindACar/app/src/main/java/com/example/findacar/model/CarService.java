package com.example.findacar.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarService implements Serializable {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("about")
    @Expose
    private String about;

    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles;

    @SerializedName("landlinePhone")
    @Expose
    private String landlinePhone;

    @SerializedName("additionalServices")
    @Expose
    private List<AdditionalService> additionalServices;


    public CarService() {

    }

    public CarService(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public CarService(String name, Address address, String phone, String email, String about, List<Vehicle> vehicles) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.about = about;
        this.vehicles = vehicles;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public String getLandlinePhone() {
        return landlinePhone;
    }

    public void setLandlinePhone(String landlinePhone) {
        this.landlinePhone = landlinePhone;
    }

    public List<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
