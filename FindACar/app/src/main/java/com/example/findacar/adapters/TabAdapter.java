package com.example.findacar.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.fragments.ListResultsFragment;
import com.example.findacar.fragments.MapResultsFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private int numOfItems;

    public TabAdapter(FragmentManager fm, int numOfItems) {
        super(fm);
        this.numOfItems = numOfItems;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListResultsFragment();
            case 1:
                return new MapResultsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.numOfItems;
    }
}
