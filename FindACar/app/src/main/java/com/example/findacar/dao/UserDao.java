package com.example.findacar.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.findacar.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);


    // get all data query
    @Query("SELECT * FROM user")
    List<User> getAll();
}
