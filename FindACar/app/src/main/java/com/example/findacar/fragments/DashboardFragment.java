package com.example.findacar.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.DashboardActivity;
import com.example.findacar.activites.LoginActivity;
import com.example.findacar.activites.SearchResultsActivity;
import com.example.findacar.adapters.SpinnerForSearchAdapter;
import com.example.findacar.model.CarService;
import com.example.findacar.model.SearchDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.ServiceUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private EditText pickUpDate;
    private EditText returnDate;

    private String place;
    private String datepickUp;
    private String dateReturn;

    private String timePickUp;
    private String timeReturn;

    private String [] items = {"","Beograd", "Nis", "Novi Sad"};

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

        final Spinner spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerForSearchAdapter(getContext(),
                R.layout.spinner_item, items);

        spinner.setAdapter(spinnerAdapter);

        pickUpDate = (EditText) view.findViewById(R.id.datePickUp);
        returnDate = (EditText) view.findViewById(R.id.dateReturn);

        pickUpTime = (EditText) view.findViewById(R.id.timePickUp);
        returnTime = (EditText) view.findViewById(R.id.timeReturn);

        ImageView imagePickUp = (ImageView) view.findViewById(R.id.imageCal2);
        ImageView imageReturn = (ImageView) view.findViewById(R.id.imageCal);


        Button next = view.findViewById(R.id.goNext);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                place = spinner.getSelectedItem().toString();

                SearchDTO searchDTO = new SearchDTO(place, datepickUp, dateReturn);

                Call<List<CarService>> call = ServiceUtils.reviewerService.searchCity(searchDTO);

                call.enqueue(new Callback<List<CarService>>() {
                    @Override
                    public void onResponse(Call<List<CarService>> call, Response<List<CarService>> response) {
                        System.out.println("ovo je odg " + response.body());
                        if(response.isSuccessful()){
                            response.body();

                            List<CarService> list = response.body();
                            Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                            Gson gson = new Gson();
                            intent.putExtra("services", gson.toJson(list));
                            intent.putExtra("place", place);
                            startActivity(intent);

                        }


                    }

                    @Override
                    public void onFailure(Call<List<CarService>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

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

                datepickUp = month + "/" + day + "/" + year;
                pickUpDate.setText(datepickUp);
                pickUpDate.setTextColor(getResources().getColor(R.color.colorDarkGray));

                TimePickerDialog dialogTime = new TimePickerDialog(getActivity(), R.style.DatePickerDialog, mTimeSetListenerPickUp, hour, minute, true);
                dialogTime.show();
            }
        };

        mTimeSetListenerPickUp = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                timePickUp = hourOfDay + ":" + minute + ":00";
                pickUpTime.setText(timePickUp);
                pickUpTime.setTextColor(getResources().getColor(R.color.colorDarkGray));

            }
        };

        mTimeSetListenerReturn = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                timeReturn = hourOfDay + ":" + minute + ":00";
                returnTime.setText(timeReturn);
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

                dateReturn = month + "/" + day + "/" + year;
                returnDate.setText(dateReturn);
                returnDate.setTextColor(getResources().getColor(R.color.colorDarkGray));


                TimePickerDialog dialogTime = new TimePickerDialog(getActivity(), R.style.DatePickerDialog, mTimeSetListenerReturn, hour, minute, true);
                dialogTime.show();
            }
        };

        return view;
    }

    public Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }
}
