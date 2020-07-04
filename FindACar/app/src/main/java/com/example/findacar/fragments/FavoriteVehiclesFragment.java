package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findacar.R;
import com.example.findacar.adapters.FavoritesAdapter;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.model.Review;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.VehicleWithReviews;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteVehiclesFragment extends ListFragment {

    private List<VehicleWithReviews> list;
    private UserDatabase userDatabase;

    public FavoriteVehiclesFragment() {
    }

    public FavoriteVehiclesFragment(String email) {
        userDatabase = UserDatabase.getInstance(getActivity());
        long userId = userDatabase.userDao().loadSingleByEmail(email);

        UserWithVehiclesAndReviews userWithVehiclesAndReviews = userDatabase.userDao().getUserWithVehiclesAndReviews(userId);

        if(userWithVehiclesAndReviews != null && userWithVehiclesAndReviews.vehiclesWithReviews.size()!=0){
            list = userWithVehiclesAndReviews.vehiclesWithReviews;
            for(VehicleWithReviews vehicleWithReviews : this.list){

                System.out.println("Vozilo " + vehicleWithReviews.vehicle.getName());
                for(Review review : vehicleWithReviews.reviews){
                    System.out.println("--Komentar " + review.getComment());
                }
                System.out.println("**************************************");
            }

        }else {
            list = new ArrayList<VehicleWithReviews>();
        }



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_favorite_vehicles, container, false);
        return vi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FavoritesAdapter adapter = new FavoritesAdapter(this, this.list, getActivity());
        setListAdapter(adapter);
    }

}
