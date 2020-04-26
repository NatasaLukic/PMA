package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.adapters.CarServicesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResults extends ListFragment {

    public ListResults() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_results, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityFragmentCreated()", Toast.LENGTH_SHORT).show();

        CarServicesAdapter adapter = new CarServicesAdapter(getActivity());
        setListAdapter(adapter);
    }
}
