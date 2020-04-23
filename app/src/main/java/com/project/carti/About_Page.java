package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About_Page extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about__page);

        String info_page = "Welcome to Carti! Keep track of your grocery bill as you go. " +
                "Use the camera to scan price tags. " +
                "Adjust your sales tax using the tax slider. " +
                "If there is no price tag, manually add the price of an item to keep an accurate bill." +
                " Use the delete button when you put an item back or spend too much. " +
                "View your entire grocery list to keep track of what you’ve already bought. " +
                "Happy shopping!";

        textView = (TextView)findViewById(R.id.textView);
        textView.setText(info_page);
    }
}