package com.example.kelly.mmelk.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.adapter.ActivitiesAdapter;
import com.example.kelly.mmelk.data.ActivitiesContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/8/16.
 */

public class ActivitiesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    @BindView(R.id.activities_recycleview)RecyclerView mRecycleView;
    @BindView(R.id.empty_activities_recycleview)TextView emptyTextView;

    private ActivitiesAdapter mActivitiesAdapter;
    private static final int ACTIVITIES_LOADER = 0;

    /**
     * retains the instance
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * creates and initializes the views
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_activities, container, false);
        ButterKnife.bind(this, rootView);
        mActivitiesAdapter = new ActivitiesAdapter(getContext(), null);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearTrailerLayoutManager = new LinearLayoutManager(getContext());
        linearTrailerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearTrailerLayoutManager);
        mRecycleView.setAdapter(mActivitiesAdapter);
        return rootView;
    }

    /**
     * initializes the cursor loader
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(ACTIVITIES_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * queries the database
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                ActivitiesContract.ActivityEntry.CONTENT_URI,
                Constants.ACTIVITIES_PROJECTION,
                null,
                null,
                ActivitiesContract.ActivityEntry.COLUMN_CATEGORY + " ASC");
    }

    /**
     * swaps the cursor ultimately update the UI
     * @param loader
     * @param cursor
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mActivitiesAdapter.swapCursor(cursor);
        if (cursor.getCount() == 0){
            mRecycleView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.GONE);
            mRecycleView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * resets the adapter
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mActivitiesAdapter.swapCursor(null);
    }
}
