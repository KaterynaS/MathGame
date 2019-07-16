package com.example.equationgenerator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Question {

    private String question;
    private int correctAnswer;
    private int[] answerChoices; // = {12, 4, 5, 2};
    ArrayList<Integer> answersToChoose = new ArrayList<Integer>();

    public Question() { }


    public Question(String question, int answer, int[] choices) {
        this.question = question;
        this.correctAnswer = answer;
        this.answerChoices = choices;
    }

    public Question(String question, int correctAnswer, ArrayList<Integer> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answersToChoose = answers;
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int[] getAnswerChoices() {
        return answerChoices;
    }

    public Question generateMultiplicationQuestion(int maxAnswer) {

        int firstNumber = generateNumberInRange(0, maxAnswer);
        int secondNumber = generateNumberInRange(0, maxAnswer);

        int correctAnswer;

        String template = "{0} * {1} =";
        String questionText = MessageFormat.format(template, firstNumber, secondNumber);

        correctAnswer = firstNumber*secondNumber;
        int[] answers = generateAnswerChoices(correctAnswer);

        return new Question(questionText, correctAnswer, answers);
    }

    private static int generateNumberInRange(int min, int max) {
        Random random = new Random();
        int a = random.nextInt((max - min) + 1) + min;

        return a;
    }

    private static int[] generateAnswerChoices(int correctAnswer)
    {
        int[] answerChoices = new int[4];
        int correctAnswerIndex = generateNumberInRange(0,3);

        Integer[] arr = new Integer[9];
        for (int k = 0; k < arr.length; k++) {
            if(k==correctAnswer) {arr[k] = 9;}
            else { arr[k] = k;}
        }
        Collections.shuffle(Arrays.asList(arr));
        Arrays.toString(arr);

        for (int i = 0; i < answerChoices.length; i++) {
            if (i == correctAnswerIndex) {answerChoices[i] = correctAnswer;}
            else { answerChoices[i] = arr[i]; }
        }

        return answerChoices;
    }


}
