package com.example.findacar.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "vehicleId"})
public class UserVehicleCrossRef {

    public long userId;
    public long vehicleId;
}
