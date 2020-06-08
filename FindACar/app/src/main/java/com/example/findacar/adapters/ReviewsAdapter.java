package com.example.findacar.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.mockupData.Reviews;
import com.example.findacar.model.Review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter  extends BaseAdapter {

    private List<Review> mDataset;
    public Activity activity;

    public ReviewsAdapter(Activity activity, List<Review> reviews) {
        this.mDataset = reviews;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("VVVVVVVV", String.valueOf(mDataset.size()));
        View vi = convertView;
        Review review = mDataset.get(position);
        if (convertView == null) {
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_review, null);
        }
        TextView user = (TextView) vi.findViewById(R.id.user);
        TextView date = (TextView) vi.findViewById(R.id.date);
        TextView comment = (TextView) vi.findViewById(R.id.comment);
        RatingBar rating = (RatingBar) vi.findViewById(R.id.ratingBar4);

        Drawable drawable = rating.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.SRC_ATOP);

        user.setText(review.getUser().getFirstName() + " " + review.getUser().getLastName());

        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate= DateFor.format(review.getDate());
        date.setText(stringDate);

        comment.setText(review.getComment());
        rating.setRating((float) review.getRating());
        rating.setEnabled(false);
        return vi;
    }
}
