package com.example.findacar.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_rating, container, false);

/*        Button button = (Button)view.findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });*/
        return  view;
    }

}
