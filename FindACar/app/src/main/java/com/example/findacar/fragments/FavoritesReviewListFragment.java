package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findacar.R;
import com.example.findacar.adapters.FavoriteReviewAdapter;
import com.example.findacar.adapters.FavoritesAdapter;
import com.example.findacar.model.Review;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesReviewListFragment extends ListFragment {

    private List<Review> reviews;
    private ListFragment fragment;

    public FavoritesReviewListFragment() {
        // Required empty public constructor
    }

    public FavoritesReviewListFragment(List<Review> reviews, ListFragment fragment) {
        this.reviews = reviews;
        this.fragment = fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_review_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FavoriteReviewAdapter adapter = new FavoriteReviewAdapter(getActivity(), this.reviews);
        setListAdapter(adapter);
    }

}
