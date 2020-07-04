package com.example.findacar.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.findacar.model.Review;
import com.example.findacar.model.User;
import com.example.findacar.model.UserVehicleCrossRef;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.Vehicle;
import com.example.findacar.model.VehicleWithReviews;

import java.util.List;

@Dao
public abstract class UserDao {

    @Insert
    public abstract void insert(User user);

    @Insert
    public abstract long insert(Vehicle vehicle);

    @Update
    public abstract void update(Vehicle vehicle);

    @Update
    public abstract void update(User user);

    @Delete
    public abstract void delete(Vehicle vehicle);

    @Transaction
    @Query("DELETE FROM vehicle where vehicleId=:vehicleId")
    public abstract void deleteVehicle(long vehicleId);

    @Insert
    public abstract void insertAll(List<Review> reviews);

    @Insert
    public abstract void insert(UserVehicleCrossRef userVehicleCrossRef);

    @Delete
    public abstract void delete(UserVehicleCrossRef userVehicleCrossRef);

    @Query("SELECT * FROM UserVehicleCrossRef WHERE userId=:userId  AND  vehicleId=:vehicleId")
    public abstract UserVehicleCrossRef findOneByUserIdAndVehicleId(long userId, long vehicleId);


    @Query("SELECT * FROM user")
    public abstract List<User> getAll();

    @Query("SELECT userId FROM user WHERE email=:email ")
    public abstract long loadSingleByEmail(String email);

    @Query("SELECT * FROM user WHERE email=:email ")
    public abstract User getUser(String email);

    @Query("SELECT firstName FROM user WHERE email=:email ")
    public abstract String loadSingle(String email);

    @Query("SELECT lastName FROM user WHERE email=:email ")
    public abstract String loadSingleLastName(String email);

    @Transaction
    @Query("SELECT * FROM user where userId=:userId")
    public abstract UserWithVehiclesAndReviews getUserWithVehiclesAndReviews(long userId);

    @Transaction
    @Query("SELECT * FROM vehicle where vehicleId=:vehicleId")
    public abstract Vehicle getVehicle(long vehicleId);

    @Transaction
    @Query("SELECT * FROM vehicle where id=:id")
    public abstract Vehicle getVehicleByServerId(long id);
    @Transaction
    @Query("SELECT * FROM vehicle where vehicleId=:vehicleId")
    public abstract VehicleWithReviews getVehicleWithReviews(long vehicleId);

    @Transaction
    @Query("SELECT * FROM review where id=:id")
    public abstract Review getReview(long id);

    public void insertReview(Review review){


    }

    @Transaction
    @Query("DELETE FROM review where vehicleOwnerId=:vehicleId")
    public abstract void deleteReviewsForVehicle(long vehicleId);

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


    public void insertNewReviewsForVehicle(long newVersion, long vehicleId, List<Review> reviews){

        Vehicle v = getVehicle(vehicleId);
        v.setVersion(newVersion);
        update(v);

        for(Review review : reviews){
            review.setVehicleOwnerId(vehicleId);
        }

        insertAll(reviews);
    }
}
