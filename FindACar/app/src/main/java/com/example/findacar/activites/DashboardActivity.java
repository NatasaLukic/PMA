package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import com.example.findacar.R;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.fragments.DashboardFragment;
import com.example.findacar.fragments.FavoriteVehiclesFragment;
import com.example.findacar.fragments.ReservationsFragment;
import com.example.findacar.fragments.UserProfileFragment;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.VehicleWithReviews;
import com.example.findacar.modelDTO.LogInDTO;
import com.example.findacar.service.ServiceUtils;
import com.example.findacar.service.SyncService;
import com.example.findacar.service.SessionService;
import com.example.findacar.utils.IReservationsHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements IReservationsHelper, NavigationView.OnNavigationItemSelectedListener {

    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;

    public static String SYNC_DATA = "SYNC_DATA";

    private DrawerLayout drawer;
    public NavigationView navigationView;
    public List<Reservation> prev = new ArrayList<Reservation>();
    public List<Reservation> active = new ArrayList<Reservation>();
    public String email;
    public UserDatabase userDatabase;
    public List<VehicleWithReviews> vehiclesWithReviews;
    private SessionService sessionService;

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        email = getIntent().getStringExtra("user");
        getData(email);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putString("user", email).apply();
        userDatabase = UserDatabase.getInstance(this);

        //Sinhronizacija

        System.out.println("email " + email);
        long userId = userDatabase.userDao().loadSingleByEmail(email);

        UserWithVehiclesAndReviews userWithVehiclesAndReviews = userDatabase.userDao()
                .getUserWithVehiclesAndReviews(userId);

//        System.out.println("Broj u listi " + userWithVehiclesAndReviews.vehiclesWithReviews.size());

        if (userWithVehiclesAndReviews.vehiclesWithReviews.size() > 0){

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent alarmIntent = new Intent(this, SyncService.class);
            alarmIntent.putExtra("email", email);
            pendingIntent = PendingIntent.getService(this, 0, alarmIntent, 0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                    60000*5, pendingIntent); // na 5 min

        }


        sessionService = SessionService.getInstance(getApplicationContext());
        sessionService.insertStringValue("user", email);

        Log.e("TAD", email);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            //To do//
                            return;
                        }
                        // Get the Instance ID token//
                        String token = task.getResult().getToken();
                        Log.e("TAG", token);
                        Call<ResponseBody> call = ServiceUtils.findACarService.sendFcmToken(email, token);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        });

                    }
                });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
                break;
            case R.id.nav_user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserProfileFragment()).commit();
                break;
            case R.id.nav_reservations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReservationsFragment(email)).commit();
                break;
            case R.id.nav_favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteVehiclesFragment(email)).commit();
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_language:
                showLanguageChangeDialog();
                break;
            case R.id.nav_logout:
                logOut();
                break;
        }
        navigationView.setCheckedItem(menuItem.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
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


    public static int getConnectivityStatus(Context context){

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }

        return TYPE_NOT_CONNECTED;
    }


    private void logOut() {
        sessionService.remove(SessionService.EMAIL);
        sessionService.remove(SessionService.LOGGED_IN_PREF);
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void getData(String userEmail){
        Call<List<Reservation>> call = ServiceUtils.findACarService.getUserReservations(userEmail);

        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {

                if(response.isSuccessful()){
                    List<Reservation> res = response.body();

                    for(Reservation r : res){
                        if(checkDate(r) == true){
                            active.add(r);
                        } else {
                            prev.add(r);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                System.out.println("aaaaaaaaaaaaaaa");
                Log.e("ERROR", t.getMessage());
            }
        });

    }

    public boolean checkDate(Reservation r){

        if (new Date().after(r.getReturnDate())) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public List<Reservation> getPrevious() {
        return prev;
    }

    @Override
    public List<Reservation> getCurrent() {
        return active;
    }

}
