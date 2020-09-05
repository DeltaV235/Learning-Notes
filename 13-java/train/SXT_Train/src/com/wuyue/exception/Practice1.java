package com.wuyue.exception;

import java.util.Scanner;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println("Please enter a score (between 0 to 100): ");
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        try {
            if (score < 0 || score >100)
                throw new InputException("分数必须在0—100之间");
            else
                System.out.println("Score is " + score);
        }catch (InputException e){
            e.printStackTrace();
        }
    }
}

class InputException extends Exception {
    public InputException() {}
    public InputException(String msg) {
        super(msg);
    }
}
