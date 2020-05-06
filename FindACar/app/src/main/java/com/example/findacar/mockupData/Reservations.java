package com.example.findacar.mockupData;

import com.example.findacar.R;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.User;
import com.example.findacar.model.Vehicle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Reservations {
    public static List<Reservation> getReservations(){

        List<Reservation> reservations = new ArrayList<>();
        User user = new User("Pera", "Peric", "pera@gmail.com", "");
        Vehicle v1 = new Vehicle("Toyota Aygo", 4, 2, "Economy",true,
                true, R.drawable.toyota_aygo);
        Date pickupDate = new GregorianCalendar(2020, Calendar.APRIL, 15).getTime();
        Date returnDate = new GregorianCalendar(2020, Calendar.APRIL, 22).getTime();
        Reservation r1 = new Reservation(user, v1, pickupDate, returnDate, 15.300);

        pickupDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 15).getTime();
        returnDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 22).getTime();
        Reservation r2 = new Reservation(user, v1, pickupDate, returnDate, 13.300);

        pickupDate = new GregorianCalendar(2020, Calendar.MAY, 15).getTime();
        returnDate = new GregorianCalendar(2020, Calendar.MAY, 22).getTime();
        Reservation r3 = new Reservation(user, v1, pickupDate, returnDate, 16.300);

        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        return reservations;
    }
}
