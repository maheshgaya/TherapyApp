package com.example.kelly.mmelk.adapter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;
import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.Utilities;
import com.example.kelly.mmelk.activity.ActivitiesActivity;
import com.example.kelly.mmelk.activity.ActivitiesDetailActivity;
import com.example.kelly.mmelk.fragment.ActivitiesDetailFragment;
import com.maheshgaya.android.common.CursorRecyclerViewAdapter;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/7/16.
 */

public class ActivitiesAdapter extends CursorRecyclerViewAdapter<ActivitiesAdapter.ViewHolder> {
    private Context mContext;
    private static ArrayList<String> headers;
    private static final String TAG = ActivitiesAdapter.class.getSimpleName();
    public static Activity mActivity;

    /**
     * constructor
     * @param context
     * @param cursor
     */
    public ActivitiesAdapter(Context context, Cursor cursor){
        super(context, cursor);
        mContext = context;
        headers = new ArrayList<String>(){};
    }

    /**
     * set data to UI
     * @param viewHolder
     * @param cursor
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        final String activityId = String.valueOf(cursor.getLong(Constants.COLUMN_ACTIVITIES_ID));

        String activityName = cursor.getString(Constants.COLUMN_ACTIVITIES_NAME);
        viewHolder.activitiesNameTextView.setText(activityName);

        String imageStr = cursor.getString(Constants.COLUMN_ACTIVITIES_PICTURE);
        viewHolder.iconImageButton.setImageResource(Utilities.getRes(imageStr, mContext));

        String category = cursor.getString(Constants.COLUMN_ACTIVITIES_CATEGORY);
        if (!headers.contains(category) || headers.isEmpty()) {
            headers.add(category);
            if (headers.size() > 1){
                viewHolder.lineViewDivider.setVisibility(View.VISIBLE);
            }
            viewHolder.headerTextView.setVisibility(View.VISIBLE);
            viewHolder.headerTextView.setText(category);

        } else {
            viewHolder.headerTextView.setVisibility(View.GONE);
            viewHolder.lineViewDivider.setVisibility(View.GONE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivitiesDetailActivity.class);
                intent.putExtra(ActivitiesDetailFragment.ACTIVITIES_EXTRA, activityId);
                mContext.startActivity(intent);

                mActivity = (ActivitiesActivity) mContext;

            }
        });
    }

    /**
     * initializes the view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycleview_select_activities, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * template for activities item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.activities_name_textview)TextView activitiesNameTextView;
        @BindView(R.id.activities_header_textview)TextView headerTextView;
        @BindView(R.id.activities_icon_imagebutton)ImageButton iconImageButton;
        @BindView(R.id.line_divider)View lineViewDivider;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
