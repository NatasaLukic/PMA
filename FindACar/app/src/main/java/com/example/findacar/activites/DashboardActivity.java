package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.findacar.R;
import com.example.findacar.fragments.DashboardFragment;
import com.example.findacar.fragments.ReservationsFragment;
import com.example.findacar.fragments.UserProfileFragment;
import com.example.findacar.model.Reservation;
import com.example.findacar.service.ServiceUtils;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    public NavigationView navigationView;
    public List<Reservation> prev = new ArrayList<Reservation>();
    public List<Reservation> active = new ArrayList<Reservation>();
    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = getIntent().getStringExtra("user");
        Log.e("TAD", email);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new DashboardFragment()).commit();
                break;
            case R.id.nav_user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new UserProfileFragment()).commit();
                break;
            case R.id.nav_reservations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new ReservationsFragment(email)).commit();
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_language:
                showLanguageChangeDialog();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        navigationView.setCheckedItem(menuItem.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void  onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loadLanguage() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "en");
        setLocale(language);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void showLanguageChangeDialog() {
        final String[] listItems = {"English", "Serbian"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DashboardActivity.this);
        mBuilder.setTitle(R.string.choose_language);
        mBuilder.setItems(listItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocale("en");
                    recreate();
                } else if (i == 1) {
                    setLocale("sr");
                    recreate();
                }
                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

}
