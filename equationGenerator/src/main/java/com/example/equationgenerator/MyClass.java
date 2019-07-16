package com.example.equationgenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MyClass {

    public static void main (String[] dcs)
    {
        Question e = new Question();
        e = e.generateMultiplicationQuestion(4);

        System.out.println();
        System.out.println(e.getQuestion());
        System.out.println(e.getCorrectAnswer());

        for (int i = 0; i < e.getAnswerChoices().length; i++) {
            System.out.print(e.getAnswerChoices()[i] + " ");
        }

    }

    private static Operand pickOp() {
        Operand op;
        Random random = new Random();
        int a = random.nextInt(2);
        if(a == 0){op = Operand.PLUS;}
        else {op = Operand.MINUS;}

        return op;
    }

    private static Operand pickMorD() {
        Operand op;
        Random random = new Random();
        int a = random.nextInt(2);
        if(a == 0){op = Operand.MULTIPLY;}
        else {op = Operand.DIVIDE;}

        return op;
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


    private static int generateNumberInRange(int min, int max) {
        Random random = new Random();
        int a = random.nextInt((max - min) + 1) + min;

        return a;
    }



}
