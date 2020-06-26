package com.example.findacar.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.findacar.R;
import com.example.findacar.model.VehiclePhoto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class VehiclePhotosAdapter extends PagerAdapter {

    private Context mContext;
    private int position;
    private List<String> urls = new ArrayList<String>();
    //public static final String SERVICE_API_PATH = "";
    //public static final String SERVICE_API_PATH = "http://192.168.0.26:8057/";
    public static final String SERVICE_API_PATH = "http://192.168.0.15:8057/";


    public VehiclePhotosAdapter(Context context, List<VehiclePhoto> photos){
        this.mContext = context;

        Collections.sort(photos, new SortVehiclePhotos());
        for(VehiclePhoto v: photos)
            this.urls.add(v.getPath());

    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(SERVICE_API_PATH + "search/getImage/" + urls.get(position)).into(imageView);
        //imageView.setImageResource(images.get(position));
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);
    }

    public class SortVehiclePhotos implements Comparator<VehiclePhoto>
    {

        @Override
        public int compare(VehiclePhoto o1, VehiclePhoto o2) {

            return (int) (o1.getId() - o2.getId());
        }

    }
}
