package com.example.kelly.mmelk.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.maheshgaya.android.common.CursorRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/12/16.
 */

public class GoalsAdapter  extends CursorRecyclerViewAdapter<GoalsAdapter.ViewHolder> {
    private Context mContext;
    public GoalsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(Constants.COLUMN_GOALS_INNER_ACTIVITIES_NAME);
        String duration = cursor.getString(Constants.COLUMN_GOALS_INNER_ACTIVITIES_DURATION);
        String frequency = cursor.getString(Constants.COLUMN_GOALS_INNER_ACTIVITIES_FREQUENCY);

        viewHolder.titleTextView.setText(title);
        viewHolder.durationTextView.setText(duration);
        viewHolder.freqTextView.setText(frequency);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycleview_goals, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.goals_title_textview)TextView titleTextView;
        @BindView(R.id.goals_duration_textview)TextView durationTextView;
        @BindView(R.id.goals_freq_textview)TextView freqTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

