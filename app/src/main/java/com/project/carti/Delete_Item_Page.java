package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Delete_Item_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete__item__page);
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
