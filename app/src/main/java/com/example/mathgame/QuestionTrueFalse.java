package com.example.mathgame;

import java.text.MessageFormat;
import java.util.Random;

public class QuestionTrueFalse implements Question {

    private String question;
    private boolean answerTrue;

    public QuestionTrueFalse() {

    }

    public QuestionTrueFalse(String question, boolean answerTrue) {
        this.question = question;
        this.answerTrue = answerTrue;
    }


    public String getQuestion() { return question; }

    public boolean isAnswerTrue() {
        return answerTrue;
    }


    @Override
    public String toString() {
        return "Is " + question + " True?";
    }


    public QuestionTrueFalse generateArithmeticQuestion()
    {
        QuestionTrueFalse question = new QuestionTrueFalse();

        Operand operand = pickOperand();

        switch (operand)
        {
            case PLUS:
                question = generatePlusQuestionAndAnswer();
                break;
            case MINUS:
                question = generateMinusQuestionAndAnswer();
                break;
            default: question = new QuestionTrueFalse("1+2=3", true);
        }

        return question;
    }


    private QuestionTrueFalse generateMinusQuestionAndAnswer()
    {
        int rangeMin = 0;
        int rangeMax = 9;

        int a = generateNumberInRange(rangeMin, rangeMax);
        int b = generateNumberInRange(rangeMin, rangeMax);

        int firstNumber;
        int secondNumber;

        //todo find swap function
        //largerFirst
        if(b>a)
        {
            firstNumber = b;
            secondNumber = a;
        }
        else
        {
            firstNumber = a;
            secondNumber = b;
        }

        int realAnswer = firstNumber - secondNumber;
        boolean answer = true;

        //generate answerToShow, which is true 50%
        int answerToShow = generateNumberInRange(0,1);
        if(answerToShow == 0){answerToShow = firstNumber - secondNumber;}
        else {answerToShow = generateNumberInRange(0, 9);}

        if(realAnswer == answerToShow) {answer = true;}
        else {answer = false;}

        String template = "{0} - {1} = {2}";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber, answerToShow);

        return new QuestionTrueFalse(questionText, answer);
    }

    private QuestionTrueFalse generatePlusQuestionAndAnswer() {
        int rangeMin = 0;
        int rangeMax = 9;

        int firstNumber = generateNumberInRange(rangeMin, rangeMax);
        int secondNumber = generateNumberInRange(rangeMin, rangeMax);

        int realAnswer = firstNumber + secondNumber;
        boolean answer = true;

        //generate answerToShow, which is true 50%
        int answerToShow = generateNumberInRange(0,1);
        if(answerToShow == 0){answerToShow = firstNumber + secondNumber;}
        else {answerToShow = generateNumberInRange(firstNumber,secondNumber+firstNumber);}

        if(realAnswer == answerToShow) {answer = true;}
        else {answer = false;}

        String template = "{0} + {1} = {2}";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber, answerToShow);

        return new QuestionTrueFalse(questionText, answer);
    }

    private Operand pickOperand() {

        Operand op;
        Random random = new Random();
        int a = random.nextInt(2);
        if(a == 0){op = Operand.PLUS;}
        else {op = Operand.MINUS;}

        return op;
    }

    private int generateNumberInRange(int min, int max) {

        Random random = new Random();
        int a = random.nextInt((max - min) + 1) + min;

        return a;
    }

    @Override
    public String getQuestionText() {
        return question;
    }

}
