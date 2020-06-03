package com.example.findacar.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.activites.CarServiceDetailsActivity;
import com.example.findacar.adapters.CarServicesAdapter;
import com.example.findacar.mockupData.CarServices;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Review;
import com.example.findacar.model.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResultsFragment extends ListFragment {

    private List<CarService> list;

    public ListResultsFragment() {
        // Required empty public constructor
    }

    public ListResultsFragment(List<CarService> l) {
        this.list = l;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_results, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        System.out.println("asdasdsad");

        final CarService carService = this.list.get(position);
        System.out.println(carService.getEmail());
        Intent intent = getActivity().getIntent();

        SearchVehiclesDTO searchVehiclesDTO = new SearchVehiclesDTO();

        searchVehiclesDTO.setId(carService.getId());
        searchVehiclesDTO.setPickUpDate(intent.getStringExtra("pickUp"));
        searchVehiclesDTO.setReturnDate(intent.getStringExtra("return"));

        Call<List<Vehicle>> call = ServiceUtils.reviewerService.searchDates(searchVehiclesDTO);

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {

                if(response.isSuccessful()){
                    System.out.println("usao");

                    response.body();
                    List<Vehicle> vehicles = response.body();
                    final Intent intent1 = new Intent(getActivity(), CarServiceDetailsActivity.class);
                    intent1.putExtra("vehicles", (ArrayList<Vehicle>) vehicles);

                    Call<List<Review>> call1 = ServiceUtils.reviewerService.getCarServiceReviews(carService.getId());
                    call1.enqueue(new Callback<List<Review>>() {
                        @Override
                        public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                            if(response.isSuccessful()){
                                // TODO Zasto u polje rating ubacuje 0.0????????????????????????
                                List<Review> list = response.body();
                                carService.setReviews(list);
                                Gson gson = new Gson();
                                intent1.putExtra("carService", gson.toJson(carService));
                                startActivity(intent1);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Review>> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {

            }
        });




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            CarServicesAdapter adapter = new CarServicesAdapter(getActivity(), this.list);
        setListAdapter(adapter);
    }
}
