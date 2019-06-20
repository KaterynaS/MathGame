package com.example.mathgame;

import java.text.MessageFormat;
import java.util.Random;

public class QuestionParent {

    private String questionText;
    private int correctAnswer;


    public QuestionParent(String questionText, int correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public boolean checkAnswer(int userInput)
    {
        boolean isCorrect;
        if(userInput == correctAnswer) { isCorrect = true; }
        else { isCorrect = false; }
        return isCorrect;
    }


    public QuestionParent generateArithmeticQuestion()
    {
        QuestionParent question;

        Operand operand = pickOperand();

        switch (operand)
        {
            case PLUS:
                question = generatePlusQuestion();
                break;
            case MINUS:
                question = generateMinusQuestion();
                break;
            default: question = new QuestionParent("1 + 2 = ", 3);
        }

        return question;
    }


    private QuestionParent generatePlusQuestion() {
        int rangeMin = 0;
        int rangeMax = 9;

        int firstNumber = generateNumberInRange(rangeMin, rangeMax);
        int secondNumber = generateNumberInRange(rangeMin, rangeMax);

        int realAnswer = firstNumber + secondNumber;

        //generate answerToShow, which is true 50%
        int answerToShow = generateNumberInRange(0,1);
        if(answerToShow == 0){answerToShow = firstNumber + secondNumber;}
        else {answerToShow = generateNumberInRange(firstNumber,secondNumber+firstNumber);}

        String template = "{0} + {1} = {2}";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber, answerToShow);

        return new QuestionParent(questionText, realAnswer);
    }

    private QuestionParent generateMinusQuestion()
    {
        int rangeMin = 0;
        int rangeMax = 9;

        int a = generateNumberInRange(rangeMin, rangeMax);
        int b = generateNumberInRange(rangeMin, rangeMax);

        int firstNumber;
        int secondNumber;

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

        //generate answerToShow, which is true 50%
        int answerToShow = generateNumberInRange(0,1);
        if(answerToShow == 0){answerToShow = firstNumber - secondNumber;}
        else {answerToShow = generateNumberInRange(0, 9);}

        String template = "{0} - {1} = {2}";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber, answerToShow);

        return new QuestionParent(questionText, realAnswer);
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




}
