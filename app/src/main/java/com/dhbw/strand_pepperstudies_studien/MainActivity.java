package com.dhbw.strand_pepperstudies_studien;

import android.util.*;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.dhbw.strand_pepperstudies_studien.activities.*;
import com.dhbw.strand_pepperstudies_studien.fragments.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_MainActivity";
    private QiContext qiContext;
    BottomNavigationView bottomNavigationView;

    Fragment homeFragment;
    Fragment sayFragment;
    Fragment moveFragment;
    Fragment animationFragment;
    Fragment welcomeFragment;

    SayActivity sayActivity;
    AnimationActivity animationActivity;
    MoveActivity moveActivity;

    // Android Lifecycle Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QiSDK.register(this, this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

        homeFragment = new HomeFragment();
        sayFragment = new SayFragment();
        moveFragment = new MoveFragment();
        animationFragment = new AnimationFragment();
        welcomeFragment = new WelcomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, welcomeFragment).commit();

        sayActivity = new SayActivity();
        animationActivity = new AnimationActivity();
        moveActivity = new MoveActivity();
        Log.i(TAG, "onCreate done!");
    }

    @Override
    protected void onDestroy() {
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    // Robot Lifecycle Callbacks

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        Log.i(TAG, "Robot Focus gained " + TAG);
        this.qiContext = qiContext;

        sayActivity.setQiContext(qiContext);
        animationActivity.setQiContext(qiContext);
        moveActivity.setQiContext(qiContext);
    }

    @Override
    public void onRobotFocusLost() {
        Log.i(TAG, "Robot Focus lost " + TAG);
        this.qiContext = null;
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        Log.i(TAG, "Robot focus refused because " + reason + " " + TAG);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, homeFragment).commit();
                sayActivity.SaySomething("Showing home screen");
                return true;

            case R.id.ic_say:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, sayFragment).commit();
                sayActivity.SaySomething("Showing say activity");
                return true;

            case R.id.ic_move:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, moveFragment).commit();
                sayActivity.SaySomething("Showing move activity");
                return true;

            case R.id.ic_animation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, animationFragment).commit();
                sayActivity.SaySomething("Showing animation activity");
                return true;
        }
        return false;
    }

    public QiContext getQiContext() {
        return this.qiContext;
    }
}



