package com.dhbw.strand_pepperstudies_studien.activities;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.dhbw.strand_pepperstudies_studien.R;

public class AnimationActivity {

    private static final String TAG = "PepperStudies_AnimationActivity";
    QiContext qiContext;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void doAnimation()
    {
        new Thread(() -> {
            if (qiContext != null) {

                Animation animation = AnimationBuilder.with(qiContext)
                        .withResources(R.raw.animation_demo)
                        .build();

                Animate animate = AnimateBuilder.with(qiContext)
                        .withAnimation(animation)
                        .build();

                animate.async().run();
                Log.i(TAG, "I moved myself");
            } else {
                Log.i(TAG, "qiContext is null in AnimationActivity");
            }
        }).start();
    }
}
