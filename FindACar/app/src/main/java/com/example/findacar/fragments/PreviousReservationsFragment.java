package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findacar.R;

import com.example.findacar.adapters.PreviousReservationAdapter;
import com.example.findacar.mockupData.Reservations;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.Review;
import com.example.findacar.service.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousReservationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousReservationsFragment extends ListFragment {

    private List<Reservation> reservations = new ArrayList<>();
    private String email;

    public PreviousReservationsFragment() {
        // Required empty public constructor

    }

    public PreviousReservationsFragment(List<Reservation> res, String email) {
        // Required empty public constructor
        this.reservations = res;
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
    public static PreviousReservationsFragment newInstance(String param1, String param2) {
        PreviousReservationsFragment fragment = new PreviousReservationsFragment();
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
    public void onListItemClick(ListView l, View v, int position, long id) {
               super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PreviousReservationAdapter adapter = new PreviousReservationAdapter(getActivity(), reservations, email);
        setListAdapter(adapter);
    }

}
