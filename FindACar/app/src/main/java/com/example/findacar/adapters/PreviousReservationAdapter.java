package com.example.findacar.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.mockupData.Reservations;
import com.example.findacar.model.Reservation;

public class PreviousReservationAdapter extends BaseAdapter {

    public Activity activity;

    public PreviousReservationAdapter(Activity activity) {
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
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_previous_reservation, null);

        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textView4);
        TextView date = (TextView)vi.findViewById(R.id.textView5);
        TextView price = (TextView)vi.findViewById(R.id.textView8);

        vehicleServiceName.setText(reservation.getVehicle().getName());
        date.setText(reservation.getPickUpDate() + " - " + reservation.getReturnDate());
        price.setText((String.valueOf(reservation.getPrice())));
        return vi;
    }
}
