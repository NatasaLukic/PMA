package com.example.findacar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.ChangePasswordActivity;
import com.example.findacar.activites.EditProfileActivity;


public class UserProfileFragment extends Fragment {

    private Button btnEditProfile;
    private Button btnChangePassword;
    private View view;
    private String email;

    public UserProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        email = getActivity().getIntent().getStringExtra("user");

        view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        btnChangePassword = (Button)  view.findViewById(R.id.btnChangePass);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                intent.putExtra("user", email);
                startActivity(intent);
            }
        });

        btnEditProfile = (Button)  view.findViewById(R.id.btnEditProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
