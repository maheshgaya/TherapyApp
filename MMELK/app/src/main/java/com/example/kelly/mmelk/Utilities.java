package com.example.kelly.mmelk;

import android.content.Context;

/**
 * Created by Mahesh Gaya on 12/10/16.
 */

public class Utilities {
    public static int getRes(String imageStr, Context context){
        if (imageStr.equals(context.getString(R.string.res_canvas))){
            return R.drawable.canvas;
        } else if(imageStr.equals(context.getString(R.string.res_dumbbell))) {
            return R.drawable.dumbbell;
        } else if (imageStr.equals(context.getString(R.string.res_football))) {
            return R.drawable.football;
        } else if (imageStr.equals(context.getString(R.string.res_lotus_position))) {
            return R.drawable.lotus_position;
        } else if (imageStr.equals(context.getString(R.string.res_meditation))) {
            return R.drawable.meditation;
        } else if (imageStr.equals(context.getString(R.string.res_newspaper))) {
            return R.drawable.newspaper;
        } else if (imageStr.equals(context.getString(R.string.res_phone_call))) {
            return R.drawable.phone_call;
        } else if (imageStr.equals(context.getString(R.string.res_running))) {
            return R.drawable.running;
        } else if (imageStr.equals(context.getString(R.string.res_swimming))) {
            return R.drawable.swimming;
        } else if (imageStr.equals(context.getString(R.string.res_team))){
            return R.drawable.team;
        } else {
            return R.drawable.ic_walking;
        }
    }
}
