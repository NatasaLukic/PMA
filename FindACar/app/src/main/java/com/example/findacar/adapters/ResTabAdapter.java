package com.example.findacar.adapters;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.fragments.CurrentReservationsFragment;
import com.example.findacar.fragments.ListResultsFragment;
import com.example.findacar.fragments.PreviousReservationsFragment;
import com.example.findacar.model.Reservation;
import com.example.findacar.service.ServiceUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResTabAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private List<Reservation> res;
    private String email;
    List<Reservation> prev = new ArrayList<>();
    List<Reservation> active = new ArrayList<>();

    public ResTabAdapter(FragmentManager fm, int numOfTabs,  List<Reservation> prev,  List<Reservation> active, String email) {
        super(fm);
        this.prev = prev;
        this.active = active;
        this.numOfTabs = numOfTabs;
        this.email = email;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentReservationsFragment(active, email);
            case 1:
                return new PreviousReservationsFragment(prev, email);
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }

}