package com.example.findacar.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.findacar.R;
import com.example.findacar.adapters.PreviousReservationAdapter;
import com.example.findacar.model.Reservation;
import com.example.findacar.modelDTO.CreateReviewDTO;
import com.example.findacar.service.ServiceUtils;
import com.example.findacar.utils.IReservationsHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link ServiceRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceRatingFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static ListView listView;
    private static View view;
    private static int pos;
    private Reservation reservation;
    private String email;
    private PreviousReservationAdapter previousReservationAdapter;
    private int position;
    private View view2;
    EditText editText;
    IReservationsHelper reservationsHelper;

    public ServiceRatingFragment() {
        // Required empty public constructor
    }

    public ServiceRatingFragment(Reservation reservation, String email, PreviousReservationAdapter previousReservationAdapter, int position, View view) {
        this.reservation = reservation;
        this.email = email;
        this.previousReservationAdapter = previousReservationAdapter;
        this.position = position;
        this.view2 = view;
    }

    public static ServiceRatingFragment newInstance(ListView l, View v, int position) {
        listView = l;
        view = v;
        pos = position;
        ServiceRatingFragment fragment = new ServiceRatingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
      //  reservationsHelper = (IReservationsHelper)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_service_rating, container, false);

        final RatingBar rating = (RatingBar) view.findViewById(R.id.ratingBar);

        final LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();

        editText = (EditText) view.findViewById(R.id.comment);

        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.gold), PorterDuff.Mode.SRC_ATOP);

        //  drawable.setColorFilter(Color.parseColor("#DAA520"), PorterDuff.Mode.SRC_ATOP);

        Button confirm = view.findViewById(R.id.buttonConfirm);
        Button cancel = view.findViewById(R.id.buttonCancel);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm =
                        (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                final String m = String.valueOf(editText.getText());
                final String s = String.valueOf(rating.getRating());

                CreateReviewDTO reviewDTO = new CreateReviewDTO(reservation.getId(), editText.getText().toString(), String.valueOf(rating.getRating()), email);

                Call<List<Reservation>> call = ServiceUtils.findACarService.addReview(reviewDTO);

                call.enqueue(new Callback<List<Reservation>>() {
                    @Override
                    public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {

                        if (response.isSuccessful()) {
                            getDialog().dismiss();
                            List<Reservation> res = response.body();
                            previousReservationAdapter.setmDataset(res);
                            previousReservationAdapter.closeButton(position, view2, m, s);


                        } else {
                            Log.e("r", String.valueOf(response));
                        }
                    }


                    @Override
                    public void onFailure(Call<List<Reservation>> call, Throwable t) {
                        Log.e("ERROR", t.getMessage());

                    }
                });

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
