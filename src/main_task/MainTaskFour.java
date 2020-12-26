package main_task;

import java.util.Scanner;

public class MainTaskFour {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Input a: ");
        int a = input.nextInt();
        System.out.print("Input b: ");
        int b = input.nextInt();
        int c = a+b;
        System.out.print("a + b = " + c + ", a * b = " + a * b);
    }
}
