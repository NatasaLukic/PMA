package com.example.findacar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.service.ServiceUtils;
import com.example.findacar.utils.IReservationsHelper;
import com.example.findacar.adapters.ResTabAdapter;
import com.example.findacar.model.Reservation;
import com.example.findacar.service.SessionService;
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
    private List<Reservation> prev = new ArrayList<Reservation>();
    private List<Reservation> active = new ArrayList<Reservation>();
    private String email;

    public ReservationsFragment(){

    }


    public ReservationsFragment(String email){
        this.email = email;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        SessionService sessionService = SessionService.getInstance(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_reservations_tabs, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.resTabId);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerRes);

        getData(email);

        currentRes = (TabItem) view.findViewById(R.id.currentResTabId);
        previousRes = (TabItem) view.findViewById(R.id.prevResTabId);

        Log.i("PREV", String.valueOf(prev.size()));
        Log.i("ACT", String.valueOf(active.size()));


        return view;

    }

    private void getData(String userEmail) {
        Call<List<Reservation>> call = ServiceUtils.findACarService.getUserReservations(userEmail);

        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {

                if (response.isSuccessful()) {
                    List<Reservation> res = response.body();
                    Log.e("REZ", String.valueOf(res.size()));
                    for (Reservation r : res) {
                        if (checkDate(r) == true) {
                            active.add(r);
                        } else {
                            prev.add(r);
                        }
                    }

                }

                tabAdapter = new ResTabAdapter(getChildFragmentManager(), tabLayout.getTabCount(), prev, active, email);

                viewPager.setAdapter(tabAdapter);

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

                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                System.out.println("aaaaaaaaaaaaaaa");
                Log.e("ERROR", t.getMessage());
            }
        });

    }

    public boolean checkDate(Reservation r) {

        if (new Date().after(r.getReturnDate())) {
            return false;
        } else {
            return true;
        }
    }




}
