package com.example.findacar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.findacar.R;
import com.example.findacar.adapters.ReviewsAdapter;
import com.example.findacar.model.Review;

import java.util.List;

public class FavoriteReviewsFragment extends DialogFragment {

    public List<Review> reviews;

    public FavoriteReviewsFragment(){

    }

    public FavoriteReviewsFragment(List<Review> reviews){
        this.reviews = reviews;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_reviews, container, false);

        Button button = (Button) view.findViewById(R.id.closeButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ListView listView = (ListView) getView().findViewById(R.id.listReviews);

        listView.setAdapter(new ReviewsAdapter(getActivity(), reviews));

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
