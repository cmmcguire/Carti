package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
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
    ArrayList<Pair<String,Double>> items = new ArrayList<Pair<String,Double>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete__item__page);
        ItemList = (ListView) findViewById(R.id.ListViewItems);
        items.add(new Pair<>("Bananas", 15.0));
        items.add(new Pair<>("Candy", 3.99));
        items.add(new Pair<>("Soda", 5.0));
        items.add(new Pair<>("Rice", 3.0));
        items.add(new Pair<>("Cheese", 4.50));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ItemList.setAdapter(adapter);
        ItemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        ItemList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
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
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

}
