package com.project.carti;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int Count=0;
    private TextView Show_Count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Show_Count=(TextView) findViewById(R.id.show_count); //takes the ID and returns the view which is why we had to cast it. So Show_count is not referenced to a view/event
                                                //show_count is the id of our counter event
        Log.d("MainActivity", "Hello World");
    }

    public void Count_Up(View view) {
        Count++;
            if(Show_Count!=null){
                Show_Count.setText(Integer.toString(Count));   //sets the view to an integer and changes the value
            }
    }

    public void Show_Count(View view) {
        Toast toast =Toast.makeText(this, R.string.Message, Toast.LENGTH_SHORT);   //takes "this" object and creates a message from string.xml for length short seconds
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,500);   //changes centering
        toast.show();    //displays text
    }
}

