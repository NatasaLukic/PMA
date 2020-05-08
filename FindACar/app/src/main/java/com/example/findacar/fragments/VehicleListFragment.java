package com.example.findacar.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.activites.CarServiceDetailsActivity;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.adapters.CarServicesAdapter;
import com.example.findacar.adapters.VehiclesAdapter;
import com.example.findacar.mockupData.CarServices;
import com.example.findacar.mockupData.Vehicles;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Vehicle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleListFragment extends ListFragment {

    public VehicleListFragment() {
        // Required empty public constructor
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
        Toast.makeText(getActivity(), "onActivityFragmentCreated()", Toast.LENGTH_SHORT).show();

        VehiclesAdapter adapter = new VehiclesAdapter(getActivity());
        setListAdapter(adapter);
    }



}
