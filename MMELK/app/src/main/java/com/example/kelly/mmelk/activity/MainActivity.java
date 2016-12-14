package com.example.kelly.mmelk.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.data.ActivitiesContract;
import com.example.kelly.mmelk.data.DataService;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maheshgaya.android.common.ViewPagerAdapter;
import com.example.kelly.mmelk.fragment.GoalFragment;
import com.example.kelly.mmelk.fragment.PointFragment;

import java.util.Arrays;

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
    @BindView(R.id.main_coordinator_layout)CoordinatorLayout mCoordinatorLayout;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseAuth mFirebaseAuth;

    private static final int WELCOME = 1;

    private String mUsername;

    private static final int ACTIVITIES_REQUEST_CODE = 101;
    public static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar); //setup the toolbar

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // already signed in
                    mUsername = user.getDisplayName();
                } else {
                    // user signed out
                    mUsername = "";
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .setTheme(R.style.AuthTheme)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


        initializeDatabase(); //initializes the database if it is empty

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
                Intent activitiesIntent = new Intent(getApplicationContext(), ActivitiesActivity.class);
                //startActivityForResult(activitiesIntent, ACTIVITIES_REQUEST_CODE);
                startActivity(activitiesIntent);
            }
        });
    }
    public void showSnackBar(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();

    }

    public void showSnackBar(String message, int category){
        String prefixMessage = "";
        if (category == WELCOME){
            prefixMessage = getString(R.string.welcome_message);
        }
        Snackbar.make(mCoordinatorLayout, prefixMessage + " " + message, Snackbar.LENGTH_LONG).show();
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

    /**
     * onCreateOptionsMenu
     * @param menu
     * @return true
     * inflates the menu for MainActivity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    /**
     * onOptionsItemSelected
     * @param item
     * @return the item selected
     * if action_settings pressed
     *      then open settings activity
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.pref_general.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            //sign out
            showSnackBar(getString(R.string.goodbye_message));
            AuthUI.getInstance().signOut(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                showSnackBar(mUsername, WELCOME);
            } else if (resultCode == RESULT_CANCELED){
                finish();
            }
        }

    }
}
