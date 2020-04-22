package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.lang.Double;
import java.io.Serializable;

public class Grocery_List_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery__list__page);

        Intent intent = getIntent();
        ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");
        ArrayList<Double> prices = (ArrayList<Double>) intent.getSerializableExtra("prices");

        // Alexis you can access the names and prices above
        // note order is the same --> so for example index 0 in names corresponds to index 0 in prices
    }
}
