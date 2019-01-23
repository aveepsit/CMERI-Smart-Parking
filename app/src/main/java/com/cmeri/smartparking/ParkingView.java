/*
 *      Created By: Aveepsit Chowdhury
 *      File Name: ParkingView
 *      Project Name: CMERI-Smart-Parking
 *      Created on: 23-01-2019 08:12 PM
 *      Additional Notes:
 */

package com.cmeri.smartparking;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.io.IOException;
import java.util.Objects;

public class ParkingView extends AppCompatActivity {
    public static final int OFFICE_PARKING = 1;
    public static final int COLONY_PARKING = 2;
    // Set These Values:
    private final static String cmeri_andro_get_url = "http://cmerismartparking.xyz/new_andro_get.php";
    private final static int officeParkingCount = 100;
    private final static int colonyParkingCount = 100;
    private final static int timeStrSize = 20;
    private static int parkType = OFFICE_PARKING;
    private static int parkCount = officeParkingCount;
    private static int resIndStart = 3;
    private static int resIndEnd = resIndStart + officeParkingCount;
    private static int timeIndStart = 5 + officeParkingCount + colonyParkingCount;
    private static int timeIndEnd = timeIndStart + timeStrSize;
    protected boolean curStat[];
    protected ParkingSpot ps[];
    protected WebsiteParser wp;

    public static void setParking(int no) {
        parkType = no;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Fetching parking status..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                (new DoUpdate()).execute(view);
            }
        });
        if (parkType == OFFICE_PARKING) {
            resIndStart = 3;
            resIndEnd = resIndStart + officeParkingCount;
            parkCount = officeParkingCount;
            setTitle(R.string.office_park_title);
        } else if (parkType == COLONY_PARKING) {
            resIndStart = 3 + (officeParkingCount + 1);
            resIndEnd = resIndStart + colonyParkingCount;
            parkCount = colonyParkingCount;
            setTitle(R.string.colony_park_title);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        try {
            wp = new WebsiteParser(cmeri_andro_get_url);
        } catch (IOException e) {
            Snackbar.make(fab, "URL is Malformed..", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        curStat = new boolean[parkCount];
        ps = new ParkingSpot[parkCount];
        GridView gv = findViewById(R.id.park_cont);
        gv.setAdapter(new ParkingAdapter(this, parkCount, ps, curStat));
        fab.performClick();
    }

    @SuppressLint("StaticFieldLeak")
    class DoUpdate extends AsyncTask<View, Void, Void> {
        private char new_stat[];
        private String timeUpd;
        private View view;
        private boolean success = false;

        @Override
        protected Void doInBackground(View... views) {
            view = views[0];
            try {
                String webSrc = wp.getNewState();
                Log.i("OUT_AVEE", "doInBackground: \'" + webSrc + "\'");
                if (webSrc.startsWith("OK")) {
                    new_stat = webSrc.substring(resIndStart, resIndEnd).toCharArray();
                    timeUpd = webSrc.substring(timeIndStart, timeIndEnd);
                    success = true;
                }
            } catch (IOException e) {
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (success) {
                for (int i = 0; i < parkCount; i++) {
                    if (!curStat[i] && (new_stat[i] == '1')) {
                        curStat[i] = true;
                        ps[i].setFree(true);
                    } else if (curStat[i] && (new_stat[i] == '0')) {
                        curStat[i] = false;
                        ps[i].setFree(false);
                    }
                }
                Snackbar.make(view, "Updated at " + timeUpd, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            } else {
                Snackbar.make(view, "Error: Cannot Fetch data!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        }
    }
}
