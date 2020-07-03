package com.example.findacar.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.findacar.activites.DashboardActivity;

public class ConnectionReceiver extends BroadcastReceiver {

    int status;
    Context Cnt;
    Activity activity;
    AlertDialog alert;


    public ConnectionReceiver(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){

            status = getConnectivityStatus(context);

            if(status==0) {

                Toast.makeText(context, "Connection lost...", Toast.LENGTH_SHORT).show();
            }

        }




    }

    public int getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return 1;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return 2;
        }

        return 0;
    }

}
