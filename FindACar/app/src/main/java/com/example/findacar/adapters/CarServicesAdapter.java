package com.example.findacar.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.model.CarService;
import com.example.findacar.mockupData.CarServices;

public class CarServicesAdapter extends BaseAdapter {

    public Activity activity;

    public CarServicesAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return CarServices.getCarServices().size();
    }

    @Override
    public Object getItem(int position) {
        return CarServices.getCarServices().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        CarService service = CarServices.getCarServices().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.carservices_list, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView address = (TextView)vi.findViewById(R.id.address);
        ImageView image = (ImageView)vi.findViewById(R.id.item_icon);

        name.setText(service.getName());
        address.setText(service.getAddress());

        if (service.getAvatar() != -1){
            image.setImageResource(service.getAvatar());
        }

        return  vi;
    }
}
