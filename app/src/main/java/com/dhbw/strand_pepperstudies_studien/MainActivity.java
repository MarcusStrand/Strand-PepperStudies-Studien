package com.dhbw.strand_pepperstudies_studien;

import android.util.*;
import android.os.Bundle;
import android.widget.Button;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.dhbw.strand_pepperstudies_studien.activities.AnimationActivity;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_MainActivity";
    private QiContext qiContext;
    SayActivity sayActivity;
    AnimationActivity animationActivity;
    Button button_sayHi;
    Button button_doAnimation;

    Animation animation;

    // Android Lifecycle Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QiSDK.register(this, this);

        initializeButtonsAndOnClickListeners();

        sayActivity = new SayActivity();
        animationActivity = new AnimationActivity();
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

    public void initializeButtonsAndOnClickListeners()
    {
        button_sayHi = findViewById(R.id.button_SayHi);
        button_sayHi.setOnClickListener(v -> {
            if (qiContext != null) {
                sayActivity.SaySomething("It is working");
            }
        });

        /*
        button_doAnimation = findViewById(R.id.button_Animate);
        button_doAnimation.setOnClickListener(v -> {
            if (qiContext != null) {
                animationActivity.doAnimation(animation);
            }
        });
        */
    }
}



