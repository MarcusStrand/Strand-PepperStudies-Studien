package com.dhbw.strand_pepperstudies_studien.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhbw.strand_pepperstudies_studien.R;

public class WelcomeFragment extends Fragment {

    private static final String TAG = "PepperStudies_WelcomeFragment";

    // Android Lifecycle Callbacks

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "WelcomeFragment successfully created!");
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }
}