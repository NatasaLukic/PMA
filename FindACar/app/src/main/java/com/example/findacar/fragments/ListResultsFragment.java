package com.example.findacar.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
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
import com.example.findacar.model.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        System.out.println("asdasdsad");

        CarService s = this.list.get(position);
        Intent intent = getActivity().getIntent();

        SearchVehiclesDTO searchVehiclesDTO = new SearchVehiclesDTO();

        searchVehiclesDTO.setId(s.getId());
        searchVehiclesDTO.setPickUpDate(intent.getStringExtra("pickUp"));
        searchVehiclesDTO.setReturnDate(intent.getStringExtra("return"));

        Intent intent1 = new Intent(getActivity(), CarServiceDetailsActivity.class);
        intent1.putExtra("searchForVehicles", (Serializable) searchVehiclesDTO);
        startActivity(intent1);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            CarServicesAdapter adapter = new CarServicesAdapter(getActivity(), this.list);
        setListAdapter(adapter);
    }

}
