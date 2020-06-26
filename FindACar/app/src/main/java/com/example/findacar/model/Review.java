package com.example.findacar.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "review")
public class Review  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int reviewId;

    public long vehicleOwnerId;

    @Expose
    @SerializedName("comment")
    @ColumnInfo(name = "comment")
    private String comment;

    @Expose
    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    private float rating;

    @Expose
    @SerializedName("date")
    @ColumnInfo(name = "date")
    private Date date;

    @Expose
    @SerializedName("user")
    @Ignore
    private User user;

    @ColumnInfo(name="nameUser")
    private String nameUser;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public long getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(long vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
