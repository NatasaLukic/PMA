package com.example.findacar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.findacar.dao.UserDao;
import com.example.findacar.model.User;

@Database(entities = {User.class},  version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase userDatabase;
    private static  String DATABASE_NAME = "findacar";

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
