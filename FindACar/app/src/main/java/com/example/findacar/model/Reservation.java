package com.example.findacar.model;

import java.util.Date;

public class Reservation {
    private User user;
    private Vehicle vehicle;
    private Date pickUpDate;
    private Date returnDate;
    private double price;

    public Reservation(User user, Vehicle vehicle, Date pickUpDate, Date returnDate, double price) {
        this.user = user;
        this.vehicle = vehicle;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.price = price;
    }
}
