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

    public ResTabAdapter(FragmentManager fm, int numOfTabs, List<Reservation> res) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.res = res;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentReservationsFragment();
            case 1:
                return new PreviousReservationsFragment(res);
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }
}