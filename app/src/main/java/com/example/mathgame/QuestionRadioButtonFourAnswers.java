package com.example.mathgame;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class QuestionRadioButtonFourAnswers implements Question {

    private String question;
    private int correctAnswer;
    private int[] answerChoices; // = {12, 4, 5, 2};
    ArrayList<Integer> answersToChoose = new ArrayList<Integer>();

    public QuestionRadioButtonFourAnswers() { }


    public QuestionRadioButtonFourAnswers(String question, int answer, int[] choices) {
        this.question = question;
        this.correctAnswer = answer;
        this.answerChoices = choices;
    }

    public QuestionRadioButtonFourAnswers(String question, int correctAnswer, ArrayList<Integer> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answersToChoose = answers;
    }

    @Override
    public String getQuestionText() {
        return question;
    }

    public int getCorrectAnswer() {return correctAnswer;}

    public int[] getAnswerChoices()
    {
        return answerChoices;
    }

    public QuestionRadioButtonFourAnswers generateArithmeticQuestion()
    {
        QuestionRadioButtonFourAnswers question;

        Operand operand = pickOperand();
        int[] defaultAnswerChoices = {0,1,2,3};

        switch (operand)
        {
            case PLUS:
                question = generatePlusQuestion();
                break;
            case MINUS:
                question = generateMinusQuestion();
                break;
            default: question = new QuestionRadioButtonFourAnswers("1+2=", 3, defaultAnswerChoices);
        }

        return question;
    }


    private QuestionRadioButtonFourAnswers generateMinusQuestion() {
        int rangeMin = 0;
        int rangeMax = 9;

        int a = generateNumberInRange(rangeMin, rangeMax);
        int b = generateNumberInRange(rangeMin, rangeMax);

        int firstNumber;
        int secondNumber;

        int correctAnswer;

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

        String template = "{0} - {1} =";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber);

        correctAnswer = firstNumber-secondNumber;

        int corrAnsID = generateNumberInRange(0,3);

        int[] answers = generateAnswerChoices(correctAnswer, corrAnsID);

        return new QuestionRadioButtonFourAnswers(questionText, correctAnswer, answers);
    }

    private QuestionRadioButtonFourAnswers generatePlusQuestion() {
        int rangeMin = 0;
        int rangeMax = 9;
        int correctAnswer;

        int firstNumber = generateNumberInRange(rangeMin, rangeMax);
        int secondNumber = generateNumberInRange(rangeMin, rangeMax);

        String template = "{0} + {1} =";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber);

        correctAnswer = firstNumber+secondNumber;
        int corrAnsID = generateNumberInRange(0,3);

        int[] answers = generateAnswerChoices(correctAnswer, corrAnsID);

        return new QuestionRadioButtonFourAnswers(questionText, correctAnswer, answers);
    }


    private int[] generateAnswerChoices(int corrAns, int correctAnswerID)
    {
        int[] ansChoices = new int[4];

        Integer[] arr = new Integer[9];
        for (int k = 0; k < arr.length; k++) {
            if(k==corrAns) {arr[k] = 9;}
            else { arr[k] = k;}
        }
        Collections.shuffle(Arrays.asList(arr));
        Arrays.toString(arr);

        for (int i = 0; i < ansChoices.length; i++) {
            if (i == correctAnswerID) {ansChoices[i] = corrAns;}
            else { ansChoices[i] = arr[i]; }
        }

        return ansChoices;
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
