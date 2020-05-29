package com.example.findacar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.findacar.R;


public class SpinnerForSearchAdapter extends ArrayAdapter<String> {

    private static final int ITEM_HEIGHT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int ITEM_WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;

    private int textViewResourceId;

    public SpinnerForSearchAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            textView = (TextView) LayoutInflater.from(getContext())
                    .inflate(textViewResourceId, parent, false);
            textView.setPadding(30,20,15,10);
            textView.setTextSize(18);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(getItem(position));
        if (position == 0) {

            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.height = 1;
            layoutParams.width = ITEM_WIDTH;
            textView.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.height = ITEM_HEIGHT;
            layoutParams.width = ITEM_WIDTH;
            textView.setLayoutParams(layoutParams);
        }

        return textView;
    }
}
