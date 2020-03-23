package com.example.healthanalyser;

public class QuestionsLibrary {
    private String questions [] = {
        "In the past two weeks, how often have you felt down or hopeless?",
        "Have you had any thoughts of suicide?",
        "How is your sleep",
        "How is your energy?",
        "Do you prefer to stay at home rather than going out and doing new things?",
        "Overall, How would you decribe your mood?",
        "Do you consume drugs",
        "How connected do you feel to the people around you?",
        "Are you eating more or less than you normally do?",
        "Are you capable of enjoying things right now?",
        "how often do you experience physical symptoms like headaches, insomnia, or digestive issues?"
    };
    private String options[][] = {
            {"not at all","several days","more than half of the days","nearly every day"},
            {"never","some thoughts of death","some thoughts of suicide","some attempt of suicide"},
            {"sleeping as usual","slight difficulty","sleep reduced by atleast two hours","getting less than 3 hours of sleep"},
            {"As much energy as ever","less energy than before","not enough to do much","not enough to do anything"},
            {"yes","no","sometimes","dont know,depends on my mood"},
            {"Like a roller coaster", "pretty much steady", "All blues" ,"cheerful"},
            {"yes","no","sometimes","only when i am down" },
            {"A lot", "Not much", "I dont talk to people", "i am an ambivert"},
            {"eating as usual", "slightly less", "skip atleast one meal", "eating less than 2 meals"},
            {"yes", "no","sometimes","a lot of times" },
            {"alamost everyday", "several days", "seldom", "never"}
    };
    private int weights[][] = {
            {0,3,6,12},
            {0,6,6,12},
            {0,2,4,8},
            {0,2,4,8},
            {8,0,2,4},
            {4,2,8,0},
            {6,0,3,12},
            {0,6,3,0},
            {0,2,4,8},
            {0,8,4,2},
            {8,4,2,0}
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
