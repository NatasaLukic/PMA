package com.example.findacar.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.DashboardActivity;
import com.example.findacar.activites.LoginActivity;
import com.example.findacar.activites.SearchResultsActivity;
import com.example.findacar.activites.SignUpActivity;

import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private TextView date;
    private EditText pickUpDate;
    private EditText returnDate;
    private DatePickerDialog.OnDateSetListener mDateSetListenerPickUp;
    private DatePickerDialog.OnDateSetListener mDateSetListenerReturn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);

        pickUpDate = (EditText) view.findViewById(R.id.datePickUp);
        returnDate = (EditText) view.findViewById(R.id.dateReturn);

        ImageView imagePickUp = (ImageView) view.findViewById(R.id.imageCal2);
        ImageView imageReturn = (ImageView) view.findViewById(R.id.imageCal);

        Button next = view.findViewById(R.id.goNext);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                startActivity(intent);
            }
        });

        imagePickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        mDateSetListenerPickUp, year, month,day);
                dialog.show();
            }
        });

        mDateSetListenerPickUp = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                pickUpDate.setText(date);
            }
        };

        imageReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        mDateSetListenerReturn, year, month,day);
                dialog.show();
            }
        });

        mDateSetListenerReturn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date2 = month + "/" + day + "/" + year;
                returnDate.setText(date2);
            }
        };

        return view;
    }
}
