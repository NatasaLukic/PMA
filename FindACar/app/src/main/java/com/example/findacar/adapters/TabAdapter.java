package com.example.findacar.adapters;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.fragments.ListResultsFragment;
import com.example.findacar.fragments.MapResultsFragment;
import com.example.findacar.model.CarService;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private int numOfItems;
    private List<CarService> list;

    public TabAdapter(FragmentManager fm, int numOfItems, List<CarService> p) {
        super(fm);
        this.numOfItems = numOfItems;
        this.list = p;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListResultsFragment(this.list);
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
