package com.example.findacar.adapters;

import android.app.Activity;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.findacar.R;
import com.example.findacar.fragments.ServiceRatingFragment;
import com.example.findacar.mockupData.Reservations;
import com.example.findacar.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.List;

public class PreviousReservationAdapter extends BaseAdapter {
    private List<Reservation> mDataset;
    public Activity activity;

    public PreviousReservationAdapter(Activity activity, List<Reservation> mDataset) {
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
        if(convertView==null){
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_reservation, null);
        }
        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textViewVehicleName);
        TextView date = (TextView)vi.findViewById(R.id.textViewReservationDate);
        TextView price = (TextView)vi.findViewById(R.id.textViewReservationPrice);
        TextView textView5 = (TextView)vi.findViewById(R.id.textView5);
        textView5.setVisibility(View.GONE);

        vehicleServiceName.setText(reservation.getVehicle().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        date.setText(dateFormat.format(reservation.getPickUpDate()) + " - " + dateFormat.format(reservation.getReturnDate()));
        price.setText((String.valueOf(reservation.getPrice())) + " RSD");
        Button buttonRate = (Button) vi.findViewById(R.id.button5);
        Button buttonCancel = (Button) vi.findViewById(R.id.button2);
        buttonCancel.setVisibility(View.GONE);
        RatingBar ratingBar = vi.findViewById(R.id.ratingBar3);
        ratingBar.setEnabled(false);
        if (position == 1){
            ratingBar.setVisibility(View.VISIBLE);
            buttonRate.setEnabled(false);
            ratingBar.setRating(4);
        }else {
            ratingBar.setVisibility(View.GONE);
            buttonRate.setEnabled(true);
            buttonRate.setVisibility(View.VISIBLE);
            buttonRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction ft =((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
                    Fragment prev =((AppCompatActivity)activity).getSupportFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    // Create and show the dialog.
                    DialogFragment newFragment = new ServiceRatingFragment();
                    newFragment.show(ft, "dialog");
                }
            });

        }
        return vi;
    }
}
