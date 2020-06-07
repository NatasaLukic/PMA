package com.example.findacar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.activites.ICarServiceDetails;
import com.example.findacar.adapters.FilterListAdapter;
import com.example.findacar.model.FilterVehicles;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    FilterListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private ICarServiceDetails carServiceDetailsActivity;
    private Button applyButton;
    private Button resetButton;
    private FilterVehicles filterVehicles;

    public FilterFragment() {
        // Required empty public constructor
        filterVehicles = new FilterVehicles();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        prepareData();
        applyButton = view.findViewById(R.id.saveButton);
        resetButton = view.findViewById(R.id.cancelButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                listAdapter = new FilterListAdapter(getContext(), listDataHeader, listDataChild);
                expandableListView.setAdapter(listAdapter);
            }
        });
        expandableListView = (ExpandableListView) view.findViewById(R.id.expand_list);

        listAdapter = new FilterListAdapter(getContext(), listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(), "Group expanded\t" + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(), "Group collapsed\t" + groupPosition, Toast.LENGTH_SHORT).show();

            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                FilterListAdapter adapter = (FilterListAdapter) expandableListView.getExpandableListAdapter();
                HashMap<Integer, boolean[]> checkboxState = adapter.getmChildCheckStates();
                List<String> header = adapter.getListDataHeader();
                HashMap<String, List<String>> listData = adapter.getListDataChild();
                for (Map.Entry<Integer, boolean[]> entry : checkboxState.entrySet()) {

                    String headerColumn = header.get(entry.getKey());
                    List<String> children = listData.get(headerColumn);
                    boolean[] values = entry.getValue();

                    for (int i = 0; i < values.length; ++i) {

                        if (headerColumn.equals(getString(R.string.motor))) {

                            if (values[i]) {
                                filterVehicles.getMotor().add(children.get(i));
                            }

                        } else if (headerColumn.equals(getString(R.string.carType))) {

                            if (values[i]) {
                                filterVehicles.getVahicleType().add(children.get(i));
                            }

                        } else if (headerColumn.equals(getString(R.string.numOfBags))) {
                            if (values[i]) {
                                filterVehicles.getNumOfBags().add(children.get(i));
                            }

                        } else if (headerColumn.equals(getString(R.string.transmission))) {
                            if (values[i]) {
                                filterVehicles.getTransmission().add(children.get(i));
                            }

                        } else if (headerColumn.equals(getString(R.string.airCond))) {

                            filterVehicles.setAirCond(values[i]);

                        }
                    }

                }

                carServiceDetailsActivity.filterList(filterVehicles);

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                listAdapter = new FilterListAdapter(getContext(), listDataHeader, listDataChild);
                expandableListView.setAdapter(listAdapter);
            }
        });

        //   BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_toolbar);
        // bottomNavigationView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        carServiceDetailsActivity = (ICarServiceDetails) context;
    }


    private void prepareData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add(getString(R.string.carType));
        listDataHeader.add(getString(R.string.transmission));
        listDataHeader.add(getString(R.string.motor));
        listDataHeader.add(getString(R.string.numOfBags));
        listDataHeader.add(getString(R.string.airCond));

        List<String> types = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.carType)));
        List<String> transmission = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.transmission)));
        List<String> motors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.motor)));
        List<String> numOfBags = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.numOfBags)));
        List<String> airCond = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.airCond)));

        listDataChild.put(listDataHeader.get(0), types); // Header, Child data
        listDataChild.put(listDataHeader.get(1), transmission);
        listDataChild.put(listDataHeader.get(2), motors);
        listDataChild.put(listDataHeader.get(3), numOfBags);
        listDataChild.put(listDataHeader.get(4), airCond);

    }


}
