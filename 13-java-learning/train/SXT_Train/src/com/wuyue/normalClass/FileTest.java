package com.wuyue.normalClass;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest {
    public static void main(String[] args) throws IOException {
//        File f1 = new File(System.getProperty("user.dir"));
//        System.out.println(f1);
//        System.out.println(f1.exists());
//        System.out.println(f1.isDirectory());
//        System.out.println(new Date(f1.lastModified()));
//        File f2 = new File(System.getProperty("user.dir") + "/testfile.txt");
//        System.out.println(f2);
//        System.out.println(f2.createNewFile());
//        System.out.println(f2.length());
//        System.out.println(f2.getName());
//        System.out.println(f2.getAbsolutePath());
//        System.out.println(f2.delete());
//        File f3 = new File("e:/wuyue/Learning-notes/java-learning/train/SXT_Train/testdir/File");
//        System.out.println(f3.mkdirs());
//        System.out.println(f3.delete());
        File file = new File("e:/System Volume Information");
        System.out.println("file = " + file);
        System.out.println("file.toString() = " + file.toString());
        System.out.println("file.getName = " + file.getName());
        System.out.println("file.exists() = " + file.exists());
        System.out.println("file.list() = " + file.list());
    }
}
