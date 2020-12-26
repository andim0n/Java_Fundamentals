package main_task;

import java.util.Scanner;

public class MainTaskTwo {
    public static void main(String[] args){
        System.out.print("Input string to reverse: ");
        String inputString = new Scanner(System.in).nextLine();
        System.out.print("Reversed string: " + new StringBuilder(inputString).reverse().toString());
    }
}
