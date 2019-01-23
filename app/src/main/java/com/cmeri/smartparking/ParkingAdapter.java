/*
 *      Created By: Aveepsit Chowdhury
 *      File Name: ParkingAdapter
 *      Project Name: CMERI-Smart-Parking
 *      Created on: 23-01-2019 05:48 PM
 *      Additional Notes:
 */

package com.cmeri.smartparking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ParkingAdapter extends BaseAdapter {
    private Context context;
    private int parkCount;
    private ParkingSpot ps[];
    private boolean stat[];

    ParkingAdapter(Context contextL, int parkCountL, ParkingSpot p[], boolean b[]) {
        context = contextL;
        parkCount = parkCountL;
        ps = p;
        stat = b;
    }

    @Override
    public int getCount() {
        return parkCount;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int ind) {
        return ps[ind].getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ps[i] = new ParkingSpot(context, i);
        ps[i].setFree(stat[i]);
        return ps[i];
    }
}
