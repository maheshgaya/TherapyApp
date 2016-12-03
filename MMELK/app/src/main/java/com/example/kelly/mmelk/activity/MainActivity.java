package com.example.kelly.mmelk.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.adapter.ViewPagerAdapter;
import com.example.kelly.mmelk.fragment.GoalFragment;
import com.example.kelly.mmelk.fragment.PointFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Tutorial: <a href="http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/">Android Hive</a>
 */
public class MainActivity extends AppCompatActivity {

    @BindString(R.string.label_goal) String mGoalLabel;
    @BindString(R.string.label_point) String mPointLabel;

    @BindView(R.id.toolbar) Toolbar mToolbar; //toolbar
    @BindView(R.id.tabs) TabLayout mTabLayout; //tabs
    @BindView(R.id.viewpager) ViewPager mViewPager; //page content

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        //handles tabs and fragments
        setupViewPage(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    /**
     * Setup the tabs in the layout
     * @param viewPager
     */
    private void setupViewPage(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new GoalFragment(), mGoalLabel);
        adapter.addFrag(new PointFragment(), mPointLabel);
        viewPager.setAdapter(adapter);
    }
}