package com.example.findacar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.mockupData.CarServices;
import com.example.findacar.mockupData.Vehicles;
import com.example.findacar.model.CarService;
import com.example.findacar.model.Vehicle;

import org.w3c.dom.Text;

public class VehiclesAdapter extends BaseAdapter {

    public Activity activity;

    public VehiclesAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Vehicles.getVehicles().size();
    }

    @Override
    public Object getItem(int position) {
        return Vehicles.getVehicles().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        final Vehicle vehicle = Vehicles.getVehicles().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.vehicle_item, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView doors = (TextView)vi.findViewById(R.id.doors);
        TextView seats = (TextView)vi.findViewById(R.id.seats);
        ImageView image = (ImageView)vi.findViewById(R.id.vehiclePhoto);
        TextView price = (TextView) vi.findViewById(R.id.price);
        TextView type = (TextView) vi.findViewById(R.id.type);

        name.setText(vehicle.getName());
        seats.setText(Integer.toString(vehicle.getNumOfSeats()));
        doors.setText(Integer.toString(vehicle.getNumOfDoors()));
        type.setText(vehicle.getType());
        price.setText("Price: 19.200 RSD");

        TextView date = (TextView) vi.findViewById(R.id.date);
        date.setText(vehicle.getRegistratedUntil());

        Button details = (Button) vi.findViewById(R.id.button);

        details.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, VehicleActivity.class);
                intent.putExtra("name", vehicle.getName());
                intent.putExtra("position", position);

                activity.startActivity(intent);
            }
        });


        image.setImageResource(vehicle.getImage());

        return vi;
    }

}
