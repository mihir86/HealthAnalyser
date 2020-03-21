package com.example.healthanalyser;

public class QuestionsLibrary {
    private String questions [] = {
        "How are you","Your name is"
    };
    private String options[][] = {
            {"Good","Bad","Lucky","Great"},
            {"Gsdood","Brad","Lucsky","Greasdt"}
    };
    private int weights[][] = {
            {10,20,30,40},{10,20,40,40}
    };

    public String getques(int i){
        String ques = questions[i];
        return ques;
    }
    public String getoption1(int i){
        String opt = options[i][0];
        return opt;
    }
    public String getoption2(int i){
        String opt = options[i][1];
        return opt;
    }
    public String getoption3(int i){
        String opt = options[i][2];
        return opt;
    }
    public String getoption4(int i){
        String opt = options[i][3];
        return opt;
    }
    public int getweight(int i,int j){
        int w = weights[i][j];
        return w;
    }
}
