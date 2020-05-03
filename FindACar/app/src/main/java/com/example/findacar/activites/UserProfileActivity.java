package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.findacar.R;

public class UserProfileActivity extends AppCompatActivity {
    private TextView name;
    private TextView surname;
    private TextView email;
    private Button btnChangePassword;
    private Button btnMyReservations;
    private ImageButton editName;
    private ImageButton editSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.textViewName);
        surname = findViewById(R.id.textViewSurname);
        email = findViewById(R.id.textViewEmail);
        btnChangePassword = (Button) findViewById(R.id.btnChangePass);
        btnMyReservations = (Button) findViewById(R.id.btnMyReservations);
        editName = (ImageButton) findViewById(R.id.btnEditName);
        editSurname = (ImageButton) findViewById(R.id.btnEditSurname);
    }

    public void navigateChangePassword(View view) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void navigateMyReservations(View view) {
        Intent intent = new Intent(this, ReservationsActivity.class);
        startActivity(intent);
    }
}
