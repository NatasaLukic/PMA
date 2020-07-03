package com.example.findacar.service;

import com.example.findacar.model.CarService;
import com.example.findacar.modelDTO.ChangePasswordDTO;
import com.example.findacar.modelDTO.CreateReservationDTO;
import com.example.findacar.modelDTO.EditProfileDTO;
import com.example.findacar.modelDTO.LogInDTO;
import com.example.findacar.modelDTO.RegisterDTO;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.Review;
import com.example.findacar.modelDTO.CreateReviewDTO;
import com.example.findacar.modelDTO.SearchDTO;
import com.example.findacar.modelDTO.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.modelDTO.SyncRequestDTO;
import com.example.findacar.modelDTO.SyncResponseDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface HttpService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    @POST("/user/logIn")
    Call<Object> login(@Body LogInDTO logIn);

    @POST("user/auth/register")
    Call<ResponseBody> register(@Body RegisterDTO registerDTO);

    @POST("search/findCity")
    Call<List<CarService>> searchCity(@Body SearchDTO searchDTO);

    @POST("search/findForDates")
    Call<List<Vehicle>> searchDates(@Body SearchVehiclesDTO searchDTO);

    @POST("reservations")
    Call<ResponseBody> createReservation(@Body CreateReservationDTO reservationDTO);

    @GET("vehicles/{vehicleId}/reviews")
    Call<List<Review>> getVehicleReviews(@Path("vehicleId")Long vehicleId);

    @GET("user/res/{email}")
    Call<List<Reservation>> getUserReservations(@Path("email")String email);

    @POST("reviews/add")
    Call<List<Reservation>> addReview(@Body CreateReviewDTO commentDTO);

    @GET("/user/res/cancelRes/{id}")
    Call<ResponseBody> cancelRes(@Path("id") long id);

    @PUT("/user/{email}/{token}")
    Call<ResponseBody> sendFcmToken(@Path("email") String email, @Path("token") String token);

    @POST("/user/addFavorite/{email}/{idVehicle}")
    Call<ResponseBody> addFavorite(@Path("email") String email, @Path("idVehicle") long idVehicle);

    @POST("/sync/check")
    Call<List<SyncResponseDTO>> checkSync(@Body List<SyncRequestDTO> vehicles);

    @POST("user/auth/changePassword/{email}")
    Call<ResponseBody> changePassword(@Path("email") String email, @Body ChangePasswordDTO changePasswordDTO);

    @DELETE("/user/{email}/favorite-vehicles/{vehicleId}")
    Call<ResponseBody> removeVehicleFromFavorites(@Path("email") String email, @Path("vehicleId") long vehicleId);

    @POST("user/auth/editProfile/{email}")
    Call<ResponseBody> editProfile(@Path("email") String email, @Body EditProfileDTO editProfileDTO);
}
