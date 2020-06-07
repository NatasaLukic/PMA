package com.example.findacar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchVehiclesDTO implements Serializable {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("pickUpDate")
    @Expose
    private String pickUpDate;

    @SerializedName("returnDate")
    @Expose
    private String returnDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
