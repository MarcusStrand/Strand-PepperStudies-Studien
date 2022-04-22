package com.dhbw.strand_pepperstudies_studien.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.object.human.AttentionState;
import com.aldebaran.qi.sdk.object.human.EngagementIntentionState;
import com.aldebaran.qi.sdk.object.human.ExcitementState;
import com.aldebaran.qi.sdk.object.human.Gender;
import com.aldebaran.qi.sdk.object.human.Human;
import com.aldebaran.qi.sdk.object.human.PleasureState;
import com.aldebaran.qi.sdk.object.human.SmileState;
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.AnimationActivity;
import com.dhbw.strand_pepperstudies_studien.activities.HumanActivity;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

public class AnimationFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_AnimationFragment";
    View view;
    QiContext qiContext;

    Button button_Explanation;
    Button button_Animation;
    Button button_Human;
    TextView textView;

    SayActivity sayActivity;
    AnimationActivity animationActivity;
    HumanActivity humanActivity;

    Human engagedHuman;
    Integer age;
    Gender gender;
    PleasureState pleasureState;
    ExcitementState excitementState;
    SmileState smileState;
    AttentionState attentionState;
    EngagementIntentionState engagementIntentionState;

    // Android Lifecycle Callbacks

    public AnimationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sayActivity = new SayActivity();
        animationActivity = new AnimationActivity();
        humanActivity = new HumanActivity();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_animation, container, false);
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
            button_Animation = view.findViewById(R.id.button_Animation);
            button_Human = view.findViewById(R.id.button_Human);
            textView = view.findViewById(R.id.textViewAnimation);

            button_Explanation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("This button should explain the animation screen to you, but it does not");
                }
            });

            button_Animation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    animationActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("gnuk, gnuk");
                    animationActivity.doAnimation();
                }
            });

            button_Human.setOnClickListener(v -> {
                if (qiContext != null) {
                    humanActivity.setQiContext(this.qiContext);
                    engagedHuman = humanActivity.startHumanActivity();
                    if (engagedHuman != null) {
                        age = engagedHuman.getEstimatedAge().getYears();
                        gender = engagedHuman.getEstimatedGender();
                        pleasureState = engagedHuman.getEmotion().getPleasure();
                        excitementState = engagedHuman.getEmotion().getExcitement();
                        smileState = engagedHuman.getFacialExpressions().getSmile();
                        attentionState = engagedHuman.getAttention();
                        engagementIntentionState = engagedHuman.getEngagementIntention();
                        Log.i(TAG, "Human characteristics successfully stored.");
                    }
                    updateTextView();
                    engagedHuman = null;
                }
            });
        }
        else
        {
            Log.i(TAG,"QiContext is null! " + TAG);
        }
    }

    public void updateTextView()
    {
        if(engagedHuman != null)
        {
            textView.setText("Hallo");
        }
        textView.setText("Human not found.");
    }

}