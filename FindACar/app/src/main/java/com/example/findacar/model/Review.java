package com.example.findacar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Review  implements Serializable {
    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("comment")
    private String comment;
    @Expose
    @SerializedName("rating")
    private float rating;
    @Expose
    @SerializedName("date")
    private Date date;
    @Expose
    @SerializedName("user")
    private User user;
    private CarService service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarService getService() {
        return service;
    }

    public void setService(CarService service) {
        this.service = service;
    }

}
