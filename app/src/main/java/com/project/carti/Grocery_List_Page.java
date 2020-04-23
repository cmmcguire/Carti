package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.lang.Double;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Grocery_List_Page extends AppCompatActivity {

    ListView ItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery__list__page);

        Intent intent = getIntent();
        ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");
        ArrayList<Double> prices = (ArrayList<Double>) intent.getSerializableExtra("prices");

        ArrayList<String> names_prices=new ArrayList<>();

        ItemList=(ListView) findViewById(R.id.Results);

        for(int i = 0; i < names.size(); i++)
        {
            int number=i+1;
            String result="Item "+number+": "+names.get(i)+"\n"+"Price: $"+prices.get(i).toString();
            names_prices.add(i,result);
        }

    ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names_prices);
        ItemList.setAdapter(adapter);

    }
}
















