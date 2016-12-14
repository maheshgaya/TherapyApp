package com.example.kelly.mmelk.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;



import android.widget.TextView;


import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.adapter.ActivitiesAdapter;


import com.example.kelly.mmelk.data.ActivitiesContract;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.kelly.mmelk.adapter.GoalsAdapter;
import com.example.kelly.mmelk.data.ActivitiesContract;


import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class GoalFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int GOAL_LOADER = 0;
    private static final int GOAL_ACTIVITIES_LOADER = 0;
    private static final int ACTIVITIES_LOADER = 1;

    private Cursor mActivitiesCursor;

    @BindView(R.id.empty_goals_recycleview)TextView mEmptyRecycleView;
    @BindView(R.id.goals_recycleview) RecyclerView mRecycleView;
    private GoalsAdapter mGoalsActivitiesAdapter;


    public GoalFragment(){
        //required default constructor
    }

    /**
     * initialize the loaders
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(GOAL_ACTIVITIES_LOADER, null, this);
        getLoaderManager().initLoader(ACTIVITIES_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * sets retainInstance to true
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    /**
     * close open cursors
     */
    @Override
    public void onDestroy() {
        //close cursors to avoid memory leak
        if (mActivitiesCursor != null){
            mActivitiesCursor.close();
        }
        super.onDestroy();
    }

    /**
     * configures the recycle view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goals, container, false);
        ButterKnife.bind(this, rootView);
        mGoalsActivitiesAdapter = new GoalsAdapter(getContext(), null);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearTrailerLayoutManager = new LinearLayoutManager(getContext());
        linearTrailerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearTrailerLayoutManager);
        mRecycleView.setAdapter(mGoalsActivitiesAdapter);

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
            case GOAL_ACTIVITIES_LOADER:{
                Uri uri = ActivitiesContract.GoalEntry.buildGoalsActivities();
                return new CursorLoader(getActivity(),
                        uri,
                        Constants.GOALS_ACTIVITIES_PROJECTION,
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
            case GOAL_ACTIVITIES_LOADER:{
                mGoalsActivitiesAdapter.swapCursor(data);
                if (data.getCount()  > 0){
                    mRecycleView.setVisibility(View.VISIBLE);
                    mEmptyRecycleView.setVisibility(View.GONE);

                } else {
                    mRecycleView.setVisibility(View.GONE);
                    mEmptyRecycleView.setVisibility(View.VISIBLE);
                }
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
        mGoalsActivitiesAdapter.swapCursor(null);
    }
}
