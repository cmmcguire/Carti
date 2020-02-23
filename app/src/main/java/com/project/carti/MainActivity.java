package com.project.carti;

import android.os.Bundle;
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

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    Button cameraBtn, detectBtn;
    Bitmap imageBitmap;
    TextView textView, shopTotal;
    double total;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binds buttons, TextViews, and ImageViews to xml
        mImageView = findViewById(R.id.mImageView);
        cameraBtn = findViewById(R.id.cameraButton);
        detectBtn = findViewById(R.id.detectButton);
        textView = findViewById(R.id.textView);
        shopTotal = findViewById(R.id.shopTotal);


        // listens for Camera button to be clicked
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraBtnClicked();
            }
        });

        // listens for Detect button to be clicked
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectImg();
            }
        });
    }


    private void detectImg() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

        Task<FirebaseVisionText> result =
                detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText result) {
                                // Task completed successfully
                                processTxt(result);
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
    private void cameraBtnClicked() {
        dispatchTakePictureIntent();

    }

    // prototype text processing method

    private void processTxt(FirebaseVisionText text) {
        // retrieve blocks of text using Firebase getTextBlocks()
        List<FirebaseVisionText.TextBlock> blocks = text.getTextBlocks();

        // if no text found, set text field
        if (blocks.size() == 0) {
            Toast.makeText(MainActivity.this, "Sorry, No Text Found", Toast.LENGTH_LONG).show();
            return;
        }

        // process text block, set TextView on screen
        for (FirebaseVisionText.TextBlock block : text.getTextBlocks()) {
            txt = block.getText();
            textView.setTextSize(24);
            textView.setText(txt);
            convertToInt(txt);
        }
    }

    //converts string from picture to int and then adds it to total
    private void convertToInt(String str)
    {
        int number;
        number = Integer.parseInt(str);
        System.out.println(number);
        total = total+number;
    }

    private void calculateSalesTax()
    {
        total = total*(1.06);
    }
}

