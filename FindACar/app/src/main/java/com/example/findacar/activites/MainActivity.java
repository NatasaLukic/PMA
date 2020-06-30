package com.example.findacar.activites;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findacar.R;
import com.example.findacar.service.SessionService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topAnimation, bottomAnimation;
    ImageView image;
    TextView logo;
    private SessionService sessionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);

        image.setAnimation(topAnimation);
        logo.setAnimation(bottomAnimation);

        sessionService = SessionService.getInstance(getApplicationContext());
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        if (!sessionService.getBooleanValue(SessionService.LOGGED_IN_PREF) ||
                                sessionService.getStringValue(SessionService.EMAIL) == null) {
                            intent = new Intent(MainActivity.this, LoginActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, DashboardActivity.class);
                            intent.putExtra("user", sessionService.getStringValue(SessionService.EMAIL));

                        }
                        startActivity(intent);
                        finish();
                    }
                }, SPLASH_SCREEN
        );
    }

}
