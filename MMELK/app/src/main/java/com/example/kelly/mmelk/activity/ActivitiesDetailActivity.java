package com.example.kelly.mmelk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kelly.mmelk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/8/16.
 */

public class ActivitiesDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar; //toolbar

    /**
     * setup the toolbar
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_details_activities);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar); //setup the toolbar
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
