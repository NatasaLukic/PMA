package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findacar.R;
import com.example.findacar.adapters.VehiclesAdapter;
import com.example.findacar.model.Vehicle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleListFragment extends ListFragment {

    private List<Vehicle> list;

    public VehicleListFragment() {
        // Required empty public constructor
    }

    public VehicleListFragment(List<Vehicle> vehicles) {
        this.list = vehicles;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_toolbar);
        bottomNavigationView.setVisibility(View.VISIBLE);
        View vi = inflater.inflate(R.layout.fragment_vehicle_list, container, false);

        return vi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        VehiclesAdapter adapter = new VehiclesAdapter(getActivity(), this.list);
        setListAdapter(adapter);
    }



}
