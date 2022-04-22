package com.dhbw.strand_pepperstudies_studien.activities;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.ListenBuilder;
import com.aldebaran.qi.sdk.builder.PhraseSetBuilder;
import com.aldebaran.qi.sdk.object.conversation.Listen;
import com.aldebaran.qi.sdk.object.conversation.PhraseSet;

public class ListenActivity {

    private static final String TAG = "PepperStudies_ListenActivity";
    QiContext qiContext;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void ListenFunction()
    {
        // Create a phrase set.
        PhraseSet phraseSet = PhraseSetBuilder.with(qiContext)
                .withTexts("Hello")
                .build();

        // Build the action.
        Listen listen = ListenBuilder.with(qiContext)
                .withPhraseSet(phraseSet)
                .build();

        // Run the action synchronously.
        listen.run();
    }
}
