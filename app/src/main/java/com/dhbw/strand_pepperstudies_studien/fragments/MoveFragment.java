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
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.MoveActivity;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

public class MoveFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_MoveFragment";
    QiContext qiContext;
    View view;

    Button button_Explanation;
    Button button_MoveForward;

    SayActivity sayActivity;
    MoveActivity moveActivity;


    // Android Lifecycle Callbacks

    public MoveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sayActivity = new SayActivity();
        moveActivity = new MoveActivity();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_move, container, false);
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
        Log.i(TAG, "Robot Focus lost " + TAG);
        this.qiContext = null;
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        Log.i(TAG, "Robot focus refused because " + reason +  " " + TAG);
    }


    //Custom Methods

    public void initializeButtonsAndOnClickListeners()
    {
        if(qiContext != null)
        {
            button_Explanation = view.findViewById(R.id.button_Explanation);
            button_MoveForward = view.findViewById(R.id.button_MoveForward);

            button_Explanation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("This button should explain the move screen to you, but it does not");
                }
            });

            button_MoveForward.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    moveActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("Be careful, I will now move forward.");
                    moveActivity.MoveForward();
                }
            });
        }
        else
        {
            Log.i(TAG,"QiContext is null! " + TAG);
        }
    }
}

