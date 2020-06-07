package com.example.findacar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findacar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader = new ArrayList<String>();
    private HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    // Hashmap for keeping track of our checkbox check states
    private HashMap<Integer, boolean[]> mChildCheckStates;

    public FilterListAdapter(Context context, List<String> listDataHeader,
                             HashMap<String, List<String>> listChildData) {

        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        mChildCheckStates = new HashMap<>();
    }

    public List<String> getListDataHeader() {
        return listDataHeader;
    }

    public HashMap<String, List<String>> getListDataChild() {
        return listDataChild;
    }

    public HashMap<Integer, boolean[]> getmChildCheckStates() {
        return mChildCheckStates;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);


        ImageView imageView = (ImageView) convertView.findViewById(R.id.picFilter);

        if (headerTitle.equals(context.getString(R.string.motor))) {
            imageView.setImageResource(R.drawable.ic_fuel);
        } else if (headerTitle.equals(context.getString(R.string.carType))) {

            imageView.setImageResource(R.drawable.ic_directions_car_black_filter);
        } else if (headerTitle.equals(context.getString(R.string.numOfBags))) {

            imageView.setImageResource(R.drawable.ic_suitcase);

        } else if (headerTitle.equals(context.getString(R.string.transmission))) {

            imageView.setImageResource(R.drawable.ic_manual);

        } else if (headerTitle.equals(context.getString(R.string.airCond))) {

            imageView.setImageResource(R.drawable.ic_air_cond);
        }

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        final CheckBox checkBox = convertView.findViewById(R.id.check);
        /*
         * You have to set the onCheckChangedListener to null
         * before restoring check states because each call to
         * "setChecked" is accompanied by a call to the
         * onCheckChangedListener
         */
        checkBox.setOnCheckedChangeListener(null);
        if (mChildCheckStates.containsKey(groupPosition)) {
            boolean[] checked = mChildCheckStates.get(groupPosition);
            checkBox.setChecked(checked[childPosition]);

        } else {
            boolean[] checked = new boolean[getChildrenCount(groupPosition)];
            mChildCheckStates.put(groupPosition, checked);
            checkBox.setChecked(false);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean[] checked = mChildCheckStates.get(groupPosition);
                checked[childPosition] = isChecked;
                mChildCheckStates.remove(groupPosition);
                mChildCheckStates.put(groupPosition, checked);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
