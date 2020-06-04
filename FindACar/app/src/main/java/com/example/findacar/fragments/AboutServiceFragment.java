package com.example.findacar.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.adapters.ReviewsAdapter;
import com.example.findacar.adapters.TabAdapter;
import com.example.findacar.mockupData.Reviews;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Review;
import com.example.findacar.service.ServiceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutServiceFragment extends Fragment {

    private CarService carService;
    private TextView about;
    private TextView street;
    private TextView city;
    private TextView country;
    private TextView email;
    private TextView mobilePhone;
    private TextView landlinePhone;


    public AboutServiceFragment() {
        // Required empty public constructor
    }
    public AboutServiceFragment(CarService carService) {
        this.carService = carService;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_service, container, false);
        about = (TextView)view.findViewById(R.id.textView22);
        about.setMovementMethod(new ScrollingMovementMethod());

        street = (TextView)view.findViewById(R.id.textView23);
        city = (TextView)view.findViewById(R.id.textView24);
        country = (TextView)view.findViewById(R.id.textView27);
        mobilePhone = (TextView)view.findViewById(R.id.textView14);
        landlinePhone = (TextView)view.findViewById(R.id.textView13);
        email = (TextView)view.findViewById(R.id.textView16);

        populateView();
     //   getChildFragmentManager().beginTransaction().add(R.id.listReviews, new ReviewFragment()).commit();
        final LinearLayout layout = view.findViewById(R.id.listRev);
        ListAdapter adapter = new ReviewsAdapter(getActivity(), carService.getReviews());

        final int adapterCount = adapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }

        return view;
    }

    private void populateView(){
        about.setText(carService.getAbout());
        street.setText(carService.getAddress().getStreet());
        city.setText(carService.getAddress().getCity() + ", " + carService.getAddress().getPostalCode());
        country.setText(carService.getAddress().getCountry());
        mobilePhone.setText(carService.getPhone());
        landlinePhone.setText(carService.getLandlinePhone());
        email.setText(carService.getEmail());

    }

}
