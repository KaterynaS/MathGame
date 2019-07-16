package com.example.mathgame;

import android.app.Application;

public class MyMathGame extends Application {

    private int currentScore;

    public MyMathGame() {
        this.currentScore = 0;
    }

    public int getCurrentScore(){
        return currentScore;
    }

    public void addOneToCurrentScore()
    {
        currentScore++;
    }

    public void addTwoToCurrentScore()
    {
        currentScore = currentScore + 2;
    }

    public void subtractOneFromCurrentScore()
    {
        if(currentScore >= 1)
        {
            currentScore--;
        }
    }
}
