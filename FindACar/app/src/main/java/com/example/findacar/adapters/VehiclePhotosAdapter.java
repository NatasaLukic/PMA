package com.example.findacar.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;

import java.util.ArrayList;

public class VehiclePhotosAdapter extends PagerAdapter {

    private Context mContext;
    private int position;
    private ArrayList<Integer> images = new ArrayList<Integer>();

    public VehiclePhotosAdapter(Context context, int position){
        this.mContext = context;
        this.position = position;
        this.images.add(R.drawable.nissan_1_2);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images.get(position));
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);
    }
}
