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

    double currentProgress = 0.0;

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

                double correctedProgress = (double)progress / 4;
                textView.setText("" + correctedProgress + "%");
                currentProgress = correctedProgress;
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

    // converts progress double to correct tax
    // Ex. 7.00 converts to .07
    private double convertToTax(double progress) {
        return (progress / 100);
    }

    // passes tax input back to MainActivity
    private void passTaxInput() {

        double taxRate = convertToTax(currentProgress);

        Intent intent = new Intent();
        intent.putExtra("tax", taxRate);
        setResult(RESULT_OK, intent);
        finish();
    }

}