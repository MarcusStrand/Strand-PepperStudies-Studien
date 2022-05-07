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

    public void takePicture() {
        new Thread(() -> {
            if (qiContext != null) {

                // Take the picture
                TakePicture takePicture = TakePictureBuilder.with(qiContext).build();
                result = takePicture.async().run().getValue();

                // Retrieve the image data and convert
                EncodedImageHandle encodedImageHandle = result.getImage();
                EncodedImage encodedImage = encodedImageHandle.getValue();
                byte[] byteArray = encodedImage.getData().array();
                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            } else {
                Log.i(TAG, "qiContext is null in TakePictureActivity");
            }
        }).start();
    }

    public Bitmap updatePicture() {
        if (bmp != null) {
            return bmp;
        }
        return null;
    }
}
