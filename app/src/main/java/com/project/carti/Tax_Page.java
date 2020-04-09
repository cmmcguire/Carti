package com.project.carti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class Tax_Page extends AppCompatActivity {

    //TODO private static SeekBar tax_bar;
    //TODO private static TextView tax;

    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tax__page);

        textView= (TextView) findViewById(R.id.Progress_Bar_Textview);
        progressBar= (ProgressBar) findViewById(R.id.Progress_Bar_Slider);
        seekBar= (SeekBar) findViewById(R.id.seekBar2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                textView.setText("" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



//TODO Fix Slider Code*****

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
//
//} TODO Fix Above Code^^^



}