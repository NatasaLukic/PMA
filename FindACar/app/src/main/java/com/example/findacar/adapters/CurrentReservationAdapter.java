package com.example.findacar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.findacar.R;
import com.example.findacar.mockupData.Reservations;
import com.example.findacar.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrentReservationAdapter extends BaseAdapter {

    public Activity activity;

    public CurrentReservationAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Reservations.getCurrentReservations().size();
    }

    @Override
    public Object getItem(int position) {
        return Reservations.getCurrentReservations().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Reservation reservation = (Reservation) getItem(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_current_reservation, null);

        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textViewVehicleName);
        TextView date = (TextView)vi.findViewById(R.id.textViewReservationDate);
        TextView price = (TextView)vi.findViewById(R.id.textViewReservationPrice);

        vehicleServiceName.setText(reservation.getVehicle().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        date.setText(dateFormat.format(reservation.getPickUpDate()) + " - " + dateFormat.format(reservation.getReturnDate()));
        price.setText((String.valueOf(reservation.getPrice())));

        Button buttonRate = (Button) vi.findViewById(R.id.button5);
        Button buttonCancel = (Button) vi.findViewById(R.id.button2);
        buttonRate.setVisibility(View.GONE);
        buttonCancel.setVisibility(View.VISIBLE);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "on cancel button click", Toast.LENGTH_SHORT).show();
            }
        });

        return vi;
    }
}
