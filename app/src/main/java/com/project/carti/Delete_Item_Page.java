package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete_Item_Page extends AppCompatActivity {
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete__item__page);
      
        ListView ItemList;

        Intent intent = getIntent();
        ArrayList<String> names = intent.getStringArrayListExtra("names");

        ItemList = findViewById(R.id.ListViewItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, names);

        ItemList.setAdapter(adapter);

        ItemList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = (String) parent.getItemAtPosition(position);
            }
        });
    }
    public void confirm_input(View view){
        passItemToDelete();
    }
    private void passItemToDelete() {

        String itemNameToDelete = name;

        Intent intent = new Intent();
        intent.putExtra("name", itemNameToDelete);
        setResult(RESULT_OK, intent);
        finish();
    }
}