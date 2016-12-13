package com.example.kelly.mmelk.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.Utilities;
import com.example.kelly.mmelk.activity.QuestionActivity;
import com.example.kelly.mmelk.data.ActivitiesContract;
import com.example.kelly.mmelk.data.AxisFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class PointFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = PointFragment.class.getSimpleName();
    public static final int REQUEST_QUESTION = 1;
    public static final int POINTS_LOADER = 1;
    public static final int ANSWERS_LOADER = 2;
    public static final int MOOD_LOADER = 3;

    @BindView(R.id.add_question_btn)Button mAddQuestionBtn;
    @BindView(R.id.chart)LineChart mLineChart;
    @BindView(R.id.points_textview)TextView mPointsTextView;


    public PointFragment(){
        //required default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(POINTS_LOADER, null, this);
        getLoaderManager().initLoader(ANSWERS_LOADER, null, this);
        getLoaderManager().initLoader(MOOD_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_points, container, false);
        ButterKnife.bind(this, rootView);
        mAddQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivityForResult(intent, REQUEST_QUESTION);
            }
        });

        return rootView;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case  POINTS_LOADER: {
                return new CursorLoader(getActivity(),
                        ActivitiesContract.PointEntry.CONTENT_URI,
                        Constants.POINTS_PROJECTION,
                        null,
                        null,
                        null);
            }
            case ANSWERS_LOADER:{
                String currentTimeStr = Utilities.getCurrentTime();
                return new CursorLoader(getActivity(),
                        ActivitiesContract.AnswerEntry.CONTENT_URI,
                        Constants.ANSWERS_PROJECTION,
                        ActivitiesContract.AnswerEntry.COLUMN_DATE + " = ? ",
                        new String[]{currentTimeStr},
                        null);
            }
            case MOOD_LOADER:{
                return new CursorLoader(getActivity(),
                        ActivitiesContract.AnswerEntry.CONTENT_URI,
                        Constants.ANSWERS_PROJECTION,
                        ActivitiesContract.AnswerEntry.COLUMN_QUESTION_ID + " = ? ",
                        new String[]{"2"},
                        null);
            }
            default:
                return null;
        }


    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()){
            case POINTS_LOADER:{
                int pointsSum = 0;
                while (data.moveToNext()){
                    pointsSum += data.getInt(Constants.COLUMN_POINTS_POINT);
                }
                mPointsTextView.setText(String.valueOf(pointsSum));
                break;
            }
            case ANSWERS_LOADER:{
                if (data.moveToFirst()){
                    mAddQuestionBtn.setVisibility(View.GONE);
                    Log.d(TAG, "onLoadFinished: Visibily - GONE ");
                } else {
                    mAddQuestionBtn.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onLoadFinished: Visibily - VISIBLE ");
                }
                break;
            }
            case MOOD_LOADER:{
                Map<String, String> moods = new HashMap<String, String>();
                ArrayList<String> dateXAxisValues = new ArrayList<String>(){};
                while (data.moveToNext()){
                    //X - date ,Y - moods
                    moods.put(data.getString(Constants.COLUMN_ANSWERS_DATE),
                            data.getString(Constants.COLUMN_ANSWERS_ANSWER));
                    dateXAxisValues.add(data.getString(Constants.COLUMN_ANSWERS_DATE));
                }

                String[] dateArray = dateXAxisValues.toArray(new String[dateXAxisValues.size()]);
                //add date to x Axis
                AxisFormatter formatter = new AxisFormatter(getContext(), dateArray);

                AxisBase xAxis = mLineChart.getXAxis();
                xAxis.setAxisMinimum(0);
                xAxis.setGranularity(2f); // minimum axis-step (interval) is 2
                xAxis.setValueFormatter(formatter);

                AxisBase leftAxis = mLineChart.getAxisLeft();
                leftAxis.setAxisMinimum(0);
                leftAxis.setAxisMaximum(5);

                AxisBase rightAxis = mLineChart.getAxisRight();
                rightAxis.setAxisMinimum(0);
                rightAxis.setAxisMaximum(5);

                List<Entry> moodEntries = new ArrayList<Entry>();
                int i = 1;
                for (String key: moods.keySet()){
                    moodEntries.add(new Entry(i, Float.parseFloat(moods.get(key))));
                    i++;
                }

                LineDataSet dataSet = new LineDataSet(moodEntries, "Moods");
                //Customization
                //disable value text for each point
                dataSet.setDrawValues(false);

                //change color for Circle dots
                if (Build.VERSION.SDK_INT > 22) {
                    dataSet.setCircleColor(getResources().getColor(R.color.colorPrimaryDark, null));
                    dataSet.setColor(getResources().getColor(R.color.colorPrimary, null));
                } else {
                    dataSet.setCircleColor(getResources().getColor(R.color.colorPrimaryDark));
                    dataSet.setColor(getResources().getColor(R.color.colorPrimary));
                }

                LineData lineData = new LineData(dataSet);
                mLineChart.setData(lineData);
                Description description = mLineChart.getDescription();
                description.setText(getString(R.string.line_description));
                mLineChart.invalidate(); // refresh

                break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
