package com.example.kelly.mmelk.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.maheshgaya.android.common.CursorRecyclerViewAdapter;

/**
 * Created by Mahesh Gaya on 12/7/16.
 */

public class ActivityAdapter extends CursorRecyclerViewAdapter<ActivityAdapter.ViewHolder> {
    private Context mContext;

    public ActivityAdapter(Context context, Cursor cursor){
        super(context, cursor);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
