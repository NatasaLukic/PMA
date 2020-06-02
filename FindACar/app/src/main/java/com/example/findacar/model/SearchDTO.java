package com.example.findacar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDTO {

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("pickUpDate")
    @Expose
    private String pickUpDate;

    @SerializedName("returnDate")
    @Expose
    private String returnDate;

    public SearchDTO(String city, String pickUpDate, String returnDate) {
        this.city = city;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
