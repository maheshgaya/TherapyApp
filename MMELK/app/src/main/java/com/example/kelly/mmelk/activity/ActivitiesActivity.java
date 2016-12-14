package com.example.kelly.mmelk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kelly.mmelk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh Gaya on 12/8/16.
 */

public class ActivitiesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar; //toolbar

    /**
     * setups the toolbar
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_activities);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar); //setup the toolbar
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
