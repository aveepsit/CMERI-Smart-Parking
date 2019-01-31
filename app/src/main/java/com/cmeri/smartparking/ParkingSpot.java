/*
 *      Created By: Aveepsit Chowdhury
 *      File Name: ParkingSpot
 *      Project Name: CMERI-Smart-Parking
 *      Created on: 23-01-2019 06:00 PM
 *      Additional Notes:
 */

package com.cmeri.smartparking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.Gravity;

@SuppressLint("ViewConstructor")
public class ParkingSpot extends AppCompatTextView {
    public static int edgeSize = 100;
    public static int fontSize = 24;

    private boolean state;

    @SuppressLint("SetTextI18n")
    public ParkingSpot(Context context, int pos) {
        super(context);
        setTypeface(null, Typeface.BOLD);
        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        setText("S" + String.valueOf(pos));
        setFocusable(false);
        setClickable(false);
        setWidth(edgeSize);
        setHeight(edgeSize);
    }

    boolean isOccupied() {
        return state;
    }

    void setOccupied(boolean b) {
        state = b;
        if (b) {
            setBackgroundResource(R.color.occ_grad_start);
        } else {
            setBackgroundResource(R.color.free_grad_start);
        }
    }
}
