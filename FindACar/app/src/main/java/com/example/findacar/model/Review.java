package com.example.findacar.model;

import java.time.LocalDate;
import java.util.Date;

public class Review {
    private String comment;
    private int rate;
    private Date date;
    private User user;
    private CarService service;

    public Review(String comment, int rate, Date date, User user, CarService service) {
        this.comment = comment;
        this.rate = rate;
        this.date = date;
        this.user = user;
        this.service = service;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
