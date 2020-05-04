package com.example.findacar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.ChangePasswordActivity;


public class UserProfileFragment extends Fragment {

    private TextView name;
    private TextView surname;
    private TextView email;
    private Button btnChangePassword;
    private ImageButton editName;
    private ImageButton editSurname;
    private View view;

    public UserProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        name =  view.findViewById(R.id.textViewName);
        surname =  view.findViewById(R.id.textViewSurname);
        email =  view.findViewById(R.id.textViewEmail);
        btnChangePassword = (Button)  view.findViewById(R.id.btnChangePass);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        editName = (ImageButton)  view.findViewById(R.id.btnEditName);
        editSurname = (ImageButton)  view.findViewById(R.id.btnEditSurname);
        // TODO Populate text views
        return view;

    }
}
