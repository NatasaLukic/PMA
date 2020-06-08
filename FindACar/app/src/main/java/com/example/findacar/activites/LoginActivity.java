package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.model.LogInModel;
import com.example.findacar.model.User;
import com.example.findacar.service.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    UserDatabase userDatabase;
    List<User> usersList = new ArrayList<>();

    Button callSignUp, callLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDatabase = UserDatabase.getInstance(this);

        callLogin = findViewById(R.id.login);
        callSignUp = findViewById(R.id.signup_screen);
        progressBar = findViewById(R.id.loading);

        callLogin.setVisibility(View.VISIBLE);
        callSignUp.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callLogin.setVisibility(View.GONE);
                callSignUp.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                email = (EditText) findViewById(R.id.email);
                password = (EditText) findViewById(R.id.password);

                final String emailSend = email.getText().toString();
                String passwordSend = password.getText().toString();

                LogInModel logIn = new LogInModel();

                logIn.setEmail(emailSend);
                logIn.setPassword(passwordSend);

                Call<Boolean> call = ServiceUtils.findACarService.login(logIn);

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        if (response.body() == true) {

                            User order = new User("ilinkaIphone X", "kovacevic", "il@gmail.com", "ilinka");
                            userDatabase.userDao().insert(order);

                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.putExtra("user", emailSend);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();

                            callLogin.setVisibility(View.VISIBLE);
                            callSignUp.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            }
        });


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });

    }

}
