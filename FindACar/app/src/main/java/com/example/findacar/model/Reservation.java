package com.example.findacar.model;

import java.util.Date;

public class Reservation {

    private long id;
    private User user;
    private Vehicle vehicle;
    private Date pickUpDate;
    private Date returnDate;
    private Review review;
    private double price;

    public Reservation() {
    }

    public Reservation(User user, Vehicle vehicle, Date pickUpDate, Date returnDate, double price) {
        this.user = user;
        this.vehicle = vehicle;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
