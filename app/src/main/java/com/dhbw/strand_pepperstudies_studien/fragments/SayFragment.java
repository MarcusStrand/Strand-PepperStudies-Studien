package com.dhbw.strand_pepperstudies_studien.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

public class SayFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_SayFragment";
    QiContext qiContext;
    View view;

    Button button_Explanation;
    Button button_Say;

    SayActivity sayActivity;

    // Android Lifecycle Callbacks

    public SayFragment() {
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
        view = inflater.inflate(R.layout.fragment_say, container, false);
        MainActivity ma = (MainActivity) getActivity();
        this.qiContext = ma.getQiContext();

        initializeButtonsAndOnClickListeners();
        return view;
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    @Override
    public void onRobotFocusLost() {
        Log.i(TAG, "Robot Focus lost " + TAG);
        this.qiContext = null;
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        Log.i(TAG, "Robot focus refused because " + reason +  " " + TAG);
    }

    public void initializeButtonsAndOnClickListeners()
    {
        if(qiContext != null)
        {
            button_Explanation = view.findViewById(R.id.button_Explanation);
            button_Say = view.findViewById(R.id.button_Say);

            button_Explanation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("This button should explain the say screen to you, but it does not");
                }
            });

            button_Say.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("She sells sea shells by the sea shore.");
                }
            });
        }
        else
        {
            Log.i(TAG,"QiContext is null! " + TAG);
        }
    }

}