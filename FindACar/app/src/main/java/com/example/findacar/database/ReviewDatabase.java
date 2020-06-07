package com.example.findacar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.findacar.converters.Converters;
import com.example.findacar.dao.ReviewDao;
import com.example.findacar.model.Review;

@Database(entities = {Review.class},  version = 1)
@TypeConverters({Converters.class})
public abstract class ReviewDatabase extends RoomDatabase {

    private static ReviewDatabase reviewDatabase;
    private static  String DATABASE_NAME = "review";

    public synchronized static ReviewDatabase getInstance(Context context) {
        if(reviewDatabase == null) {
            reviewDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ReviewDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return reviewDatabase;
    }

    public abstract ReviewDao reviewDao();
}
