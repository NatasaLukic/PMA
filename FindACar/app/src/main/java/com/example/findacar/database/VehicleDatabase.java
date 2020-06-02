package com.example.findacar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.findacar.dao.VehicleDao;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Vehicle;

@Database(entities = {Vehicle.class},  version = 1)
public abstract class VehicleDatabase extends RoomDatabase {

    private static VehicleDatabase vehicleDatabase;
    private static  String DATABASE_NAME = "vehicle";

    public synchronized static VehicleDatabase getInstance(Context context) {
        if(vehicleDatabase == null) {
            vehicleDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    VehicleDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return vehicleDatabase;
    }

    // create Dao
    public  abstract VehicleDao vehicleDao();
}
