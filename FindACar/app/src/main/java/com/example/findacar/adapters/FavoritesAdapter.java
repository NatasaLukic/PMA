package com.example.findacar.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.findacar.R;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.fragments.FavoriteReviewsFragment;
import com.example.findacar.fragments.FavoritesReviewListFragment;
import com.example.findacar.fragments.ServiceRatingFragment;
import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.model.VehicleWithReviews;
import com.example.findacar.service.ServiceUtils;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesAdapter extends BaseAdapter {

    private ListFragment fragment;
    private Activity activity;
    private List<VehicleWithReviews> vehicles;

    public FavoritesAdapter(){

    }

    public FavoritesAdapter(ListFragment fragment, List<VehicleWithReviews> vehicles, Activity activity) {
        this.fragment = fragment;
        this.vehicles = vehicles;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return this.vehicles.size();
    }

    @Override
    public Object getItem(int position) {
        return this.vehicles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        final Vehicle vehicle = this.vehicles.get(position).vehicle;
        final List<Review> reviews = this.vehicles.get(position).reviews;

        if (convertView == null)
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_one_favorite_vehicle, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView doors = (TextView)vi.findViewById(R.id.doors);
        TextView seats = (TextView)vi.findViewById(R.id.seats);
        ImageView image = (ImageView)vi.findViewById(R.id.vehiclePhoto);
        TextView type = (TextView) vi.findViewById(R.id.type);

        name.setText(vehicle.getName());
        seats.setText(Integer.toString(vehicle.getSeats()));
        doors.setText(Integer.toString(vehicle.getDoors()));
        type.setText(vehicle.getType());

        Picasso.get().load(ServiceUtils.SERVICE_API_PATH + "search/getImage/" +vehicle.getImageFile())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(300,300).into(image);

        TextView autom = (TextView) vi.findViewById(R.id.aut);

        if (vehicle.isAutom() == true) {
            autom.setText("Automatic");
        } else {
            autom.setText("Manual");

        }

        Button button = (Button) vi.findViewById(R.id.buttonReviews);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft =((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
                Fragment prev =((AppCompatActivity)activity).getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                DialogFragment newFragment = new FavoriteReviewsFragment(reviews);
                newFragment.show(ft, "dialog");


            }
        });

        return vi;
    }
}
