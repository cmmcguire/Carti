package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Pair;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete_Item_Page extends AppCompatActivity {
    ListView ItemList;
    ArrayAdapter<Pair<String,Double>> adapter;
    //ArrayList<Pair<String,Double>> items = MainActivity.items;
    ArrayList<Pair<String,Double>> list_item = new ArrayList<>();
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete__item__page);
        ItemList = findViewById(R.id.ListViewItems);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ItemList.setAdapter(adapter);
        ItemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        ItemList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                if(list_item.contains(items.get(position))){
                    list_item.remove(items.get(position));
                    count = list_item.size();
                    mode.setTitle(count + " items selected");
                }else{
                    count = count+1;
                    mode.setTitle(count + " items selected");
                    list_item.add(items.get(position));
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
                        for (Pair<String, Double> msg : list_item) {
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
    public void confirm_input(){
        sendDeletedData();
    }
    private void sendDeletedData(){
        Intent intent = new Intent(Delete_Item_Page.this,MainActivity.class);
        for(int i = 0; i < list_item.size(); i++){
            Pair<String,Double> selected = new Pair<>(null,null);
            selected = list_item.get(i);
            String to_delete = selected.first;
            intent.putExtra("Delete",to_delete);
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}
