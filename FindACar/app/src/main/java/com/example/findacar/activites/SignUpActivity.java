package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findacar.R;
import com.example.findacar.modelDTO.RegisterDTO;
import com.example.findacar.service.ServiceUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    Button callSignIn;
    Button btnSignUp;
    EditText email;
    EditText name;
    EditText surname;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        callSignIn = findViewById(R.id.login_screen);

        callSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        email = findViewById(R.id.email);
        name = findViewById(R.id.firstName);
        surname = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.passwordConfirm);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegisterDTO registerDTO = new RegisterDTO(email.getText().toString(), name.getText().toString(), surname.getText().toString(),
                        password.getText().toString(), confirmPassword.getText().toString());
                validateUserInput(registerDTO);

            }
        });
    }

    private void validateUserInput(RegisterDTO registerDTO) {

        boolean cancel = false;
        View focusView = null;

        if (isEmptyOrNull(registerDTO.getEmail())) {
            email.setError("Email is required");
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(registerDTO.getEmail())) {
            email.setError("Email is invalid!");
            focusView = email;
            cancel = true;
        }

        if (isEmptyOrNull(registerDTO.getName())) {
            name.setError("First Name is required!");
            focusView = name;
            cancel = true;
        }
        if (isEmptyOrNull(registerDTO.getSurname())) {
            surname.setError("Last Name is required!");
            focusView = surname;
            cancel = true;
        }

        if (isEmptyOrNull(registerDTO.getPassword())) {
            password.setError("Password is required!");
            focusView = password;
            cancel = true;
        } else if (!isPasswordValid(registerDTO.getPassword())) {
            password.setError("Password is invalid!");
            focusView = password;
            cancel = true;
        }

        if (isEmptyOrNull(registerDTO.getConfirmPassword())) {
            confirmPassword.setError("Password is required");
            focusView = confirmPassword;
            cancel = true;
        } else if (!isPasswordValid(registerDTO.getConfirmPassword())) {
            confirmPassword.setError("Password is invalid");
            focusView = confirmPassword;
            cancel = true;
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            Toast.makeText(SignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            sendRegisterRequest(registerDTO);
        }
    }

    private void sendRegisterRequest(RegisterDTO registerDTO) {
        Call<ResponseBody> call = ServiceUtils.findACarService.register(registerDTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "We sent you an email message. Please check your inbox to verify your email.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(SignUpActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
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

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
