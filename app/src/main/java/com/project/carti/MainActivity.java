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

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;
import java.util.ArrayList;

import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // static constants for onActivityResult
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int ADD_ITEM_REQUEST_CODE = 2;

    // instance of TextProcessing class to call methods in class
    private TextProcessing textProcessing = new TextProcessing();

    // declaration of UI features
    ImageView mImageView;
    Button cameraBtn;
    Bitmap imageBitmap;
    TextView recognizedTextView, StringTotal, PriceTotal;


    // declaration of global variables
    double salesTax = 0;
    ArrayList<Pair<String,Double>> items = new ArrayList<Pair<String, Double>>();  // holds itemNameString with price, list of tuples

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binds buttons, TextViews, and ImageViews to xml
        mImageView = findViewById(R.id.mImageView);
        cameraBtn = findViewById(R.id.cameraButton);
        StringTotal = findViewById(R.id.StringTotal);
        recognizedTextView = findViewById(R.id.recognizedTextView);
        PriceTotal = findViewById(R.id.PriceTotal);


        //shopTotal = findViewById(R.id.shopTotal);

        // listens for Camera button to be clicked
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraButtonClicked();
            }
        });

        // end of onCreate
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Menu_Object = getMenuInflater();
        Menu_Object.inflate(R.menu.main_menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int Selected_Menu_Item=item.getItemId();

        if(Selected_Menu_Item==R.id.menu_grocery_list){
            startActivity(new Intent(MainActivity.this, Grocery_List_Page.class));
        } else if (Selected_Menu_Item==R.id.menu_add){

            // allows Add_Item_Page to send data back to MainActivity
            Intent intent = new Intent(MainActivity.this, Add_Item_Page.class);
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);

        }else if(Selected_Menu_Item==R.id.menu_delete){
            startActivity(new Intent(MainActivity.this, Delete_Item_Page.class));
        }else if(Selected_Menu_Item==R.id.menu_about){
            startActivity(new Intent(MainActivity.this, About_Page.class));
        }else if(Selected_Menu_Item==R.id.menu_tax){
            startActivity(new Intent(MainActivity.this, Tax_Page.class));
        }

        return super.onOptionsItemSelected(item);
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

    // opens camera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // sets the captured image in the ImageView on screen after REQUEST_IMAGE_CAPTURE
    // retrieve text from Add_Item_Page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check for REQUEST_IMAGE_CAPTURE
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);

            // directly call detectImage()
            // eliminates need for detect button
            detectImage();
        }

        // alternatively, check for ADD_ITEM_REQUEST_CODE
        if (requestCode == ADD_ITEM_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String manualName = data.getStringExtra("name");
                String manualPrice = data.getStringExtra("price");
                String manualQuantity = data.getStringExtra("quantity");

                manualInputToArrayList(manualName, manualPrice, manualQuantity);
            }
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



        // print total with tax to screen to 2 decimal places
        updateTotalOnScreen();

    }

    // calculates total without sales tax
    private double calculate_total(){
        double total = 0;
        for(Pair<String,Double> item : items)
        {
            total+=item.second;
        }
        System.out.println("Total is " + total);
        return total;
    }

    // add sales tax to total and return result
    private double calculateTotalWithSalesTax() {
        double total = calculate_total();
        return total * (1 + salesTax);
    }

    // converts a double to a string
    protected String convertToString(double dub)
    {
        String str;
        str = Double.toString(dub);
        return str;
    }

    // manual entry to ArrayList through name, price, and quantity values
    private void manualInputToArrayList(String name, String price, String quantity) {

        double priceDouble = textProcessing.convertToDouble(price);
        int quantityInt = Integer.parseInt(quantity);

        // add item to the ArrayList quantityInt number of times
        for (int i = 0; i < quantityInt; i++)
        {
            items.add(new Pair <String,Double> (name, priceDouble));
        }

        recognizedTextView.setText("");
        updateTotalOnScreen();
    }

    private void updateTotalOnScreen() {
        String stringTotal = "$" + String.format("%.2f", calculateTotalWithSalesTax());
        PriceTotal.setTextSize(24);
        PriceTotal.setText(stringTotal);
    }
}
