package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Add_Item_Page extends AppCompatActivity {

    private TextInputLayout Item_Input_Value;
    private TextInputLayout Price_Input_Value;
    private TextInputLayout Quantity_Input_Value;
    private TextInputLayout Description_Input_Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add__item__page);

        Item_Input_Value=findViewById(R.id.Add_Item_Input_Value);
        Price_Input_Value=findViewById(R.id.Add_Item_Price_Value);
        Quantity_Input_Value=findViewById(R.id.Add_Item_Quantity_Value);
        Description_Input_Value=findViewById(R.id.Add_Item_Description_Value);

    }


    public void Confirm_Input(View view){
        if(!Validate_Item() | !Validate_Price() | !Validate_Quantity()){
            return;
        }
    }

    private boolean Validate_Item(){
        String Item_Input=Item_Input_Value.getEditText().getText().toString().trim();

        if(Item_Input.isEmpty()){
            Item_Input_Value.setError("Field Cannot be Empty");
            return false;
        }else if(Item_Input.length()>30){
            Item_Input_Value.setError("Value is Too Large");
            return false;
        } else{
            Item_Input_Value.setError(null);
            return true;
        }
    }

    private boolean Validate_Price(){
        String Price_Input=Price_Input_Value.getEditText().getText().toString().trim();

        if(Price_Input.isEmpty()){
            Price_Input_Value.setError("Field Cannot be Empty");
            return false;
        }else if(Price_Input.length() > 6){
            Price_Input_Value.setError("Value is Too Large");
            return false;
        }else if(Price_Input.equals("0") || Price_Input.contains("-")){
            Price_Input_Value.setError("Value Must be Greater than 0");
            return false;
        } else{
            Price_Input_Value.setError(null);
            return true;
        }
    }

    private boolean Validate_Quantity(){
        String Quantity_Input=Quantity_Input_Value.getEditText().getText().toString().trim();

        if(Quantity_Input.isEmpty()){
            Quantity_Input_Value.setError("Field Cannot be Empty");
            return false;
        }else if(Quantity_Input.length() > 2){
            Quantity_Input_Value.setError("Value is Too Large");
            return false;
        }else if(Quantity_Input.equals("0") || Quantity_Input.contains("-")){
            Quantity_Input_Value.setError("Value Must be Greater than 0");
            return false;
        }
        else{
            Quantity_Input_Value.setError(null);
            return true;
        }
    }



}
