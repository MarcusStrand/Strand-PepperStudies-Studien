package com.dhbw.strand_pepperstudies_studien.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.TakePictureBuilder;
import com.aldebaran.qi.sdk.object.camera.TakePicture;
import com.aldebaran.qi.sdk.object.image.EncodedImage;
import com.aldebaran.qi.sdk.object.image.EncodedImageHandle;
import com.aldebaran.qi.sdk.object.image.TimestampedImageHandle;


public class TakePictureActivity {

    private static final String TAG = "PepperStudies_TakePictureActivity";
    QiContext qiContext;
    TimestampedImageHandle result;
    Bitmap bmp;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void takePicture()
    {
        new Thread(() -> {
            if (qiContext != null) {
                // Build the action.
                TakePicture takePicture = TakePictureBuilder.with(qiContext).build();

                // Run the action synchronously.
                result = takePicture.async().run().getValue();

                // Retrieve the image data.
                // 1. get a proxy to access the data
                EncodedImageHandle encodedImageHandle = result.getImage();
                // 2. copy the remote data with the getValue() method
                EncodedImage encodedImage = encodedImageHandle.getValue();
                //convert to bitmap
                byte[] byteArray = encodedImage.getData().array();
                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


            } else {
                Log.i(TAG, "qiContext is null in TakePictureActivity");
            }
        }).start();
    }

    public Bitmap updatePicture()
    {
        if(bmp != null)
        {
            return bmp;
        }
        return null;
    }
}
