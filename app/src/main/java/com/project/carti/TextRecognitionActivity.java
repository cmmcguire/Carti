package com.project.carti;

import java.util.*;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class TextRecognitionActivity extends AppCompatActivity {



    private void runTestRecognition(){
        //FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(???); <------- image from camera should be passed as paramater
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        //disable button while image is being processed
        //detector.processImage(???)  <----- pass image from camera as paramater
    }

    private void processTextRecognitionResult(FirebaseVisionText texts)     //should find actual text from image
    {
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
    }

}
