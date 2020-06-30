package com.example.findacar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.utils.IReservationsHelper;
import com.example.findacar.adapters.ResTabAdapter;
import com.example.findacar.model.Reservation;
import com.example.findacar.service.SessionService;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ReservationsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem currentRes, previousRes;
    public ResTabAdapter tabAdapter;
    List<Reservation> prev = new ArrayList<Reservation>();
    List<Reservation> active = new ArrayList<Reservation>();
    private String email;
    private IReservationsHelper reservationsHelper;

    public ReservationsFragment(){

    }


    public ReservationsFragment(String email){
        this.email = email;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        SessionService sessionService = SessionService.getInstance(context);
        reservationsHelper = (IReservationsHelper)context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_reservations_tabs, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.resTabId);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerRes);

        currentRes = (TabItem) view.findViewById(R.id.currentResTabId);
        previousRes = (TabItem) view.findViewById(R.id.prevResTabId);

        tabAdapter = new ResTabAdapter(getChildFragmentManager(), tabLayout.getTabCount(), reservationsHelper.getPrevious(), reservationsHelper.getCurrent(), email);

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

        return view;

    }



}
