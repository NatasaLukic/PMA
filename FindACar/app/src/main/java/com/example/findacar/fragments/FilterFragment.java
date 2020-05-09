package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.findacar.R;
import com.example.findacar.adapters.FilterListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    FilterListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expand_list);

        prepareData();

        listAdapter = new FilterListAdapter(getContext(), listDataHeader, listDataChild);

        expandableListView.setAdapter(listAdapter);

     //   BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_toolbar);
       // bottomNavigationView.setVisibility(View.GONE);

        return view;
    }

    private void prepareData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Type of car");
        listDataHeader.add("Transmission");
        listDataHeader.add("Motor");
        listDataHeader.add("Number of bags");
        listDataHeader.add("Air conditioning");

        List<String> types = new ArrayList<String>();
        types.add("Small");
        types.add("Medium");
        types.add("Large");
        types.add("Economy");

        List<String> transmission = new ArrayList<String>();
        transmission.add("Automatic");
        transmission.add("Manual");

        List<String> motors = new ArrayList<String>();
        motors.add("Petrol");
        motors.add("Diesel");

        List<String> numOfBags = new ArrayList<String>();
        numOfBags.add("2 and more");

        List<String> airCond = new ArrayList<String>();
        airCond.add("Air Conditioning");

        listDataChild.put(listDataHeader.get(0), types); // Header, Child data
        listDataChild.put(listDataHeader.get(1), transmission);
        listDataChild.put(listDataHeader.get(2), motors);
        listDataChild.put(listDataHeader.get(3), numOfBags);
        listDataChild.put(listDataHeader.get(4), airCond);

    }
}
