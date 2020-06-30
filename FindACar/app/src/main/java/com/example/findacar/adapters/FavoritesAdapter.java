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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.findacar.R;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.fragments.FavoritesReviewListFragment;
import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.model.VehicleWithReviews;

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

        Vehicle vehicle = this.vehicles.get(position).vehicle;
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

        TextView autom = (TextView) vi.findViewById(R.id.aut);

        if (vehicle.isAutom() == true) {
            autom.setText("Automatic");
        } else {
            autom.setText("Manual");

        }
        ExpandableListView expandableListView_data;

        ExpandableListView expandableListView = vi.findViewById(R.id.expand_list_review);

      //  AppCompatActivity activity = (AppCompatActivity) finalVi.getContext();
     //   Fragment myFragment = new FavoritesReviewListFragment(reviews, fragment);
      //  activity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_reviews, myFragment).commit();

     //   fragment.getChildFragmentManager().beginTransaction().add(R.id.layout_reviews, myFragment).commit();


/*
        Button button  = (Button) vi.findViewById(R.id.buttonReviews);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
*/
              /*
            }
        });*/

/*
        LinearLayout layout = vi.findViewById(R.id.listRev2);

        ListAdapter adapter = new FavoriteReviewAdapter(activity, reviews);

        int adapterCount = adapter.getCount();
        System.out.println("ima reviewa " + adapterCount);
        // TODO FIX THIS
        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }

*/
        return vi;
    }
}
