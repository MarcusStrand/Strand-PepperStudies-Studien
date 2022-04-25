package com.dhbw.strand_pepperstudies_studien.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.object.conversation.ListenResult;
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.ListenActivity;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

public class SayFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_SayFragment";
    QiContext qiContext;
    View view;

    Button button_Explanation;
    Button button_Say;
    Button button_ListenToMe;
    Button button_UpdateList;
    TextView textViewPepperListen;

    SayActivity sayActivity;
    ListenActivity listenActivity;

    String heardPhrases = "";
    Future<ListenResult> currentPhraseFuture;


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


    // Custom Methods

    public void initializeButtonsAndOnClickListeners()
    {
        if(qiContext != null)
        {
            button_Explanation = view.findViewById(R.id.button_Explanation);
            button_Say = view.findViewById(R.id.button_Say);
            button_ListenToMe = view.findViewById(R.id.button_ListenToMe);
            button_UpdateList = view.findViewById(R.id.button_UpdateList);
            textViewPepperListen = view.findViewById(R.id.textViewPepperListen);

            button_Explanation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("You can make me talk on this screen. I can also listen to what you say if you press the correct button." +
                            " The list of things I can identify is on the left." +
                            " Update this list after I listened to you." );

                }
            });

            button_UpdateList.setOnClickListener(v -> {
                if (qiContext !=  null && listenActivity != null) {
                    currentPhraseFuture = listenActivity.getResultFuture();
                    if(currentPhraseFuture != null )
                    {
                        String resultString = currentPhraseFuture.getValue().getHeardPhrase().getText();
                        if(!resultString.equals(""))
                        {
                            heardPhrases = heardPhrases.concat(resultString + "\n");
                            textViewPepperListen.setText("---------- Things you said ----------\n\n"
                                    + heardPhrases);
                        }
                    }
                    else
                    {
                        sayActivity.setQiContext(this.qiContext);
                        sayActivity.SaySomething("No saved phrase");
                    }
                }
            });

            button_Say.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("I am going to explode in 3. . .... 2. . .... 1. . .... boom!" );
                }
            });

            button_ListenToMe.setOnClickListener(v -> {
                if (qiContext != null) {
                    listenActivity = new ListenActivity();
                    listenActivity.setQiContext(this.qiContext);
                    listenActivity.startListenFunction();
                }
            });
        }
        else
        {
            Log.i(TAG,"QiContext is null! " + TAG);
        }
    }
}