package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findacar.R;

import com.example.findacar.adapters.CurrentReservationAdapter;
import com.example.findacar.adapters.PreviousReservationAdapter;
import com.example.findacar.mockupData.Reservations;
import com.example.findacar.model.Reservation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousReservationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousReservationsFragment extends ListFragment {


    public PreviousReservationsFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_previous_reservations, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Reservation reservation = Reservations.getReservations().get(position);
        showRateServiceDialog(l, v, position);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityPreviousReservationsFragmentCreated()", Toast.LENGTH_SHORT).show();
        PreviousReservationAdapter adapter = new PreviousReservationAdapter(getActivity());
        //CurrentReservationAdapter adapter = new CurrentReservationAdapter(getActivity());
        setListAdapter(adapter);
    }

    private void showRateServiceDialog(ListView l, View v, int position) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        // Create and show the dialog.
        DialogFragment newFragment = ServiceRatingFragment.newInstance(l, v, position);
        newFragment.show(ft, "dialog");
    }

}
