package com.example.mathgame;

public class Score {

    public int currentScore;

    public Score() {
        currentScore = 0;
    }

    public int getCurrentScore() {
        return currentScore;
    }


    public void addScore(int addToScore)
    {
        currentScore = currentScore + addToScore;
    }

    public void lowerTheScore(int lowerTheScore)
    {
        if(currentScore >= lowerTheScore)
        {
            currentScore = currentScore - lowerTheScore;
        }
        else
        {
            currentScore = 0;
        }
    }
}
