package com.example.findacar.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.findacar.model.Review;
import com.example.findacar.model.User;
import com.example.findacar.model.UserVehicleCrossRef;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.Vehicle;

import java.util.List;

@Dao
public abstract class UserDao {

    @Insert
    public abstract void insert(User user);

    @Insert
    public abstract long insert(Vehicle vehicle);

    @Insert
    public abstract void insertAll(List<Review> reviews);

    @Insert
    public abstract void insert(UserVehicleCrossRef userVehicleCrossRef);

    @Query("SELECT * FROM user")
    public abstract List<User> getAll();

    @Query("SELECT userId FROM user WHERE email=:email ")
    public abstract long loadSingleByEmail(String email);

    @Query("SELECT firstName FROM user WHERE email=:email ")
    public abstract String loadSingle(String email);

    @Transaction
    @Query("SELECT * FROM user where userId=:userId")
    public abstract UserWithVehiclesAndReviews getUserWithVehiclesAndReviews(long userId);

    public long insertVehicle(Vehicle vehicle) {

        long vehicleId = insert(vehicle);

        if(vehicle.getReviews() != null) {
            insertReviewsForVehicle(vehicleId, vehicle.getReviews());
        }

        return vehicleId;
    }

    public void insertReviewsForVehicle(long vehicleId, List<Review> reviews){


        for(Review review : reviews){
            review.setVehicleOwnerId(vehicleId);
        }

        insertAll(reviews);
    }




}
