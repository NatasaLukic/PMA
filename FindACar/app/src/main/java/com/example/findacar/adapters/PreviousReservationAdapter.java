package com.example.findacar.adapters;

import android.app.Activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

public class PreviousReservationAdapter extends BaseAdapter implements PreviousReservationsHelper {
    private List<Reservation> mDataset;
    public Activity activity;
    private String email;
    //public static final String SERVICE_API_PATH = "http://192.168.0.35:8057/";
    public static final String SERVICE_API_PATH = "http://192.168.0.15:8057/";


    public PreviousReservationAdapter(Activity activity, List<Reservation> mDataset, String email) {
        this.activity = activity;
        this.mDataset = mDataset;
        this.email = email;
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
        final Reservation reservation = this.mDataset.get(position);
        if(convertView==null){
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_reservation, null);
        }
        TextView vehicleServiceName = (TextView)vi.findViewById(R.id.textViewVehicleName);
        TextView datePickUp = (TextView)vi.findViewById(R.id.textViewReservationDatePickUp);
        TextView datReturn = (TextView)vi.findViewById(R.id.textViewReservationDateReturn);
        TextView price = (TextView)vi.findViewById(R.id.textViewReservationPrice);
        TextView comment = (TextView)vi.findViewById(R.id.textView5);


        ImageView carPhoto = (ImageView) vi.findViewById(R.id.car_photo);
        Picasso.get().load(SERVICE_API_PATH + "search/getImage/" + reservation.getVehicle().getImageFile())
                .resize(300,300).into(carPhoto);
        //carPhoto.setImageResource(R.drawable.dacia_logan);

        vehicleServiceName.setText(reservation.getVehicle().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        datePickUp.setText(dateFormat.format(reservation.getPickUpDate()));
        datReturn.setText(dateFormat.format(reservation.getReturnDate()));
        Button buttonRate = (Button) vi.findViewById(R.id.button5);
        Button buttonCancel = (Button) vi.findViewById(R.id.button2);
        buttonCancel.setVisibility(View.GONE);
        RatingBar ratingBar = vi.findViewById(R.id.ratingBar3);

        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.SRC_ATOP);

        if (reservation.getReview() != null){
            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.setRating(reservation.getReview().getRating());
            buttonRate.setVisibility(View.GONE);
            comment.setVisibility(View.VISIBLE);
            comment.setText(reservation.getReview().getComment());
        }else {
            ratingBar.setVisibility(View.GONE);
            comment.setVisibility(View.GONE);
            buttonRate.setVisibility(View.VISIBLE);
            final View finalVi = vi;
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
                    DialogFragment newFragment = new ServiceRatingFragment(reservation, email, PreviousReservationAdapter.this, position, finalVi);
                    newFragment.show(ft, "dialog");
                }
            });

        }
        return vi;
    }

    @Override
    public void closeButton(int position, View view, String comment, String rating) {

        View v = getView(position, view, null);
        System.out.println(position);
        Button b = (Button) v.findViewById(R.id.button5);
        b.setVisibility(View.GONE);

        System.out.println(comment);

        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar3);
        ratingBar.setVisibility(View.VISIBLE);
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setRating(Float.parseFloat(rating));

        TextView commentText = (TextView) v.findViewById(R.id.textView5);
        commentText.setText(comment);
        commentText.setVisibility(View.VISIBLE);
    }


}
