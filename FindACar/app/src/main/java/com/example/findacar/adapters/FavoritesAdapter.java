package com.example.findacar.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.model.Review;
import com.example.findacar.model.Vehicle;
import com.example.findacar.model.VehicleWithReviews;

import java.util.List;

public class FavoritesAdapter extends BaseAdapter {

    private Activity activity;
    private List<VehicleWithReviews> vehicles;

    public FavoritesAdapter(){

    }

    public FavoritesAdapter(Activity activity, List<VehicleWithReviews> vehicles) {
        this.activity = activity;
        this.vehicles = vehicles;

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
        List<Review> reviews = this.vehicles.get(position).reviews;

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

        LinearLayout layout = vi.findViewById(R.id.listRev2);

        ListAdapter adapter = new FavoriteReviewAdapter(activity, reviews);

        int adapterCount = adapter.getCount();
        System.out.println("ima reviewa " + adapterCount);

        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }

        return vi;
    }
}
