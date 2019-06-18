package com.example.mathgame;

import android.app.Application;

public class MyMathGame extends Application {

    private int currentScore;
    private int hello;



    public MyMathGame() {
        this.currentScore = 0;
    }

    public int getCurrentScore(){
        return currentScore;
    }
    public void setCurrentScore(int s){
        currentScore = s;
    }

    public void addOneToCurrentScore()
    {
        currentScore++;
    }

    public void addTwoToCurrentScore()
    {
        currentScore = currentScore + 2;
    }

    public void substructOneFromCurrentScore()
    {
        if(currentScore >= 1)
        {
            currentScore--;
        }
    }
}
