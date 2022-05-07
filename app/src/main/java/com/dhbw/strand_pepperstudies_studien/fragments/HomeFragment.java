package com.dhbw.strand_pepperstudies_studien.fragments;

import android.graphics.Bitmap;
import android.util.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.dhbw.strand_pepperstudies_studien.MainActivity;
import com.dhbw.strand_pepperstudies_studien.R;
import com.dhbw.strand_pepperstudies_studien.activities.SayActivity;
import com.dhbw.strand_pepperstudies_studien.activities.TakePictureActivity;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements RobotLifecycleCallbacks {

    private static final String TAG = "PepperStudies_HomeFragment";
    View view;
    QiContext qiContext;

    Button button_Explanation;
    Button button_TakePicture;
    Button button_UpdatePicture;
    ImageView imageView_CameraPicture;

    SayActivity sayActivity;
    TakePictureActivity takePictureActivity;


    // Android Lifecycle Callbacks

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sayActivity = new SayActivity();
        takePictureActivity = new TakePictureActivity();

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
        Log.i(TAG, "Robot Focus lost " + TAG);
        this.qiContext = null;
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        Log.i(TAG, "Robot focus refused because " + reason + " " + TAG);
    }


    //Custom Methods

    public void initializeButtonsAndOnClickListeners() {
        if (qiContext != null) {
            button_Explanation = view.findViewById(R.id.button_Explanation);
            button_TakePicture = view.findViewById(R.id.button_TakePicture);
            button_UpdatePicture = view.findViewById(R.id.button_UpdatePicture);
            imageView_CameraPicture = view.findViewById(R.id.imageView_CameraPicture);

            button_Explanation.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("On this screen you can take a picture of what pepper sees. Update the imageview afterwards to display it.");
                }
            });

            button_TakePicture.setOnClickListener(v -> {
                if (qiContext != null) {
                    sayActivity.setQiContext(this.qiContext);
                    takePictureActivity.setQiContext(this.qiContext);
                    sayActivity.SaySomething("Picture taken");
                    takePictureActivity.takePicture();
                }
            });

            button_UpdatePicture.setOnClickListener(v -> {
                if (qiContext != null) {
                    Bitmap bmp = takePictureActivity.updatePicture();
                    if (bmp != null) {
                        imageView_CameraPicture.setImageBitmap(bmp);
                    } else {
                        sayActivity.setQiContext(this.qiContext);
                        sayActivity.SaySomething("No picture found, take one first!");
                    }
                }
            });
        } else {
            Log.i(TAG, "QiContext is null! " + TAG);
        }
    }
}