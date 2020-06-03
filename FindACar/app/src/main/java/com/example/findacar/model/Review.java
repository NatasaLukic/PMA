package com.example.findacar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

public class Review  implements Parcelable, Serializable {
    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("comment")
    private String comment;
    @Expose
    @SerializedName("rate")
    private float rate;
    @Expose
    @SerializedName("date")
    private Date date;
    @Expose
    @SerializedName("user")
    private User user;
    private CarService service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    public CarService getService() {
        return service;
    }

    public void setService(CarService service) {
        this.service = service;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    protected Review(Parcel in) {
        id = in.readLong();
        comment = in.readString();
        rate = in.readFloat();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        try {
            date = formatter.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(comment);
        dest.writeFloat(rate);
        dest.writeString(date.toString());
        dest.writeParcelable(user, flags);
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}
