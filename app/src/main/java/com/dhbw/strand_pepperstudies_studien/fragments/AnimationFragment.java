package com.dhbw.strand_pepperstudies_studien.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldebaran.qi.sdk.QiContext;
import com.dhbw.strand_pepperstudies_studien.R;

public class AnimationFragment extends Fragment {

    private static final String TAG = "PepperStudies_AnimationFragment";
    QiContext qiContext;

    public AnimationFragment() {
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

        return inflater.inflate(R.layout.fragment_animation, container, false);
    }
}