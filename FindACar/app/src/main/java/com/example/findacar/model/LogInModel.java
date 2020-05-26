package com.example.findacar.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LogInModel implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LogInModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
