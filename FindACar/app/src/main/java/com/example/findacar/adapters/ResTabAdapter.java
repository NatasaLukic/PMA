package com.example.findacar.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.fragments.CurrentReservationsFragment;
import com.example.findacar.fragments.ListResultsFragment;
import com.example.findacar.fragments.PreviousReservationsFragment;
import com.example.findacar.model.Reservation;

import java.util.List;

public class ResTabAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private List<Reservation> res;
    private String email;
    List<Reservation> prev;
    List<Reservation> active;

    public ResTabAdapter(FragmentManager fm, int numOfTabs, List<Reservation> prev, List<Reservation> active, String email) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.prev = prev;
        this.active = active;
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