package com.example.findacar.modelDTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LogInDTO implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LogInDTO() {
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
