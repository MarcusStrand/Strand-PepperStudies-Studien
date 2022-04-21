package com.dhbw.strand_pepperstudies_studien.fragments;

import android.util.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_HomeFragment";
    View view;
    SayActivity sayActivity;
    QiContext qiContext;

    Button button_HomeExplanation;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sayActivity = new SayActivity();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity ma = (MainActivity) getActivity();
        this.qiContext = ma.getQiContext();

        initializeButtonsAndOnClickListeners();
        return view;
    }

    // Robot Lifecycle Callbacks

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    @Override
    public void onRobotFocusLost() {

    }

    @Override
    public void onRobotFocusRefused(String reason) {
    }

    public void initializeButtonsAndOnClickListeners()
    {
        if(qiContext != null)
        {
        button_HomeExplanation = view.findViewById(R.id.button_Explanation);
        button_HomeExplanation.setOnClickListener(v -> {
            if (qiContext != null) {
                sayActivity.setQiContext(this.qiContext);
                sayActivity.SaySomething("test");
            }
        });
        }
        else
        {
            Log.i(TAG,"QiContext is null!");
        }
    }
}