package com.example.healthanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TxtAnalyse extends AppCompatActivity {

    private EditText et;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt_analyse);
        et = (EditText) findViewById(R.id.editText);
        submit = (Button) findViewById(R.id.button5);

        //string matching function

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = et.getText().toString();
                //call the function here where txt is the req string
                startActivity(new Intent(TxtAnalyse.this,TxtResults.class));
            }
        });

    }
}
