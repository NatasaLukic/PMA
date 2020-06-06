package com.example.findacar.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.model.VehicleWithReviews;

import java.util.List;

@Dao
public interface VehicleDao {

    @Insert
    void insert(Vehicle vehicle);

    @Query("SELECT * FROM vehicle")
    List<Vehicle> getAll();

    @Transaction
    @Query("SELECT * FROM vehicle WHERE vehicleid = :vehicleId")
    public VehicleWithReviews getVehicleWithReviews(int vehicleId);

}
