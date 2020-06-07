package com.example.findacar.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert
    void insert(Review review);

    @Query("SELECT * FROM review")
    List<Review> getAll();


}
