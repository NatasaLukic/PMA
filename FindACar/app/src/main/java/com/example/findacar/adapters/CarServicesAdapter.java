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

import java.util.List;

public class CarServicesAdapter extends BaseAdapter {

    public Activity activity;
    public List<CarService> list;

    public CarServicesAdapter(Activity activity, List<CarService> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        CarService service = this.list.get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.carservices_list, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView address = (TextView)vi.findViewById(R.id.address);

        name.setText(service.getName());
        address.setText(service.getAddress().getCity());

        return  vi;
    }
}
