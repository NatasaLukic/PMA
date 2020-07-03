package com.example.findacar.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.findacar.activites.DashboardActivity;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.model.Review;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.VehicleWithReviews;
import com.example.findacar.modelDTO.SyncRequestDTO;
import com.example.findacar.modelDTO.SyncResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncService extends Service {

    public static String RESULT_CODE = "RESULT_CODE";
    public UserDatabase userDatabase;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Intent ints = new Intent(DashboardActivity.SYNC_DATA);
        int status = NetworkUtils.getConnectivityStatus(getApplicationContext());
        ints.putExtra(RESULT_CODE, status);

        if(status == NetworkUtils.TYPE_WIFI || status == NetworkUtils.TYPE_MOBILE){

            userDatabase = UserDatabase.getInstance(getApplicationContext());
            List<SyncRequestDTO> checks = getVehicles();

            Call<List<SyncResponseDTO>> call = ServiceUtils.findACarService.checkSync(checks);
            call.enqueue(new Callback<List<SyncResponseDTO>>() {
                @Override
                public void onResponse(Call<List<SyncResponseDTO>> call, Response<List<SyncResponseDTO>> response) {
                    if (response.code() == 200) {
                        List<SyncResponseDTO> news = response.body();

                        if (news.size() != 0){

                            for (SyncResponseDTO syncResponseDTO : news) {

                                List<Review> reviewsNew = syncResponseDTO.getNewReviews();

                                System.out.println("Ovo su novi");

                                List<Review> forDatabase = new ArrayList<Review>();

                                for (Review review : reviewsNew) {
                                    System.out.println("omm " + review.getComment());

                                    if (userDatabase.userDao().getReview(review.id) != null) {
                                        // postoji
                                    } else {
                                        // nov komentar
                                        forDatabase.add(review);
                                    }
                                }

                                userDatabase.userDao().insertNewReviewsForVehicle(syncResponseDTO.getNewVersion(), syncResponseDTO.getVehicleId(), forDatabase);

                            }
                    }

                    }else{
                        Log.d("REZ","Meesage recieved: "+response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<SyncResponseDTO>> call, Throwable t) {
                    Log.d("REZ", t.getMessage() != null?t.getMessage():"error");
                }
            });
        }

        sendBroadcast(ints);

        stopSelf();

        return START_NOT_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public List<SyncRequestDTO> getVehicles(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String email = preferences.getString("user", "0");

        System.out.println("email " + email);

        long userId = userDatabase.userDao().loadSingleByEmail(email);

        System.out.println("userid "+ userId);

        UserWithVehiclesAndReviews userWithVehiclesAndReviews = userDatabase.userDao()
                .getUserWithVehiclesAndReviews(userId);

        List<SyncRequestDTO> checks = new ArrayList<SyncRequestDTO>();

        for(VehicleWithReviews vehicleWithReviews : userWithVehiclesAndReviews.vehiclesWithReviews){
            SyncRequestDTO syncDTO = new SyncRequestDTO(vehicleWithReviews.vehicle.getVehicleId(),
                    vehicleWithReviews.vehicle.getId(), vehicleWithReviews.vehicle.getVersion());

            checks.add(syncDTO);
        }

        return checks;


    }


}
