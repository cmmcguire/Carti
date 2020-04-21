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
        ArrayList<Double> prices = (ArrayList<Double>) intent.getSerializableExtra("prices");
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
/*
   ItemList.setSelection(new ) {
@Override
public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        if(tempNames.contains(names.get(position))){
        tempNames.remove(names.get(position));
        count = tempNames.size();
        mode.setTitle(count + " items selected");
        }else{
        count = count+1;
        mode.setTitle(count + " items selected");
        tempNames.add(names.get(position));
        name = names.get(position);
        }
        }

@Override
public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        count=0;
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.delete_menu_option, menu);

        return true;
        }

@Override
public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
        }

@Override
public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch(item.getItemId()) {
        case R.id.delete_id:
        for (String msg : tempNames) {
        adapter.remove(msg);
        }
        Toast.makeText(getBaseContext(), count + " items removed", Toast.LENGTH_SHORT).show();
        count = 0;
        mode.finish();
        return true;
        case R.id.confirm_delete:
        confirm_input();
default:
        return false;
        }
        }

@Override
public void onDestroyActionMode(ActionMode mode) {

        }
        });
        }
        */