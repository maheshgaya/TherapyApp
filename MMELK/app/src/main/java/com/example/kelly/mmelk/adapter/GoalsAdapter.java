package com.example.kelly.mmelk.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.Utilities;
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
        String imageStr = cursor.getString(Constants.COLUMN_GOALS_INNER_ACTIVITIES_PICTURE);

        viewHolder.titleTextView.setText(title);
        viewHolder.durationTextView.setText(duration);
        viewHolder.freqTextView.setText(frequency);
        viewHolder.iconImageButton.setImageResource(Utilities.getRes(imageStr, mContext));
        viewHolder.editContextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.edit_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_edit:{
                                //TODO: edit item
                                return true;
                            }
                            case R.id.action_delete:{
                                //TODO: delete item
                                return true;
                            }
                            default:
                                return false;
                        }
                    };
                });
            }
        });

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
        @BindView(R.id.goals_icon_imagebutton)ImageButton iconImageButton;
        @BindView(R.id.goals_context_imageButton)ImageButton editContextImageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

