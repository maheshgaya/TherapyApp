package com.example.kelly.mmelk.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.data.ActivitiesContract;
import com.example.kelly.mmelk.data.DataService;
import com.maheshgaya.android.common.ViewPagerAdapter;
import com.example.kelly.mmelk.fragment.GoalFragment;
import com.example.kelly.mmelk.fragment.PointFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Tutorial: <a href="http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/">Android Hive</a>
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindString(R.string.label_goal) String mGoalLabel;
    @BindString(R.string.label_point) String mPointLabel;

    @BindView(R.id.toolbar) Toolbar mToolbar; //toolbar
    @BindView(R.id.tabs) TabLayout mTabLayout; //tabs
    @BindView(R.id.viewpager) ViewPager mViewPager; //page content
    @BindView(R.id.fab) FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar); //setup the toolbar

        initializeDatabase(); //intializes the database if it is empty

        //handles tabs and fragments
        setupViewPage(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

        //add and remove FAB according to page selected
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: {
                        mFab.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 1: {
                        mFab.setVisibility(View.GONE);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: This should open the Add Activity Page
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Setup the tabs in the layout
     * @param viewPager
     */
    private void setupViewPage(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        adapter.addFrag(new GoalFragment(), mGoalLabel);
        adapter.addFrag(new PointFragment(), mPointLabel);
        viewPager.setAdapter(adapter);
    }

    /**
     * Setup icons
     */
    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.ic_walking,
                R.drawable.ic_graph
        };

        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);

    }

    private void initializeDatabase(){
        Cursor activitiesCursor = this.getContentResolver().query(
                ActivitiesContract.ActivityEntry.CONTENT_URI,
                Constants.ACTIVITIES_PROJECTION,
                null,
                null,
                null
        );
        try {
            if (activitiesCursor.getCount() == 0) {
                Log.d(TAG, "initializeDatabase: database is being initialized.");
                Intent dataIntent = new Intent(this, DataService.class);
                startService(dataIntent);
            }
        } finally {
            activitiesCursor.close();
        }
    }
}
