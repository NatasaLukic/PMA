package com.example.findacar.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UserWithVehiclesAndReviews {

    @Embedded
    public User user;

    @Relation(
            entity = Vehicle.class,
            parentColumn = "userId",
            entityColumn = "vehicleId",
            associateBy = @Junction(UserVehicleCrossRef.class))
    public List<VehicleWithReviews> vehiclesWithReviews;
}
