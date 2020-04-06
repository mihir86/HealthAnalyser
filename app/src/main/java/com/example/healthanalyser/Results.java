package com.example.healthanalyser;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;

public class Results extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView suggestionText;
    private int finalres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        finalres = Questions.finalres;
        Questions.finalres = 0;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressText = (TextView) findViewById(R.id.textView2);
        suggestionText = (TextView) findViewById(R.id.textView3);
        progressBar.setProgress(finalres);
        progressText.setText(Integer.toString(finalres));
        String temp;
        if(finalres <= 20) {
            suggestionText.setTextColor(Color.GREEN);
            temp = "Congrats! You have great mental health state!";
        }
        else if(finalres <= 40) {
            suggestionText.setTextColor(Color.parseColor("#FFFFC107"));
            temp = "You are suffering from a bit of low mental health, however hang in there and it'll be fine!";
        }
        else if(finalres <= 60)
        {
            suggestionText.setTextColor(Color.RED);
            temp = "You are suffering from mild poor mental health, recommend consulting specialists";
        }
        else
        {
            suggestionText.setTextColor(Color.RED);
            temp = "You are suffering from severe poor mental health, immediate doctor consultation required!!!";
        }
        suggestionText.setText(temp);
    }
}