package com.example.findacar.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class VehicleWithReviews {

    @Embedded
    public Vehicle vehicle;

    @Relation(
            parentColumn = "vehicleId",
            entityColumn = "vehicleOwnerId"
    )
    public List<Review> reviews;


}
