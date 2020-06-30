package com.example.findacar.model;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.adapters.ResTabAdapter;
import com.example.findacar.service.ServiceUtils;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationsViewModel extends ViewModel {
     public List<Reservation> activeReservations = new ArrayList<>();
     public List<Reservation> previousReservations = new ArrayList<>();

    public ReservationsViewModel(String userEmail){
        this.getData(userEmail);
    }

    private void getData(String userEmail){
        Call<List<Reservation>> call = ServiceUtils.findACarService.getUserReservations(userEmail);
        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {

                if(response.isSuccessful()){
                    List<Reservation> res = response.body();

                    for(Reservation r : res){
                        if(checkDate(r) == true){
                            activeReservations.add(r);
                        } else {
                            previousReservations.add(r);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                System.out.println("UJEBO");
                Log.e("ERRORR", t.getMessage());
            }
        });

    }

    public boolean checkDate(Reservation r){

        if (new Date().after(r.getReturnDate())) {
            return false;
        } else {
            return true;
        }
    }
}
