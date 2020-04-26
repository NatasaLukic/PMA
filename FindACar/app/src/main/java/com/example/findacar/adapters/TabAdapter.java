package com.example.findacar.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.fragments.ListResults;
import com.example.findacar.fragments.MapResults;

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
                return new ListResults();
            case 1:
                return new MapResults();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.numOfItems;
    }
}
