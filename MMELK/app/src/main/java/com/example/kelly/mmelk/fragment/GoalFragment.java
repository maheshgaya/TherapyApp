package com.example.kelly.mmelk.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.data.ActivitiesContract;

import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class GoalFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int GOAL_LOADER = 0;
    private static final int ACTIVITIES_LOADER = 1;

    private Cursor mGoalsCursor;
    private Cursor mActivitiesCursor;

    public GoalFragment(){
        //required default constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(GOAL_LOADER, null, this);
        getLoaderManager().initLoader(ACTIVITIES_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        //close cursors to avoid memory leak
        if (mGoalsCursor != null){
            mGoalsCursor.close();
        }
        if (mActivitiesCursor != null){
            mActivitiesCursor.close();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goals, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * This method queries the content provider in a background thread
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            //queries Goals table
            case GOAL_LOADER:{
                return new CursorLoader(getActivity(),
                        ActivitiesContract.GoalEntry.CONTENT_URI,
                        Constants.GOALS_PROJECTION,
                        null,
                        null,
                        null
                );
            }
            //queries Activities table
            case ACTIVITIES_LOADER:{
                return new CursorLoader(getActivity(),
                        ActivitiesContract.ActivityEntry.CONTENT_URI,
                        Constants.ACTIVITIES_PROJECTION,
                        null,
                        null,
                        null
                        );
            }
            default:{
                return null;
            }
        }

    }

    /**
     * The background thread has already queried the content provider
     * Now, update the UI
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()){
            case GOAL_LOADER:{
                mGoalsCursor = data;
                //TODO: update UI for Goals
                break;
            }
            case ACTIVITIES_LOADER:{
                mActivitiesCursor = data;
                //TODO: TASK ASSIGNMENT: @LHITO - update UI for Activities
                //You should initialize the ListView in onCreate() method and then use it here
            }
        }
    }

    /**
     * No need for this.
     * Does nothing
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
