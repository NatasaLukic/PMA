package com.example.findacar.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.model.AdditionalService;

import java.util.List;

import dalvik.system.BaseDexClassLoader;

public class AdditionalServicesReservationAdapter extends BaseAdapter {

    private Activity activity;
    private List<AdditionalService> additionalServices;

    public AdditionalServicesReservationAdapter() {
    }

    public AdditionalServicesReservationAdapter(Activity activity, List<AdditionalService> additionalServices) {
        this.activity = activity;
        this.additionalServices = additionalServices;
    }

    @Override
    public int getCount() {
        return this.additionalServices.size();
    }

    @Override
    public Object getItem(int position) {
        return this.additionalServices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        AdditionalService additionalService = additionalServices.get(position);
        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_additional_service_reservation, null);

        TextView name = (TextView) vi.findViewById(R.id.textAdd);
        TextView price = (TextView) vi.findViewById(R.id.priceAdd);

        name.setText(additionalService.getName());
        price.setText(String.valueOf(additionalService.getPrice()) + " RSD");



        return vi;

    }
}
