package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.adapters.CarServicesAdapter;
import com.example.findacar.adapters.VehiclesAdapter;
import com.example.findacar.mockupData.Vehicles;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleList extends ListFragment {

    public VehicleList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
