package com.wuyue.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionTest {
    public static void main(String[] args) {
        FileReader fileReader = null;
        if (fileReader == null)
            try {
                throw new TestException();
            } catch (TestException e) {
                e.printStackTrace();
            }
        try {
            fileReader = new FileReader("E:\\wuyue\\Configuration-Files\\git-config\\gitconfi");
            System.out.println((char)fileReader.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileReader!=null)
                    fileReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("imhere");
    }
}

class TestException extends Exception {
    public TestException() {}
    public TestException(String msg) {
        super(msg);
    }
}
