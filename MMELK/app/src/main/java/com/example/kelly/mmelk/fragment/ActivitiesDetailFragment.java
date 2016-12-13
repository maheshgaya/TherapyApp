package com.example.kelly.mmelk.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.Utilities;
import com.example.kelly.mmelk.activity.ActivitiesActivity;
import com.example.kelly.mmelk.adapter.ActivitiesAdapter;
import com.example.kelly.mmelk.data.ActivitiesContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/8/16.
 */

public class ActivitiesDetailFragment extends Fragment {
    public static final String ACTIVITIES_EXTRA = "act_goal";
    @BindView(R.id.activities_details_duration_spinner)Spinner mDurationSpinner;
    @BindView(R.id.activities_details_freq_spinner)Spinner mFreqSpinner;
    @BindView(R.id.activities_details_add_goal_button)Button mAddGoalBtn;
    @BindView(R.id.activities_details_imageview)ImageView mImageView;
    @BindView(R.id.activities_details_title_textview)TextView mTitleTextView;

    private String mDuration;
    private String mFrequency;

    /**
     * retains the instance
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details_activities, container, false);
        ButterKnife.bind(this, rootView);
        Intent callingIntent = getActivity().getIntent();
        final String activitiesId = callingIntent.getStringExtra(ACTIVITIES_EXTRA);


        Cursor activitiesCursor = getActivity().getContentResolver().query(
                ActivitiesContract.ActivityEntry.CONTENT_URI,
                Constants.ACTIVITIES_PROJECTION,
                ActivitiesContract.ActivityEntry._ID + " = ? ",
                new String[]{activitiesId},
                null
        );

        try {
            if (activitiesCursor.moveToFirst()){
                String title = activitiesCursor.getString(Constants.COLUMN_ACTIVITIES_NAME);
                mTitleTextView.setText(title);
                String imageStr = activitiesCursor.getString(Constants.COLUMN_ACTIVITIES_PICTURE);
                mImageView.setImageResource(Utilities.getRes(imageStr, getContext()));
            }
        }finally {
            activitiesCursor.close();
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.duration, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mDurationSpinner.setAdapter(spinnerAdapter);
        mDurationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDuration = parent.getItemAtPosition(position).toString();
                if (mDuration.equals(getString(R.string.spinner_hint)) || mFrequency.equals(getString(R.string.spinner_hint))) {
                    mAddGoalBtn.setEnabled(false);
                } else {
                    mAddGoalBtn.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> freqAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.frequency, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mFreqSpinner.setAdapter(freqAdapter);
        mFreqSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mFrequency = parent.getItemAtPosition(position).toString();
                if (mDuration.equals(getString(R.string.spinner_hint)) || mFrequency.equals(getString(R.string.spinner_hint))) {
                    mAddGoalBtn.setEnabled(false);
                } else {
                    mAddGoalBtn.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mAddGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDatabase(Long.parseLong(activitiesId));
            }
        });
        return rootView;
    }

    private void addToDatabase(long activitiesId){
        //prepare to insert into database
        ContentValues goalsValues = new ContentValues();
        goalsValues.put(ActivitiesContract.GoalEntry.COLUMN_ACTIVITIES_ID, activitiesId);
        goalsValues.put(ActivitiesContract.GoalEntry.COLUMN_DURATION, mDuration);
        goalsValues.put(ActivitiesContract.GoalEntry.COLUMN_FREQUENCY, mFrequency);
        //Insert into database
        getActivity().getContentResolver().insert(
                ActivitiesContract.GoalEntry.CONTENT_URI,
                goalsValues
        );
        ActivitiesAdapter.mActivity.finish();
        getActivity().finish(); //end the activity
    }
}
