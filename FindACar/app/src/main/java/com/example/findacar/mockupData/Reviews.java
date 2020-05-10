package com.example.findacar.mockupData;

import com.example.findacar.model.CarService;
import com.example.findacar.model.Review;
import com.example.findacar.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Reviews {
    public static List<Review> getReviews(){
        User user1 = new User("Pera", "Peric", "pera@gmail.com", "");
        User user2 = new User("Mika", "Mikic", "mika@gmail.com", "");
        User user = new User("Zika", "Zikic", "zika@gmail.com", "");
        CarService c1 = new CarService("Servis 1", "Narodnog Fronta 23");
        Date pickupDate = new GregorianCalendar(2020, Calendar.APRIL, 15).getTime();
        Review review1 = new Review("Super", 5 , pickupDate, user1, c1);
        Review review2 = new Review(" Phasellus accumsan metus ut ante sollicitudin tincidunt.", 4 , pickupDate, user1, c1);
        Review review3 = new Review("Mauris mattis ex quis ipsum venenatis ornare.", 2 , pickupDate, user1, c1);
        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        return reviews;
    }
}
