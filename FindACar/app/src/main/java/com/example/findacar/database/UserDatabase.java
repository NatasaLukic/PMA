package com.example.findacar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.findacar.converters.Converters;
import com.example.findacar.dao.UserDao;
import com.example.findacar.model.Review;
import com.example.findacar.model.User;
import com.example.findacar.model.UserVehicleCrossRef;
import com.example.findacar.model.Vehicle;

@Database(entities = {User.class, Vehicle.class, Review.class, UserVehicleCrossRef.class},  version = 1)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase userDatabase;
    private static  String DATABASE_NAME = "user";

    public synchronized static UserDatabase getInstance(Context context) {
        if(userDatabase == null) {
            userDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;
    }

    // create Dao
    public  abstract UserDao userDao();
}
