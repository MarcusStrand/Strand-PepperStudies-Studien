package com.dhbw.strand_pepperstudies_studien.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.LocalizeAndMapBuilder;
import com.aldebaran.qi.sdk.object.actuation.ExplorationMap;
import com.aldebaran.qi.sdk.object.actuation.LocalizationStatus;
import com.aldebaran.qi.sdk.object.actuation.LocalizeAndMap;
import com.aldebaran.qi.sdk.object.actuation.MapTopGraphicalRepresentation;
import com.aldebaran.qi.sdk.object.image.EncodedImage;

public class LocalizeAndMapActivity {

    private static final String TAG = "PepperStudies_LocalizeAndMapActivity";
    QiContext qiContext;
    LocalizeAndMap localizeAndMap;
    private Future<Void> localizingAndMapping;
    ExplorationMap explorationMap;
    Bitmap bmp;

    public void setQiContext(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public Bitmap LocalizeAndMap()
    {
        new Thread(() -> {
            // Build the action.
            localizeAndMap = LocalizeAndMapBuilder.with(qiContext).build();

            // Add a listener to get the map when localized.
            localizeAndMap.addOnStatusChangedListener(localizationStatus -> {
                if (localizationStatus == LocalizationStatus.LOCALIZED) {
                    // Stop the action.
                    localizingAndMapping.requestCancellation();
                    // Dump the map for future use by a Localize action.
                    explorationMap = localizeAndMap.dumpMap();
                    MapTopGraphicalRepresentation mapGraphicalRepresentation =
                            explorationMap.getTopGraphicalRepresentation();
                    EncodedImage encodedImage = mapGraphicalRepresentation.getImage();
                    byte[] byteArray = encodedImage.getData().array();
                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                }
            });
            // Run the action.
            localizingAndMapping = localizeAndMap.async().run();
        }).start();
        return bmp;
    }
}
