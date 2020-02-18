package com.project.carti;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
//import android.annotation.NonNull;
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

//    private int Count=0;
//    private TextView Show_Count;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Show_Count=(TextView) findViewById(R.id.show_count); //takes the ID and returns the view which is why we had to cast it. So Show_count is not referenced to a view/event
//                                                //show_count is the id of our counter event
//        Log.d("MainActivity", "Hello World");
//    }
//
//    public void Count_Up(View view) {
//        Count++;
//            if(Show_Count!=null){
//                Show_Count.setText(Integer.toString(Count));   //sets the view to an integer and changes the value
//            }
//    }
//
//    public void Show_Count(View view) {
//        Toast toast =Toast.makeText(this, R.string.Message, Toast.LENGTH_SHORT);   //takes "this" object and creates a message from string.xml for length short seconds
//        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,500);   //changes centering
//        toast.show();    //displays text
//    }

    ImageView mImageView;
    Button cameraBtn, detectBtn;
    Bitmap imageBitmap;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.mImageView);
        cameraBtn = findViewById(R.id.cameraButton);
        detectBtn = findViewById(R.id.detectButton);
        textView = findViewById(R.id.textView);

//        // set title on ActionBar
//        getActionBar().setTitle("Carti (Text Recognition Test)");

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraBtnClicked();
            }
        });
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

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }


    private void cameraBtnClicked() {

        dispatchTakePictureIntent();

    }

    private void processTxt(FirebaseVisionText text) {
        List<FirebaseVisionText.TextBlock> blocks = text.getTextBlocks();
        if (blocks.size() == 0) {
            Toast.makeText(MainActivity.this, "Sorry, No Text Found", Toast.LENGTH_LONG).show();
            return;
        }
        for (FirebaseVisionText.TextBlock block : text.getTextBlocks()) {
            String txt = block.getText();
            textView.setTextSize(24);
            textView.setText(txt);
        }
    }


}

