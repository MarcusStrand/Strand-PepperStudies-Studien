package com.dhbw.strand_pepperstudies_studien.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.LocalizeAndMapActivity;
import com.dhbw.strand_pepperstudies_studien.activities.MoveActivity;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;

public class MoveFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_MoveFragment";
    QiContext qiContext;
    View view;

    Button button_Explanation;
    Button button_MoveForward;
    Button button_LocalizeAndMap;
    Button button_UpdateMap;
    ImageView imageView;

    SayActivity sayActivity;
    MoveActivity moveActivity;
    LocalizeAndMapActivity localizeAndMapActivity;

    Bitmap bmp;


    // Android Lifecycle Callbacks

    public MoveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sayActivity = new SayActivity();
        moveActivity = new MoveActivity();
        localizeAndMapActivity = new LocalizeAndMapActivity();

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
            button_LocalizeAndMap = view.findViewById(R.id.button_LocalizeAndMap);
            button_UpdateMap = view.findViewById(R.id.button_UpdateMap);
            imageView = view.findViewById(R.id.PepperMap);

            button_Explanation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("TODO: explanation.");
                }
            });

            button_UpdateMap.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    if(bmp != null)
                    {
                        sayActivity.SaySomething("Here is the updated map.");
                        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, imageView.getWidth(), imageView.getHeight(), false));
                    }
                    else
                    {
                        sayActivity.SaySomething("Create a map first.");
                    }
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

            button_LocalizeAndMap.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    localizeAndMapActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("Please stand back as I localize myself.");
                    bmp = localizeAndMapActivity.LocalizeAndMap();
                }
            });
        }
        else
        {
            Log.i(TAG,"QiContext is null! " + TAG);
        }
    }
}

