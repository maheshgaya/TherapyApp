package com.example.kelly.mmelk.data;

import android.content.Context;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Mahesh Gaya on 12/13/16.
 */

public class AxisFormatter implements IAxisValueFormatter {
    private Context mContext;
    private String[] mDateArray;

    public AxisFormatter(Context context, String[] dateArray){
        mContext = context;
        mDateArray = dateArray;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mDateArray[(int)value];
    }
}
