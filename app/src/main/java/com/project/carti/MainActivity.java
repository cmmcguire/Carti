package com.project.carti;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import android.util.Pair;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // instance of TextProcessing class to call methods in class
    private TextProcessing textProcessing = new TextProcessing();

    // declaration of UI features
    ImageView mImageView;
    Button cameraBtn, detectBtn;
    Bitmap imageBitmap;
    TextView recognizedTextView, shopTotal;

    // declaration of global variables
    double salesTax;
    ArrayList<Pair<String,Double>> items = new ArrayList<Pair<String, Double>>(); //holds itemNameString with price
                                                                                    //list of tuples
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binds buttons, TextViews, and ImageViews to xml
        mImageView = findViewById(R.id.mImageView);
        cameraBtn = findViewById(R.id.cameraButton);
        detectBtn = findViewById(R.id.detectButton);
        recognizedTextView = findViewById(R.id.textView);
        shopTotal = findViewById(R.id.shopTotal);


        // listens for Camera button to be clicked
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraButtonClicked();
            }
        });

        // listens for Detect button to be clicked
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectImage();
            }
        });
        System.out.println("hello world end of program\n");
    }

    // process image in with Firebase API methods
    private void detectImage() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();       //on device

        Task<FirebaseVisionText> result =
                detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText result) {
                                // Task completed successfully
                                textProcessingControl(result);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        // Task failed with an exception
                                    }
                                });
    }

    // a constant set to 1 used in image capture methods
    static final int REQUEST_IMAGE_CAPTURE = 1;

    // opens camera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // sets the captured image in the ImageView on screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    // called when camera button is clicked --> then calls method to open camera
    private void cameraButtonClicked() {
        dispatchTakePictureIntent();

    }

    // text processing control method --> calls processing in TextProcessing class
    private void textProcessingControl(FirebaseVisionText text) {

        // retrieve blocks of text using Firebase getTextBlocks()
        List<FirebaseVisionText.TextBlock> blocks = text.getTextBlocks();

        // if no text found, set text field
        if (blocks.size() == 0) {
            Toast.makeText(MainActivity.this, "Sorry, No Price Found", Toast.LENGTH_LONG).show();
            return;
        }

        // call method in TextProcessing class
        String detectedPriceString = textProcessing.parseForPrice(blocks);

        // set recognizedTextView to detected price
        recognizedTextView.setTextSize(24);
        recognizedTextView.setText(detectedPriceString);

        if (detectedPriceString == "") {
            Toast.makeText(MainActivity.this, "Sorry, No Price Found", Toast.LENGTH_LONG).show();
            return;
        }


        double detectedPriceDouble = textProcessing.convertToDouble(detectedPriceString);
                   //list of tuples(2 values)

        Double detectedPriceDoubObj = new Double(detectedPriceDouble);

                                                                                                        //detectedPriceDouble is casted as a double Object
        items.add(new Pair <String,Double> (new String("default"), detectedPriceDoubObj));      //Pair values cant be primitive. must cast to objects
                                            /*                  ^^^^^^^^                */
                                            /******change default to initialize with itemNameStr*****/

        // call a function to update total here

    }

    private double calculate_total(){
        double total = 0;
        for(Pair<String,Double> item : items)
        {
            total+=item.second;
        }
        return total;
    }
    // add sales tax to total and return result
    private double calculateTotalWithSalesTax() {
        double total = calculate_total();
        return total * (1 + salesTax);
    }
}
