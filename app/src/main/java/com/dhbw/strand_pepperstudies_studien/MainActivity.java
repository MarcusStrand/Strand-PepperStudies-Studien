package com.dhbw.strand_pepperstudies_studien;

import android.util.*;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.dhbw.strand_pepperstudies_studien.activities.*;
import com.dhbw.strand_pepperstudies_studien.fragments.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_MainActivity";
    private QiContext qiContext;
    MainActivity mainActivity;
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    Fragment homeFragment;
    Fragment sayFragment;
    Fragment moveFragment;
    Fragment animationFragment;

    SayActivity sayActivity;
    AnimationActivity animationActivity;
    MoveActivity moveActivity;

    // Android Lifecycle Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QiSDK.register(this, this);
        this.fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

        homeFragment = new HomeFragment();
        sayFragment = new SayFragment();
        moveFragment = new MoveFragment();
        animationFragment = new AnimationFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, homeFragment).commit();

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
        Log.i(TAG, "Robot Focus gained");
        this.qiContext = qiContext;
        sayActivity.setQiContext(qiContext);
        animationActivity.setQiContext(qiContext);
        moveActivity.setQiContext(qiContext);
    }

    @Override
    public void onRobotFocusLost() {
        Log.i(TAG, "Robot Focus lost");
        this.qiContext = null;
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        Log.i(TAG, "Robot focus refused because " + reason);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, homeFragment).commit();
                return true;

            case R.id.ic_say:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, sayFragment).commit();
                return true;

            case R.id.ic_move:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, moveFragment).commit();
                return true;

            case R.id.ic_animation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, animationFragment).commit();
                return true;
        }
        return false;
    }
}



