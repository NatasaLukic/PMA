package com.example.findacar.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        return Reservations.getReservations().size();
    }

    @Override
    public Object getItem(int position) {
        return Reservations.getReservations().get(position);
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

        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textView10);
        TextView date = (TextView)vi.findViewById(R.id.textView11);
        TextView price = (TextView)vi.findViewById(R.id.textView13);

        vehicleServiceName.setText(reservation.getVehicle().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        date.setText(dateFormat.format(reservation.getPickUpDate()) + " - " + dateFormat.format(reservation.getReturnDate()));
        price.setText((String.valueOf(reservation.getPrice())));
        return vi;
    }
}
