package main_task;

import java.util.Random;

public class MainTaskThree {
    public static void main(String[] args) {
        int n = 10;
        int[] randomInt = new int[n];
        Random random = new Random();
        for (int i=0; i<n; i++){
            randomInt[i] = random.nextInt(100);
            System.out.println(randomInt[i]);
        }
        for (int i=0; i<n; i++){
            System.out.print(randomInt[i] + " ");
        }
    }
}
