package com.example.findacar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.findacar.R;
import com.example.findacar.adapters.CurrentReservationAdapter;
import com.example.findacar.mockupData.Reservations;
import com.example.findacar.model.Reservation;

import java.util.List;

public class CurrentReservationsFragment extends ListFragment {

    private List<Reservation> res;
    private String email;
    public CurrentReservationsFragment() {
    }

    public CurrentReservationsFragment(List<Reservation> res, String email) {
        this.res = res;
        this.email = email;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviousReservationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentReservationsFragment newInstance(String param1, String param2) {
        CurrentReservationsFragment fragment = new CurrentReservationsFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CurrentReservationAdapter adapter = new CurrentReservationAdapter(getActivity(), res);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
