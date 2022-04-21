package com.dhbw.strand_pepperstudies_studien.activities;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.HolderBuilder;
import com.aldebaran.qi.sdk.object.holder.AutonomousAbilitiesType;
import com.aldebaran.qi.sdk.object.holder.Holder;


public class AutonomousAbilitiesActivity {

    private static final String TAG = "PepperStudies_AutonomousAbilitiesActivity";
    QiContext qiContext;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void TurnOffBackgroundMovement()
    {
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.BACKGROUND_MOVEMENT)
                .build();
        // Hold the ability asynchronously.
        holder.async().hold();
        // Release the ability asynchronously.
        //holder.async().release();
        Log.i(TAG, "Turned off background movement");
    }

    public void TurnOffBasicAwareness()
    {
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.BASIC_AWARENESS)
                .build();
        // Hold the ability asynchronously.
        holder.async().hold();
        // Release the ability asynchronously.
        //holder.async().release();
        Log.i(TAG, "Turned off basic awareness");
    }

    public void TurnOffAutonomousBlinking()
    {
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.AUTONOMOUS_BLINKING)
                .build();
        // Hold the ability asynchronously.
        holder.async().hold();
        // Release the ability asynchronously.
        //holder.async().release();
        Log.i(TAG, "Turned off autonomous blinking");
    }

    public void TurnOnBackgroundMovement()
    {
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.BACKGROUND_MOVEMENT)
                .build();
        // Hold the ability asynchronously.
        //holder.async().hold();
        // Release the ability asynchronously.
        holder.async().release();
        Log.i(TAG, "Turned on background movement");
    }

    public void TurnOnBasicAwareness()
    {
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.BASIC_AWARENESS)
                .build();
        // Hold the ability asynchronously.
        //holder.async().hold();
        // Release the ability asynchronously.
        holder.async().release();
        Log.i(TAG, "Turned on basic awareness");
    }

    public void TurnOnAutonomousBlinking()
    {
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.AUTONOMOUS_BLINKING)
                .build();
        // Hold the ability asynchronously.
        //holder.async().hold();
        // Release the ability asynchronously.
        holder.async().release();
        Log.i(TAG, "Turned on autonomous blinking");
    }

}