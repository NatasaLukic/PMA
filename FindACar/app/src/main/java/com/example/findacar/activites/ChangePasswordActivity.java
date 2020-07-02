package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.modelDTO.ChangePasswordDTO;
import com.example.findacar.service.ServiceUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePasswordActivity extends AppCompatActivity {

    private TextView oldPassword;
    private TextView newPassword;
    private TextView confirmPassword;
    private Button btnConfirm;
    private Button btnCancel;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getStringExtra("user");

        setContentView(R.layout.activity_change_password);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(oldPassword.getText().toString(), newPassword.getText().toString(), confirmPassword.getText().toString());
                validateInput(changePasswordDTO);
            }
        });
    }

    private void validateInput(ChangePasswordDTO changePasswordDTO) {
        boolean cancel = false;
        View focusView = null;

        if (isEmptyOrNull(changePasswordDTO.getOldPassword())) {
            oldPassword.setError("Email is required");
            focusView = oldPassword;
            cancel = true;
        } else if (!isPasswordValid(changePasswordDTO.getOldPassword())) {
            oldPassword.setError("Password is invalid!");
            focusView = oldPassword;
            cancel = true;
        }

        if (isEmptyOrNull(changePasswordDTO.getNewPassword())) {
            newPassword.setError("Email is required");
            focusView = newPassword;
            cancel = true;
        } else if (!isPasswordValid(changePasswordDTO.getNewPassword())) {
            newPassword.setError("Password is invalid!");
            focusView = newPassword;
            cancel = true;
        }

        if (isEmptyOrNull(changePasswordDTO.getConfirmPassword())) {
            confirmPassword.setError("Email is required");
            focusView = confirmPassword;
            cancel = true;
        } else if (!isPasswordValid(changePasswordDTO.getConfirmPassword())) {
            confirmPassword.setError("Password is invalid!");
            focusView = confirmPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            sendRegisterRequest(changePasswordDTO);
        }
    }

    private void sendRegisterRequest(ChangePasswordDTO changePasswordDTO) {
        Call<ResponseBody> call = ServiceUtils.findACarService.changePassword(email, changePasswordDTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChangePasswordActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePasswordActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ChangePasswordActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEmptyOrNull(String input) {
        if (input == null) {
            return true;
        }
        return input.isEmpty();
    }

    private boolean isPasswordValid(String password) {
        if (isEmptyOrNull(password) || password.length() < 5) {
            return false;
        }
        return true;
    }
}
