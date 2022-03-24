package com.dhbw.strand_pepperstudies_studien.activities;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.GoToBuilder;
import com.aldebaran.qi.sdk.builder.TransformBuilder;
import com.aldebaran.qi.sdk.object.actuation.Actuation;
import com.aldebaran.qi.sdk.object.actuation.Frame;
import com.aldebaran.qi.sdk.object.actuation.FreeFrame;
import com.aldebaran.qi.sdk.object.actuation.GoTo;
import com.aldebaran.qi.sdk.object.actuation.Mapping;
import com.aldebaran.qi.sdk.object.geometry.Transform;

public class MoveActivity {

    private static final String TAG = "PepperStudies_MoveActivity";
    QiContext qiContext;

    Actuation actuation;
    Frame robotFrame;
    Transform transform;
    Mapping mapping;
    FreeFrame targetFrame;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void MoveForward() {
        new Thread(() -> {
            if (qiContext != null) {
                actuation = qiContext.getActuation();
                robotFrame = actuation.robotFrame();
                transform = TransformBuilder.create().fromXTranslation(1);
                mapping = qiContext.getMapping();
                targetFrame = mapping.makeFreeFrame();
                targetFrame.update(robotFrame, transform, 0L);

                GoTo goTo = GoToBuilder.with(qiContext)
                        .withFrame(targetFrame.frame())
                        .build();
                goTo.async().run();
                Log.i(TAG, "I walked forward");
            } else {
                Log.i(TAG, "qiContext is null in MoveActivity");
            }
        }).start();
    }
}
