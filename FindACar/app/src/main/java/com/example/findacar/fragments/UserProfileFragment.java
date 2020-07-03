package com.example.findacar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.ChangePasswordActivity;
import com.example.findacar.activites.EditProfileActivity;
import com.example.findacar.database.UserDatabase;


public class UserProfileFragment extends Fragment {

    private Button btnEditProfile;
    private Button btnChangePassword;
    private View view;
    private String email;
    private TextView firstName;
    private TextView lastName;
    private  TextView userEmail;
    private UserDatabase db;

    public UserProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        email = getActivity().getIntent().getStringExtra("user");

        view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        firstName = (TextView) view.findViewById(R.id.userFirstName);
        lastName = (TextView) view.findViewById(R.id.userLastName);
        userEmail = (TextView) view.findViewById(R.id.userEmail);
        db = UserDatabase.getInstance(getActivity());
        String  firstNameUser = db.userDao().loadSingle(email);
        String lastNameUser = db.userDao().loadSingleLastName(email);
        firstName.setText(firstNameUser);
        lastName.setText(lastNameUser);
        userEmail.setText(email);

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
                intent.putExtra("user", email);
                startActivity(intent);
            }
        });
        return view;

    }
}
