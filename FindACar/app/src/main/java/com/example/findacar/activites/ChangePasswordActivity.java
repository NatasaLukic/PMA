package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.findacar.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextView oldPassword;
    private TextView newPassword;
    private TextView confirmPassword;
    private Button btnOk;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        btnOk = (Button) findViewById(R.id.buttonOk);
        btnCancel = (Button) findViewById(R.id.buttonCancel);
    }
}
