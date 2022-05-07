package com.dhbw.strand_pepperstudies_studien.activities;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;

import com.aldebaran.qi.sdk.object.human.Human;
import com.aldebaran.qi.sdk.object.humanawareness.HumanAwareness;

public class HumanActivity {

    private static final String TAG = "PepperStudies_HumanActivity";
    QiContext qiContext;
    HumanAwareness humanAwareness;
    Human engagedHuman;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public Human startHumanActivity() {
        new Thread(() -> {
            if (qiContext != null) {
                humanAwareness = qiContext.getHumanAwareness();
                engagedHuman = humanAwareness.getEngagedHuman();
            } else {
                Log.i(TAG, "qiContext is null in HumanActivity");
            }
        }).start();
        Log.i(TAG, "HumanActivity successfully started.");
        return engagedHuman;
    }
}