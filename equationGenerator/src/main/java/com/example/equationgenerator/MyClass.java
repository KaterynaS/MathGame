package com.example.equationgenerator;

import java.util.Random;

public class MyClass {

    public static void main (String[] dcs)
    {
        System.out.println("hi");
        for (int i = 0; i < 20; i++) {
            Operand op = pickOp();
            System.out.print(op.name() + "   ");
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


}
