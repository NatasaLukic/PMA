package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findacar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_toolbar);
        bottomNavigationView.setVisibility(View.GONE);

        return inflater.inflate(R.layout.fragment_filter, container, false);

    }
}
