package com.example.kelly.mmelk.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kelly.mmelk.R;
import com.maheshgaya.android.common.CursorRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/7/16.
 */

public class ActivitiesAdapter extends CursorRecyclerViewAdapter<ActivitiesAdapter.ViewHolder> {
    private Context mContext;

    public ActivitiesAdapter(Context context, Cursor cursor){
        super(context, cursor);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycleview_select_activities, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.activities_name_textview)TextView activitiesNameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
