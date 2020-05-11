package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.findacar.R;

public class EditProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView surname;
    private TextView email;
    private Button btnConfirm;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = findViewById(R.id.nameNewValue);
        surname = findViewById(R.id.surnameNewValue);
        email = findViewById(R.id.emailNewValue);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }
}
