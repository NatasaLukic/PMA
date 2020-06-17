package com.example.findacar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
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
import com.example.findacar.service.HttpService;
import com.example.findacar.service.ServiceUtils;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentReservationAdapter extends BaseAdapter implements CurrentReservationHelper {

    private List<Reservation> mDataset;
    public Activity activity;
    public static final String SERVICE_API_PATH = "http://192.168.0.35:8057/";
    //public static final String SERVICE_API_PATH = "http://192.168.0.15:8057/";

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final Reservation reservation = (Reservation) getItem(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_reservation, null);

        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textViewVehicleName);
        TextView datePickUp = (TextView)vi.findViewById(R.id.textViewReservationDatePickUp);
        TextView datReturn = (TextView)vi.findViewById(R.id.textViewReservationDateReturn);
        TextView price = (TextView)vi.findViewById(R.id.textViewReservationPrice);
        TextView canCancel = (TextView)vi.findViewById(R.id.textView5);
        canCancel.setVisibility(View.VISIBLE);

        ImageView carPhoto = (ImageView) vi.findViewById(R.id.car_photo);

        Picasso.get().load(SERVICE_API_PATH + "search/getImage/" + reservation.getVehicle().getImageFile())
                .resize(300,300).into(carPhoto);

        vehicleServiceName.setText(reservation.getVehicle().getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        datePickUp.setText(dateFormat.format(reservation.getPickUpDate()));
        datReturn.setText(dateFormat.format(reservation.getReturnDate()));

        price.setText((String.valueOf(reservation.getPrice())) + " RSD");

        RatingBar ratingBar = vi.findViewById(R.id.ratingBar3);
        ratingBar.setVisibility(View.GONE);
        Button buttonRate = (Button) vi.findViewById(R.id.button5);
        buttonRate.setVisibility(View.GONE);
        Button buttonCancel = (Button) vi.findViewById(R.id.button2);
        buttonCancel.setEnabled(true);
        buttonCancel.setVisibility(View.VISIBLE);
        final View finalVi = vi;

        try {
            if (passed(reservation.getPickUpDate(), reservation.getVehicle().getCancel())){
                buttonCancel.setVisibility(View.GONE);
                canCancel.setText("The cancellation deadline has passed.");
            }else {
                buttonCancel.setVisibility(View.VISIBLE);
                canCancel.setText("Cancellation deadline: " + getCancelDate(reservation.getPickUpDate(),
                        reservation.getVehicle().getCancel()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button button = vi.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Call<ResponseBody> call = ServiceUtils.findACarService.cancelRes(reservation.getId());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful()){
                            getView(position, finalVi, null).setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        return vi;
    }


    @Override
    public boolean passed(Date pickUpDate, int cancel) throws ParseException {

        String pattern = "yyyy-MM-dd";

        DateFormat df = new SimpleDateFormat(pattern);
        Date d = new Date();
        String date = df.format(d);

        Calendar c = Calendar.getInstance();
        c.setTime(df.parse(date));
        c.add(Calendar.DAY_OF_MONTH, cancel);
        String newDate = df.format(c.getTime());
        Log.e("DATE", newDate);
        Date newDate2  = df.parse(newDate);

        if(newDate2.before(pickUpDate) || newDate2.equals(pickUpDate)){
            return false; //moze da otkaze
        } else {
            return true; // ne moze da otkaze
        }

    }

    @Override
    public String getCancelDate(Date pickUpDate, int cancel) throws ParseException {

        String pattern = "yyyy-MM-dd";

        DateFormat df = new SimpleDateFormat(pattern);
        String date = df.format(pickUpDate);
        Calendar c = Calendar.getInstance();
        c.setTime(df.parse(date));
        c.add(Calendar.DAY_OF_MONTH, -cancel);
        String newDate = df.format(c.getTime());

        return newDate;
    }
}
