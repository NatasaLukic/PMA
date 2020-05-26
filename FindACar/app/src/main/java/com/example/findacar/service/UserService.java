package com.example.findacar.service;

import com.example.findacar.model.LogInModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface UserService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    @POST("logIn")
    Call<Boolean> login(@Body LogInModel logIn);

}
