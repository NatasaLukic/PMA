package com.example.findacar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.activites.DashboardActivity;
import com.example.findacar.activites.VehicleActivity;
import com.example.findacar.model.AdditionalService;
import com.example.findacar.model.CarService;
import com.example.findacar.modelDTO.SearchVehiclesDTO;
import com.example.findacar.model.Vehicle;
import com.example.findacar.service.NetworkUtils;
import com.example.findacar.service.ServiceUtils;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class VehiclesAdapter extends BaseAdapter {

    public Activity activity;
    public List<Vehicle> vehicles;
    public List<AdditionalService> additionalServices;
    String pickupDateTime;
    String returnDateTime;

    public VehiclesAdapter(Activity activity, List<Vehicle> vehicles){
        this.activity = activity;
        this.vehicles = vehicles;
        Intent intent = activity.getIntent();
        SearchVehiclesDTO searchVehiclesDTO = (SearchVehiclesDTO) intent.getSerializableExtra("searchForVehicles");
        pickupDateTime = searchVehiclesDTO.getPickUpDate();
        returnDateTime = searchVehiclesDTO.getReturnDate();
        CarService s = (CarService) intent.getSerializableExtra("carService");
        additionalServices = s.getAdditionalServices();

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
        TextView price = (TextView) vi.findViewById(R.id.priceText);
        TextView type = (TextView) vi.findViewById(R.id.type);

        name.setText(vehicle.getName());
        seats.setText(Integer.toString(vehicle.getSeats()));
        doors.setText(Integer.toString(vehicle.getDoors()));
        type.setText(vehicle.getType());
        price.setText(vehicle.getPriceForDays() + " RSD");

        TextView date = (TextView) vi.findViewById(R.id.reg);
        TextView autom = (TextView) vi.findViewById(R.id.aut);

        String[] strings = vehicle.getRegUntil().split("T");

        date.setText(strings[0]);

        Button details = (Button) vi.findViewById(R.id.button);

        if (vehicle.isAutom() == true) {
            autom.setText("Automatic");
        } else {
            autom.setText("Manual");
        }

        details.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int status = NetworkUtils.getConnectivityStatus(activity);

                if(status == NetworkUtils.TYPE_WIFI || status == NetworkUtils.TYPE_MOBILE) {
                    CarService carService = (CarService) activity.getIntent().getSerializableExtra("carService");
                    String email = activity.getIntent().getStringExtra("email");

                    Intent intent = new Intent(activity, VehicleActivity.class);
                    intent.putExtra("vehicle", (Serializable) vehicle);
                    intent.putExtra("pickupDateTime", pickupDateTime);
                    intent.putExtra("returnDateTime", returnDateTime);
                    intent.putExtra("carService", (Serializable) carService);
                    intent.putExtra("email", email);
                    intent.putExtra("addServices", (Serializable) additionalServices);
                    activity.startActivity(intent);
                } else {
                    Toast.makeText(activity, NetworkUtils.getConnectivityStatusString(activity), Toast.LENGTH_SHORT).show();
                }
            }
        });

        String m = vehicle.getImageFile();

        //Picasso.get().setIndicatorsEnabled(true);
        Picasso.get().load(ServiceUtils.SERVICE_API_PATH + "search/getImage/" +m)
                .resize(300,300).into(image);

        return vi;
    }

}
