package com.example.findacar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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

    private List<Reservation> mDataset;
    public Activity activity;

    public CurrentReservationAdapter(Activity activity, List<Reservation> mDataset) {
        this.activity = activity;
        this.mDataset = mDataset;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataset.get(position);
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
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_reservation, null);

        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textViewVehicleName);
        TextView datePickUp = (TextView)vi.findViewById(R.id.textViewReservationDatePickUp);
        TextView datReturn = (TextView)vi.findViewById(R.id.textViewReservationDateReturn);
        TextView price = (TextView)vi.findViewById(R.id.textViewReservationPrice);
        TextView canCancel = (TextView)vi.findViewById(R.id.textView5);
        canCancel.setVisibility(View.VISIBLE);

        ImageView carPhoto = (ImageView) vi.findViewById(R.id.car_photo);
        carPhoto.setImageResource(R.drawable.dacia_logan);

        vehicleServiceName.setText(reservation.getVehicle().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        datePickUp.setText(dateFormat.format(reservation.getPickUpDate()));
        datReturn.setText(dateFormat.format(reservation.getReturnDate()));
        price.setText((String.valueOf(reservation.getPrice())) + " RSD");
        RatingBar ratingBar = vi.findViewById(R.id.ratingBar3);
        ratingBar.setVisibility(View.GONE);
        Button buttonRate = (Button) vi.findViewById(R.id.button5);
        buttonRate.setVisibility(View.GONE);
        Button buttonCancel = (Button) vi.findViewById(R.id.button2);
        buttonCancel.setVisibility(View.VISIBLE);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if (position == 1){
            buttonCancel.setEnabled(false);
            canCancel.setText("The cancellation deadline has passed.");
        }else {
            buttonCancel.setEnabled(true);
            canCancel.setText("Cancellation deadline: dd/mm/yyyy hh:mm");
        }

        return vi;
    }
}
