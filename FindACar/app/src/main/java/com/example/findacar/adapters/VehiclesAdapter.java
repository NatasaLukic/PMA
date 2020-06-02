package com.example.findacar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.model.Vehicle;

import java.util.List;

public class VehiclesAdapter extends BaseAdapter {

    public Activity activity;
    public List<Vehicle> vehicles;

    public VehiclesAdapter(Activity activity, List<Vehicle> vehicles){
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        final Vehicle vehicle = this.vehicles.get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.vehicle_item, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView doors = (TextView)vi.findViewById(R.id.doors);
        TextView seats = (TextView)vi.findViewById(R.id.seats);
        ImageView image = (ImageView)vi.findViewById(R.id.vehiclePhoto);
        TextView price = (TextView) vi.findViewById(R.id.price);
        TextView type = (TextView) vi.findViewById(R.id.type);

        name.setText(vehicle.getName());
        seats.setText(Integer.toString(vehicle.getSeats()));
        doors.setText(Integer.toString(vehicle.getDoors()));
        type.setText(vehicle.getType());
        price.setText("Price:");

        TextView date = (TextView) vi.findViewById(R.id.reg);
        TextView autom = (TextView) vi.findViewById(R.id.aut);

        date.setText("Vehicle registrated until " + vehicle.getRegUntil());

        Button details = (Button) vi.findViewById(R.id.button);

        if (vehicle.isAutom() == true) {
            autom.setText("Automatic");
        } else {
            autom.setText("Manual");
        }

        details.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, VehicleActivity.class);
                intent.putExtra("name", vehicle.getName());
                intent.putExtra("position", position);

                activity.startActivity(intent);
            }
        });


        image.setImageResource(R.drawable.dacia_logan);

        return vi;
    }

}
