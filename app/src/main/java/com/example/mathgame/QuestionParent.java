package com.example.mathgame;

import java.util.Arrays;
import java.util.Random;

public class QuestionParent {

    private String questionText;
    private int firstNumber;
    private int seconsNumber;
    private Operand operand;
    private int correctAnswer;

    public QuestionParent() {
    }

    public QuestionParent(String questionText, int correctAnswer) {
        this.questionText = questionText; //without answer, variation for True/False should override method getText...
        this.correctAnswer = correctAnswer;
    }

    public QuestionParent(int firstNum, Operand op, int secondNumber, int corrAnswer)
    {
        String operation = "";
        if(op == Operand.PLUS)
        {
            operation = " + ";
        }
        else if (op == Operand.MINUS)
        {
            operation = " - ";
        }

        String qText = firstNum + operation + secondNumber + " = ";

        this.questionText = qText;
        this.correctAnswer = corrAnswer;
    }



    //return String Arithmetic Problem without answer
    public String getArithmeticProblemTextWithoutAnswer()
    {
        return "1 + 2 = ";
    }

    //should only be present in trueFalseQuestions
    //return String Arithmetic Problem with random answer (50% correct answer)
    public String getArithmeticProblemTextWithRandomAnswer()
    {

        //take it from generated ArithmeticProblem numbersArray
        int firstNumber = 0;
        int secondNumber = 0;

        //generate answerToShow, which is true 50%
        int answerToShow = generateNumberInRange(0,1);
        if(answerToShow == 0){answerToShow = firstNumber + secondNumber;}
        else {answerToShow = generateNumberInRange(firstNumber,secondNumber+firstNumber);}

        return "1 + 2 = 3";
    }


    //return String arithmeticProblemText and int correctAnswer
    public QuestionParent generateArithmeticProblem()
    {
        QuestionParent question;
        Operand operand = pickOperand();

        switch (operand)
        {
            case PLUS:
                question = generatePlusQuestionInRange(0,9);
                break;
            case MINUS:
                question = generatePositiveMinusQuestionInRange(0,9);
                break;
            default: question = new QuestionParent("1 + 2 = ", 3);
        }

        return question;
    }


    private QuestionParent generatePlusQuestionInRange(int min, int max)
    {
        int firstNumber = generateNumberInRange(min, max);
        int secondNumber = generateNumberInRange(min, max);
        int realAnswer = firstNumber + secondNumber;

        return new QuestionParent(firstNumber, Operand.PLUS, secondNumber, realAnswer);
    }

    private QuestionParent generatePositiveMinusQuestionInRange(int min, int max)
    {
        int[] arr = {generateNumberInRange(min, max), generateNumberInRange(min, max)};

        //largerFirst - answer should only be positive
        Arrays.sort(arr);

        int firstNumber = arr[1];
        int secondNumber = arr[0];
        int realAnswer = firstNumber - secondNumber;

        return new QuestionParent(firstNumber, Operand.MINUS, secondNumber, realAnswer);
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

    public boolean checkAnswer(int userInput)
    {
        boolean isCorrect;
        if(userInput == correctAnswer) { isCorrect = true; }
        else { isCorrect = false; }
        return isCorrect;
    }



}
