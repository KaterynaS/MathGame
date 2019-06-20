package com.example.mathgame;

import android.arch.lifecycle.ViewModel;

public class LevelOneViewModel extends ViewModel {

    public QuestionTrueFalse currentQuestion;

    public QuestionTrueFalse getCurrentQuestion()
    {
        if(currentQuestion == null)
        {
            getNewQuestion();
        }
        return currentQuestion;
    }

    public QuestionTrueFalse getNewQuestion()
    {
        currentQuestion = new QuestionTrueFalse();
        currentQuestion = currentQuestion.generateArithmeticQuestion();

        return currentQuestion;
    }

    public String getCurrentQuestionText()
    {
        return currentQuestion.getQuestionText();
    }

}
