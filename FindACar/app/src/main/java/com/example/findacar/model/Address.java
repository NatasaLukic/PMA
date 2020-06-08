package com.example.findacar.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Address implements Serializable , Parcelable{

    private String street;
    private String city;
    private String country;
    private String postalCode;

    private double x;
    private double y;


    public Address(){

    }
    public Address(String street, String city, String country, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;

    }

    public Address(String street, String city, String country, String postalCode, double x, double y) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.x = x;
        this.y = y;
    }

    protected Address(Parcel in) {
        street = in.readString();
        city = in.readString();
        country = in.readString();
        postalCode = in.readString();
        x = in.readDouble();
        y = in.readDouble();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(postalCode);
        dest.writeDouble(x);
        dest.writeDouble(y);
    }
}
