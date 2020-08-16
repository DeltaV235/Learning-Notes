package com.wuyue.io;

import java.io.File;

public class DirDemo01 {
    public static void main(String[] args) {
//        File file = new File("testDir/test01");
//        System.out.println(file.mkdirs());
//        file = new File("testDir/test02");
//        System.out.println(file.mkdir());
        File file = new File(System.getProperty("user.dir"));
        String[] subNames = file.list();
        for (String name : subNames) {
            System.out.println(name);
        }
        System.out.println("-----------------------------------");
        File[] listFile = file.listFiles();
        for (File subFiles : listFile) {
            System.out.println(subFiles.getPath());
        }
        File[] roots = File.listRoots();
        for (File root:roots)
            System.out.println(root.getPath());
    }
}
