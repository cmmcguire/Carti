package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class Tax_Page extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;

    int currentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tax__page);

        textView = (TextView) findViewById(R.id.Progress_Bar_Textview);
        progressBar = (ProgressBar) findViewById(R.id.Progress_Bar_Slider);
        seekBar = (SeekBar) findViewById(R.id.seekBar2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textView.setText("" + progress + "%");
                currentProgress = progress;
                System.out.println(currentProgress + "***1");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    // confirm button pressed
    public void Confirm_Input(View view){
        passTaxInput();
    }

    // converts progress int to correct tax
    // Ex. 7 converts to .07
    private double convertToTax(int progress) {
        return ((double)progress / 100);
    }

    // passes tax input back to MainActivity
    private void passTaxInput() {

        double taxRate = convertToTax(currentProgress);

        System.out.println(taxRate + "***2");

        Intent intent = new Intent();
        intent.putExtra("tax", taxRate);
        setResult(RESULT_OK, intent);
        finish();
    }


// is the below needed at all?

//    public void TaxBar(){
//
//        tax_bar = findViewById(R.id.seekBar2);
//        tax = findViewById(R.id.textView3);
//        tax_bar.setOnSeekBarChangeListener(
//                new SeekBar.OnSeekBarChangeListener() {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                        MainActivity.salesTax = ((float)progress/10.0);
//                        tax.setText("Tax Amount: " + MainActivity.salesTax + "%");
//
//
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//
//                    }
//                }
//        );
//    }



}