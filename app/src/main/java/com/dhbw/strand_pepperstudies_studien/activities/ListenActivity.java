package com.dhbw.strand_pepperstudies_studien.activities;

import android.util.Log;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.ListenBuilder;
import com.aldebaran.qi.sdk.builder.PhraseSetBuilder;
import com.aldebaran.qi.sdk.object.conversation.Listen;
import com.aldebaran.qi.sdk.object.conversation.ListenResult;
import com.aldebaran.qi.sdk.object.conversation.PhraseSet;

public class ListenActivity {

    private static final String TAG = "PepperStudies_ListenActivity";
    QiContext qiContext;

    Future<ListenResult> resultFuture;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public Future<ListenResult> startListenFunction()
    {
        new Thread(() -> {
            if (qiContext != null) {

                // Create a phrase set.
                PhraseSet phraseSet = PhraseSetBuilder.with(qiContext)
                        .withTexts("Hello", "Hi")
                        .build();

                Listen listen = ListenBuilder.with(qiContext)
                        .withPhraseSet(phraseSet)
                        .build();

                resultFuture = listen.async().run();
                //TODO: hier muss gewartet werden sonst macht er weiter und resultfuture ist null
            } else {
                Log.i(TAG, "qiContext is null in ListenActivity");
            }
        }).start();
        Log.i(TAG, resultFuture.getValue().getHeardPhrase().getText());
        return resultFuture;
    }
}
