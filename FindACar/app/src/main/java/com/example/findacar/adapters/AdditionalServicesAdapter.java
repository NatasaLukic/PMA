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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class AdditionalServicesAdapter extends BaseAdapter implements AdditionalServicesHelper {

    private Activity activity;
    private List<AdditionalService> additionalServices;
    private HashMap<String, Boolean> map;

    public AdditionalServicesAdapter() {
    }

    public AdditionalServicesAdapter(Activity activity, List<AdditionalService> additionalServices) {
        this.activity = activity;
        this.additionalServices = additionalServices;
        this.map = new HashMap<String, Boolean>();
    }

    @Override
    public int getCount() {
        return additionalServices.size();
    }

    @Override
    public Object getItem(int position) {
        return additionalServices.get(position);
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
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_additional_service, null);

        final TextView name = (TextView) vi.findViewById(R.id.textAdd);
        TextView price = (TextView) vi.findViewById(R.id.priceAdd);

        final CheckBox checkBox = (CheckBox) vi.findViewById(R.id.chechAdd);
        name.setText(additionalService.getName());
        price.setText(String.valueOf(additionalService.getPrice()) + " RSD");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    map.put(name.getText().toString(), true);
                    Log.e("MAPA UBACI", name.getText().toString());
                } else {
                    map.remove(name.getText().toString());
                    Log.e("MAPA IZBACI", name.getText().toString());
                }
            }
        });


        return vi;

    }

    @Override
    public HashMap<String, Boolean> getAdditional() {
        return map;
    }
}
