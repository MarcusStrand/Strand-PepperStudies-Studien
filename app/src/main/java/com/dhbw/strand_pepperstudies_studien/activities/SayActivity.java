package com.dhbw.strand_pepperstudies_studien.activities;

import android.util.Log;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;

public class SayActivity {

    private static final String TAG = "PepperStudies_SayActivity";
    QiContext qiContext;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void SaySomething(String text) {
        new Thread(() -> {
            if (qiContext != null) {
                Say say = SayBuilder.with(qiContext)
                        .withText(text)
                        .build();

                say.async().run();
                Log.i(TAG, "I have said " + text + " successfully!");
            } else {
                Log.i(TAG, "qiContext is null in SayActivity");
            }
        }).start();
    }
}
