package com.example.findacar.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.SearchResultsActivity;

import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private EditText pickUpDate;
    private EditText returnDate;

    private EditText pickUpTime;
    private EditText returnTime;

    private DatePickerDialog.OnDateSetListener mDateSetListenerPickUp;
    private DatePickerDialog.OnDateSetListener mDateSetListenerReturn;

    private TimePickerDialog.OnTimeSetListener mTimeSetListenerPickUp;
    private TimePickerDialog.OnTimeSetListener mTimeSetListenerReturn;

    private  Calendar cal = Calendar.getInstance();
    private int year = cal.get(Calendar.YEAR);
    private int month = cal.get(Calendar.MONTH);
    private int day = cal.get(Calendar.DAY_OF_MONTH);

    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        pickUpDate = (EditText) view.findViewById(R.id.datePickUp);
        returnDate = (EditText) view.findViewById(R.id.dateReturn);

        pickUpTime = (EditText) view.findViewById(R.id.timePickUp);
        returnTime = (EditText) view.findViewById(R.id.timeReturn);

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


                DatePickerDialog dialog = new DatePickerDialog(getActivity(),R.style.DatePickerDialog,
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
                pickUpDate.setTextColor(getResources().getColor(R.color.colorDarkGray));

                TimePickerDialog dialogTime = new TimePickerDialog(getActivity(), R.style.DatePickerDialog, mTimeSetListenerPickUp, hour, minute, true);
                dialogTime.show();
            }
        };

        mTimeSetListenerPickUp = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String time = hour + ":" + minute;
                pickUpTime.setText(time);
                pickUpTime.setTextColor(getResources().getColor(R.color.colorDarkGray));

            }
        };

        mTimeSetListenerReturn = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String time = hourOfDay + ":" + minute;
                returnTime.setText(time);
                returnTime.setTextColor(getResources().getColor(R.color.colorDarkGray));
            }
        };


        imageReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.DatePickerDialog,
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
                returnDate.setTextColor(getResources().getColor(R.color.colorDarkGray));


                TimePickerDialog dialogTime = new TimePickerDialog(getActivity(), R.style.DatePickerDialog, mTimeSetListenerReturn, hour, minute, true);
                dialogTime.show();
            }
        };

        return view;
    }
}
