/*
 *      Created By: Aveepsit Chowdhury
 *      File Name: Dashboard.java
 *      Project Name: CMERI-Smart-Parking
 *      Created on: 21-01-2019 06:57 PM
 *      Additional Notes:
 */

package com.cmeri.smartparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void openOfficeView(View view) {
        ParkingView.setParking(ParkingView.OFFICE_PARKING);
        startActivity(new Intent(this, ParkingView.class));
    }

    public void openColonyView(View view) {
        ParkingView.setParking(ParkingView.COLONY_PARKING);
        startActivity(new Intent(this, ParkingView.class));
    }
}
