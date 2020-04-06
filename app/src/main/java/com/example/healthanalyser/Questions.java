package com.example.healthanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Questions extends AppCompatActivity {
    private QuestionsLibrary questionsLibrary = new QuestionsLibrary();
    private TextView ques;
    private Button c1;
    private Button c2;
    private Button c3;
    private Button c4;
    private int qno = 0;
    public static int finalres = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ques = (TextView) findViewById(R.id.ques);
        c1 = (Button) findViewById(R.id.button1);
        c2 = (Button) findViewById(R.id.button2);
        c3 = (Button) findViewById(R.id.button3);
        c4 = (Button) findViewById(R.id.button4);

        if(qno==0)
            UpdateQuestion();
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalres += questionsLibrary.getweight(qno-1,0);
                    UpdateQuestion();
                }
            });
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalres += questionsLibrary.getweight(qno-1,1);
                    UpdateQuestion();
                }
            });
            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalres += questionsLibrary.getweight(qno-1,2);
                    UpdateQuestion();
                }
            });
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalres += questionsLibrary.getweight(qno-1,3);
                    UpdateQuestion();
                }
            });
    }

    private void UpdateQuestion(){
        if(qno==10)
            startActivity(new Intent(Questions.this,Results.class));
        else {
            ques.setText(questionsLibrary.getques(qno));
            c1.setText(questionsLibrary.getoption1(qno));
            c2.setText(questionsLibrary.getoption2(qno));
            c3.setText(questionsLibrary.getoption3(qno));
            c4.setText(questionsLibrary.getoption4(qno));
            qno++;
        }
    }
}
