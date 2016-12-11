package com.example.kelly.mmelk.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.kelly.mmelk.R;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class GoalFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    HashMap<String,List<String>> Activities_category;
    List<String> Activities_list;
    ExpandableListView Exp_list;
    public GoalFragment(){
        //required default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goals, container, false);
        ButterKnife.bind(this, rootView);
        //ExpandableListView expandable = new ExpandableListView(rootView.getContext(),)

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
