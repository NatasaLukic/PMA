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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


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
      //  TextView additionalInfo = (TextView)view.findViewById(R.id.textView26);
      //  TextView address = (TextView)view.findViewById(R.id.textView23);
        about.setMovementMethod(new ScrollingMovementMethod());
        //additionalInfo.setMovementMethod(new ScrollingMovementMethod());
       // address.setMovementMethod(new ScrollingMovementMethod());


     //   getChildFragmentManager().beginTransaction().add(R.id.listReviews, new ReviewFragment()).commit();

        LinearLayout layout = view.findViewById(R.id.listRev);
        ListAdapter adapter = new ReviewsAdapter(getActivity());

        final int adapterCount = adapter.getCount();

        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }

        return view;
    }

}
