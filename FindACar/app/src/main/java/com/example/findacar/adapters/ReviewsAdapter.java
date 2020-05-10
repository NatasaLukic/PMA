package com.example.findacar.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.model.Review;

import java.util.List;

public class ReviewsAdapter  extends BaseAdapter {

    private List<Review> mDataset;
    public Activity activity;

    public ReviewsAdapter(List<Review> mDataset, Activity activity) {
        this.mDataset = mDataset;
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
        View vi = convertView;
        Review review = (Review) getItem(position);
        if (convertView == null) {
            vi = activity.getLayoutInflater().inflate(R.layout.fragment_review, null);
        }
        TextView user = (TextView) vi.findViewById(R.id.textView20);
        TextView date = (TextView) vi.findViewById(R.id.textView18);
        TextView comment = (TextView) vi.findViewById(R.id.textView21);
        RatingBar rating = (RatingBar) vi.findViewById(R.id.ratingBar4);

        user.setText(review.getUser().getName() + " " + review.getUser().getSurname());
        date.setText("Date: 15/04/2020");
        comment.setText(review.getComment());
        rating.setRating(review.getRate());
        rating.setEnabled(false);
        return vi;
    }
}
