package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.adapters.ReviewsAdapter;
import com.example.findacar.mockupData.Reviews;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutServiceFragment extends Fragment {

    public AboutServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_service, container, false);
        TextView about = (TextView)view.findViewById(R.id.textView22);
        TextView additionalInfo = (TextView)view.findViewById(R.id.textView26);
        TextView address = (TextView)view.findViewById(R.id.textView23);
        about.setMovementMethod(new ScrollingMovementMethod());
        additionalInfo.setMovementMethod(new ScrollingMovementMethod());
        address.setMovementMethod(new ScrollingMovementMethod());

        Button btnReviews = (Button)view.findViewById(R.id.btnReviews) ;
        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft =((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                Fragment prev =((AppCompatActivity)getActivity()).getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                // Create and show the dialog.
                DialogFragment newFragment = new ServiceReviewsFragment();
                newFragment.show(ft, "dialog");
            }
        });

        return view;
    }

}
