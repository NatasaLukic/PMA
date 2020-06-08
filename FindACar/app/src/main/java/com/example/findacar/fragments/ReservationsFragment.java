package com.example.findacar.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.adapters.ResTabAdapter;
import com.example.findacar.model.Reservation;
import com.example.findacar.service.ServiceUtils;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem currentRes, previousRes;
    public ResTabAdapter tabAdapter;
    List<Reservation> prev = new ArrayList<Reservation>();
    List<Reservation> active = new ArrayList<Reservation>();
    private String email;

    public ReservationsFragment(){

    }

    public ReservationsFragment(String email){
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_reservations_tabs, container, false);


        Call<List<Reservation>> call = ServiceUtils.findACarService.getUserReservations(email);

        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {

                if(response.isSuccessful()){
                    List<Reservation> res = response.body();

                    for(Reservation r : res){
                        if(checkDate(r) == true){
                            active.add(r);
                        } else {
                            prev.add(r);
                        }
                    }
                    viewPager = (ViewPager) view.findViewById(R.id.viewPagerRes);
                    tabAdapter = new ResTabAdapter(getChildFragmentManager(), tabLayout.getTabCount(), prev, active, email);
                    viewPager.setAdapter(tabAdapter);

                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });


        tabLayout = (TabLayout) view.findViewById(R.id.resTabId);
        currentRes = (TabItem) view.findViewById(R.id.currentResTabId);
        previousRes = (TabItem) view.findViewById(R.id.prevResTabId);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0){
                    tabAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    tabAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }

    public boolean checkDate(Reservation r){

        if (new Date().after(r.getReturnDate())) {
            return false;
        } else {
            return true;
        }
    }
}
