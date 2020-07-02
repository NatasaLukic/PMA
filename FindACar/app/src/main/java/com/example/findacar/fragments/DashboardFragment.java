package com.example.findacar.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.findacar.R;
import com.example.findacar.activites.DashboardActivity;
import com.example.findacar.activites.SearchResultsActivity;
import com.example.findacar.adapters.SpinnerForSearchAdapter;
import com.example.findacar.model.CarService;
import com.example.findacar.modelDTO.SearchDTO;
import com.example.findacar.service.ServiceUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    FusedLocationProviderClient client;
    private double currentLocationX;
    private double currentLocationY;

    private EditText pickUpDate;
    private EditText returnDate;

    private String place;
    private CheckBox checkBoxCurrentLocation;
    private String datepickUp;
    private String dateReturn;

    private String timePickUp;
    private String timeReturn;

    private String[] items = {"", "Beograd", "Nis", "Novi Sad"};

    private EditText pickUpTime;
    private EditText returnTime;

    private DatePickerDialog.OnDateSetListener mDateSetListenerPickUp;
    private DatePickerDialog.OnDateSetListener mDateSetListenerReturn;

    private TimePickerDialog.OnTimeSetListener mTimeSetListenerPickUp;
    private TimePickerDialog.OnTimeSetListener mTimeSetListenerReturn;

    private Calendar cal = Calendar.getInstance();
    private int year = cal.get(Calendar.YEAR);
    private int month = cal.get(Calendar.MONTH);
    private int day = cal.get(Calendar.DAY_OF_MONTH);

    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);

    /*private BroadcastReceiver gpsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(LocationManager.PROVIDERS_CHANGED_ACTION)) {
                //Do your stuff on GPS status change
                Log.i("MAPA", "onReceive: mmmmmmmmmmmmmmmmmmm ");
                getLastLocation();
            }
        }
    };*/

    private void getLastLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.i("MAPA", "LOKACIJAAA " + location.getLatitude());
                currentLocationX = location.getLatitude();
                currentLocationY = location.getLongitude();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener, null);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
       // getContext().registerReceiver(gpsReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
        getLastLocation();
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final Spinner spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerForSearchAdapter(getContext(),
                R.layout.spinner_item, items);

        spinner.setAdapter(spinnerAdapter);

        pickUpDate = (EditText) view.findViewById(R.id.datePickUp);
        returnDate = (EditText) view.findViewById(R.id.dateReturn);

        pickUpTime = (EditText) view.findViewById(R.id.timePickUp);
        returnTime = (EditText) view.findViewById(R.id.timeReturn);

        checkBoxCurrentLocation = (CheckBox) view.findViewById(R.id.checkBoxCurrentLocation);

        ImageView imagePickUp = (ImageView) view.findViewById(R.id.imageCal2);
        ImageView imageReturn = (ImageView) view.findViewById(R.id.imageCal);


        Button next = view.findViewById(R.id.goNext);

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        //check permission
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }


        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Search
                LocationManager lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                boolean gps_enabled = false;
                boolean network_enabled = false;

                try {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch (Exception ex) {
                }

                try {
                    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                } catch (Exception ex) {
                }
                //if current location is checked and location is disabled
                if (!gps_enabled && !network_enabled && checkBoxCurrentLocation.isChecked()) {
                    Log.i("MAPA", "nema ukljucenu loakciju ");
                    // notify user
                    new AlertDialog.Builder(getContext())
                            .setMessage(R.string.gps_network_not_enabled)
                            .setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    getContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .show();


                } else if (gps_enabled && network_enabled && checkBoxCurrentLocation.isChecked()) {
                    //if location is enabled, get location cord
                    Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);
                    List<Address> addresses = new ArrayList<>();
                    Log.i("MAPA", "koliko je current X: " + currentLocationX);
                    Log.i("MAPA", "koliko je current Y: " + currentLocationY);

                    try {
                        addresses = geocoder.getFromLocation(currentLocationX, currentLocationY, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (addresses != null & addresses.size() > 0) {
                        //city from current location
                        place = addresses.get(0).getLocality();
                        Log.i("MAPA", "Grad je : " + place);
                    }
                    //send body for search
                    getSearchData();

                } else {
                    //city from db
                    place = spinner.getSelectedItem().toString();
                    getSearchData();
                }
            }


        });

        imagePickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog dialog = new DatePickerDialog(getActivity(),R.style.DatePickerDialog,
                        mDateSetListenerPickUp, year, month,day);

                dialog.show();

            }
        });

        mDateSetListenerPickUp = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                datepickUp = year + "-" + month + "-" + day;
                pickUpDate.setText(datepickUp);
                pickUpDate.setTextColor(getResources().getColor(R.color.colorDarkGray));

                TimePickerDialog dialogTime = new TimePickerDialog(getActivity(), R.style.DatePickerDialog, mTimeSetListenerPickUp, hour, minute, true);
                dialogTime.show();
            }
        };

        mTimeSetListenerPickUp = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                timePickUp = hourOfDay + ":" + minute + ":00";
                pickUpTime.setText(timePickUp);
                pickUpTime.setTextColor(getResources().getColor(R.color.colorDarkGray));

            }
        };

        mTimeSetListenerReturn = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                timeReturn = hourOfDay + ":" + minute + ":00";
                returnTime.setText(timeReturn);
                returnTime.setTextColor(getResources().getColor(R.color.colorDarkGray));
            }
        };


        imageReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.DatePickerDialog,
                        mDateSetListenerReturn, year, month,day);
                dialog.show();
            }
        });

        mDateSetListenerReturn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                dateReturn = year + "-" + month + "-" + day;
                returnDate.setText(dateReturn);
                returnDate.setTextColor(getResources().getColor(R.color.colorDarkGray));


                TimePickerDialog dialogTime = new TimePickerDialog(getActivity(), R.style.DatePickerDialog, mTimeSetListenerReturn, hour, minute, true);
                dialogTime.show();
            }
        };

        return view;
    }

    public Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    private void getCurrentLocation() {
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocationX = location.getLatitude();
                    currentLocationY = location.getLongitude();
                    Log.i("Mapa", "currentX  je: " + currentLocationX);
                    Log.i("Mapa", "currentY  je: " + currentLocationY);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("MAPA", "result code je sta: " +requestCode);
        if(requestCode == 44) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //when permission granted
                getCurrentLocation();
            }
        }
    }

    private void getSearchData() {
        SearchDTO searchDTO = new SearchDTO(place, datepickUp + " " + timePickUp,
                dateReturn + " " + timeReturn);

        int status = DashboardActivity.getConnectivityStatus(getContext());

        if(status == DashboardActivity.TYPE_WIFI) {

            Call<List<CarService>> call = ServiceUtils.findACarService.searchCity(searchDTO);
            call.enqueue(new Callback<List<CarService>>() {
                @Override
                public void onResponse(Call<List<CarService>> call, Response<List<CarService>> response) {
                    if (response.isSuccessful()) {
                        response.body();

                        List<CarService> list = response.body();
                        //if current location is checked - search
                        if (checkBoxCurrentLocation.isChecked()) {
                            //current location object
                            Location currentLocation = new Location(LocationManager.GPS_PROVIDER);
                            currentLocation.setLatitude(currentLocationX);
                            currentLocation.setLongitude(currentLocationY);
                            float distance = 0;
                            Location tempLocation = new Location(LocationManager.GPS_PROVIDER);
                            for (int i = 0; i < list.size(); i++) {
                                tempLocation.setLatitude(list.get(i).getAddress().getX());
                                tempLocation.setLongitude(list.get(i).getAddress().getY());
                                //calculate distance
                                distance = currentLocation.distanceTo(tempLocation);
                                Log.i("Mapa", "razdaljinaaaaaaaaaaa je: " + distance);
                                Log.i("Mapa", "trenutni radius je: " + DashboardActivity.currentRadius);

                                if (distance / 1000 > DashboardActivity.currentRadius) {
                                    //outside radius area, remove from list
                                    list.remove(list.get(i));
                                }
                            }
                        }


                        Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                        Gson gson = new Gson();
                        intent.putExtra("services", gson.toJson(list));
                        intent.putExtra("pickUp", datepickUp + " " + timePickUp);
                        intent.putExtra("return", dateReturn + " " + timeReturn);
                        intent.putExtra("place", place);
                        intent.putExtra("currentLocationX", currentLocationX);
                        intent.putExtra("currentLocationY", currentLocationY);
                        String email = getActivity().getIntent().getStringExtra("user");
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<List<CarService>> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        } else {
            Toast.makeText(getContext(), "No connection!", Toast.LENGTH_SHORT).show();
        }
    }
}
