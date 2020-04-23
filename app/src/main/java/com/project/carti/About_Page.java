package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About_Page extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about__page);

        TextView textView;

        String info = "Welcome to Carti! Keep track of your grocery bill as you go.\n" +
                "Use these features to make your experience the best it can be:\n" +
                "\t- Click the grocery bag icon to see your complete list of items.\n" +
                "\t- Click the add item icon to manually add an icon if there is no price tage.\n" +
                "\t- Click the delete item icon to delete items if you need to put something back.\n" +
                "\t- Click the Tax button to adjust your sales tax amount.\n" +
                "Happy shopping!";

        textView = findViewById(R.id.textView);
        textView.setTextSize(20);
        textView.setText(info);
    }
}