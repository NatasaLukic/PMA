package com.example.findacar.service;

import com.example.findacar.model.CarService;
import com.example.findacar.model.LogInModel;
import com.example.findacar.model.RegisterDTO;
import com.example.findacar.model.SearchDTO;
import com.example.findacar.model.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    @POST("/user/logIn")
    Call<Boolean> login(@Body LogInModel logIn);

    @POST("user/auth/register")
    Call<ResponseBody> register(@Body RegisterDTO registerDTO);

    @POST("search/findCity")
    Call<List<CarService>> searchCity(@Body SearchDTO searchDTO);

    @POST("search/findForDates")
    Call<List<Vehicle>> searchDates(@Body SearchVehiclesDTO searchDTO);

}
