package com.example.findacar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.adapters.ResTabAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ReservationsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem currentRes, previousRes;
    public ResTabAdapter tabAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_reservations, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.resTabId);
        currentRes = (TabItem) view.findViewById(R.id.currentResTabId);
        previousRes = (TabItem) view.findViewById(R.id.prevResTabId);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerRes);
        tabAdapter = new ResTabAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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
