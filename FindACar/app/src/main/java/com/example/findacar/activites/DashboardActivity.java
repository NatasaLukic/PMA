package com.example.findacar.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.findacar.R;
import com.example.findacar.database.UserDatabase;
import com.example.findacar.fragments.DashboardFragment;
import com.example.findacar.fragments.FavoriteVehiclesFragment;
import com.example.findacar.fragments.ReservationsFragment;
import com.example.findacar.fragments.UserProfileFragment;
import com.example.findacar.model.Reservation;
import com.example.findacar.model.User;
import com.example.findacar.model.UserWithVehiclesAndReviews;
import com.example.findacar.model.VehicleWithReviews;
import com.example.findacar.service.ConnectionReceiver;
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

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static String SYNC_DATA = "SYNC_DATA";

    private static int checkedLanguage = 0;
    private static int checkedRadius = 1;
    public static int currentRadius = 3;

    private DrawerLayout drawer;
    public NavigationView navigationView;
    public String email;
    public UserDatabase userDatabase;
    public List<VehicleWithReviews> vehiclesWithReviews;
    private SessionService sessionService;
    private String currentFragment;

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    private ConnectionReceiver connectionReceiver;


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
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putString("user", email).apply();
        userDatabase = UserDatabase.getInstance(this);

        User logged = userDatabase.userDao().getUser(email);


        //Sinhronizacija

        System.out.println("email " + email);
        long userId = userDatabase.userDao().loadSingleByEmail(email);

        UserWithVehiclesAndReviews userWithVehiclesAndReviews = userDatabase.userDao()
                .getUserWithVehiclesAndReviews(userId);

//        System.out.println("Broj u listi " + userWithVehiclesAndReviews.vehiclesWithReviews.size());

        if(userWithVehiclesAndReviews != null) {

            if (userWithVehiclesAndReviews.vehiclesWithReviews.size() > 0) {

                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent alarmIntent = new Intent(this, SyncService.class);
                alarmIntent.putExtra("email", email);
                pendingIntent = PendingIntent.getService(this, 0, alarmIntent, 0);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                        60000 * 4, pendingIntent); // na 5 min

            }
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

        View v = navigationView.getHeaderView(0);

        TextView name = v.findViewById(R.id.nameOfUser);

        if(logged != null){

            name.setText(logged.getFirstName() + " " + logged.getLastName());
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment(), "A").commit();
            currentFragment = "A";
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment(), "A").commit();
                currentFragment = "A";
                break;
            case R.id.nav_user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserProfileFragment(), "B").commit();
                currentFragment = "B";
                break;
            case R.id.nav_reservations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReservationsFragment(email), "C" ).commit();
                currentFragment = "C";
                break;
            case R.id.nav_favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteVehiclesFragment(email), "D").commit();
                currentFragment = "D";
                break;
            case R.id.nav_settings:
                showSettingsChangeDialog();
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
        mBuilder.setSingleChoiceItems(listItems, checkedLanguage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    checkedLanguage = 0;
                    setLocale("en");
                    dialog.dismiss();
                    Fragment frg = null;
                    frg = getSupportFragmentManager().findFragmentByTag(currentFragment);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.drawer_menu);
                } else if (i == 1) {
                    checkedLanguage = 1;
                    setLocale("sr");
                    Fragment frg = null;
                    dialog.dismiss();
                    frg = getSupportFragmentManager().findFragmentByTag(currentFragment);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.drawer_menu);
                }


            }
        });


        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void logOut() {
        sessionService.remove(SessionService.EMAIL);
        sessionService.remove(SessionService.LOGGED_IN_PREF);
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }




    public void showSettingsChangeDialog() {
        final String[] listItems = {"1km", "3km", "5km", "7km", "9km"};
        AlertDialog.Builder radiusBuilder = new AlertDialog.Builder(DashboardActivity.this);
        radiusBuilder.setTitle(R.string.set_radius);
        radiusBuilder.setSingleChoiceItems(listItems, checkedRadius, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    checkedRadius = 0;
                    currentRadius = 1;

                } else if (i == 1) {
                    checkedRadius = 1;
                    currentRadius = 3;

                } else if (i == 2) {
                    checkedRadius = 2;
                    currentRadius = 5;

                } else if (i == 3) {
                    checkedRadius = 3;
                    currentRadius = 7;

                } else if (i == 4) {
                    checkedRadius = 4;
                    currentRadius = 9;

                }
                dialog.dismiss();
            }
        });


        AlertDialog mDialog = radiusBuilder.create();
        mDialog.show();
    }

    @Override
    protected void onResume(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        connectionReceiver = new ConnectionReceiver();
        registerReceiver(connectionReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause(){
        unregisterReceiver(connectionReceiver);
        super.onPause();
    }


}
