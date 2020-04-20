package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Delete_Item_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete__item__page);

        Intent intent = getIntent();
        ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");
        ArrayList<Double> prices = (ArrayList<Double>) intent.getSerializableExtra("prices");

        // Jose you can access the names and prices above
        // note order is the same --> so for example index 0 in names corresponds to index 0 in prices
    }

    // TODO: method to select item to delete HERE (item is actually deleted in MainActivity)



    //*** IMPORTANT NOTE: below is the code to pass the deleted item back to MainActivity
    // TODO: all that is needed is to set itemNameToDelete in function below to the correct item name String

    // passes item to be deleted back to MainActivity
    private void passItemToDelete() {

        String itemNameToDelete = "";

        Intent intent = new Intent();
        intent.putExtra("name", itemNameToDelete);
        setResult(RESULT_OK, intent);
        finish();
    }
}
