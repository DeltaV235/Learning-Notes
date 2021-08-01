package com.deltav;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Generate random string in words.txt.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/31 20:09
 */
public class GenerateString {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("words.txt");

        for (int i = 0; i < 100000; i++) {
            int length = (int) (Math.random() * (10 - 1 + 1) + 1);
            fileWriter.write(getString(length) + "\n");
        }

        fileWriter.close();
    }

    public static String getString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            int num = (int) (Math.random() * (90 - 65 + 1) + 65) + (int) (Math.random() * 2) * 32;
            str += (char) num;
        }
        return str;
    }
}
