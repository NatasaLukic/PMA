package com.example.findacar.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.fragments.CurrentReservationsFragment;
import com.example.findacar.fragments.PreviousReservationsFragment;

public class ResTabAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public ResTabAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentReservationsFragment();
            case 1:
                return new PreviousReservationsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }
}