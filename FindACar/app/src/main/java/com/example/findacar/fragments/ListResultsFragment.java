package com.example.findacar.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.activites.CarServiceDetailsActivity;
import com.example.findacar.adapters.CarServicesAdapter;
import com.example.findacar.mockupData.CarServices;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResultsFragment extends ListFragment {

    private List<CarService> list;

    public ListResultsFragment() {
        // Required empty public constructor
    }

    public ListResultsFragment(List<CarService> l) {
        this.list = l;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_results, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        CarService s = this.list.get(position);

        Intent intent = new Intent(getActivity(), CarServiceDetailsActivity.class);
        intent.putExtra("vehicles", (ArrayList<Vehicle>) s.getVehicles());
        startActivity(intent);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            CarServicesAdapter adapter = new CarServicesAdapter(getActivity(), this.list);
        setListAdapter(adapter);
    }
}
