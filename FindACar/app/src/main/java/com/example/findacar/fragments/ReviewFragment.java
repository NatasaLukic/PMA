package com.example.findacar.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.adapters.ReviewsAdapter;
import com.example.findacar.adapters.VehiclesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends ListFragment {

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_reviews_list, container, false);

        RatingBar ratingBar = vi.findViewById(R.id.ratingBar4);


        return vi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ReviewsAdapter adapter = new ReviewsAdapter(getActivity());
        setListAdapter(adapter);
    }
}
