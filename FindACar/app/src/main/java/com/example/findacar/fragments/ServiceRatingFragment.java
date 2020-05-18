package com.example.findacar.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.findacar.R;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link ServiceRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceRatingFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static ListView listView;
    private static View view;
    private static int pos;

    public ServiceRatingFragment() {
        // Required empty public constructor
    }

    public static ServiceRatingFragment newInstance(ListView l, View v, int position) {
        listView = l;
        view = v;
        pos = position;
        ServiceRatingFragment fragment = new ServiceRatingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_rating, container, false);

        RatingBar rating = (RatingBar) view.findViewById(R.id.ratingBar);

        LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();

        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.gold), PorterDuff.Mode.SRC_ATOP);

      //  drawable.setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.SRC_ATOP);

        return  view;
    }
    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
