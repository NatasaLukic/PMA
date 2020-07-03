package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.fragments.UserProfileFragment;
import com.example.findacar.model.User;
import com.example.findacar.modelDTO.ChangePasswordDTO;
import com.example.findacar.modelDTO.EditProfileDTO;
import com.example.findacar.service.ServiceUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private TextView firstName;
    private TextView lastName;
    private Button btnConfirm;
    private Button btnCancel;
    private UserDatabase db;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getIntent().getStringExtra("user");

        db = UserDatabase.getInstance(this);
        String  firstNameUser = db.userDao().loadSingle(email);
        String lastNameUser = db.userDao().loadSingleLastName(email);
        setContentView(R.layout.activity_edit_profile);
        firstName = findViewById(R.id.nameNewValue);
        lastName = findViewById(R.id.surnameNewValue);

        firstName.setText(firstNameUser,TextView.BufferType.EDITABLE);
        lastName.setText(lastNameUser, TextView.BufferType.EDITABLE);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfileDTO editProfileDTO = new EditProfileDTO(firstName.getText().toString(), lastName.getText().toString());
                validateInput(editProfileDTO);
            }
        });
    }

    private void validateInput(EditProfileDTO editProfileDTO) {
        boolean cancel = false;
        View focusView = null;

        if (isEmptyOrNull(editProfileDTO.getFirstName())) {
            firstName.setError("Name is required");
            focusView = firstName;
            cancel = true;
        }

        if (isEmptyOrNull(editProfileDTO.getLastName())) {
            lastName.setError("Surname is required");
            focusView = lastName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            sendRegisterRequest(editProfileDTO);
        }
    }

    private void sendRegisterRequest(EditProfileDTO editProfileDTO) {
        Call<ResponseBody> call = ServiceUtils.findACarService.editProfile(email, editProfileDTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStackImmediate();
                    finish();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEmptyOrNull(String input) {
        if (input == null) {
            return true;
        }
        return input.isEmpty();
    }
}
