package com.example.actionbar_feature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                     startActivity(new Intent(MainActivity.this, Add_Item_Page.class));
            }else if(Selected_Menu_Item==R.id.menu_delete){
                     startActivity(new Intent(MainActivity.this, Delete_Item_Page.class));
            }else if(Selected_Menu_Item==R.id.menu_about){
                     startActivity(new Intent(MainActivity.this, About_Page.class));
            }else if(Selected_Menu_Item==R.id.menu_tax){
                startActivity(new Intent(MainActivity.this, Tax_Page.class));
            }

        return super.onOptionsItemSelected(item);
    }

//TODO Fix Slider code*** (Put inside Tax_Page.java
//    private double calculateTotalWithSalesTax(){
//        double total=calculate_total();
//        return total*(1+(salesTax/100));
//    }
//TODO Fix Code Above ***

}
