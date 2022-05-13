package com.deltav.mat.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+HeapDumpBeforeFullGC -XX:HeapDumpPath=./studentTrace.hprof
 *
 * @author DeltaV235
 * @version 1.0
 */
public class StudentTrace {
    private static List<WebPage> webPages = new ArrayList<>();

    public static void main(String[] args) {
        createWebPage();
        Student st3 = new Student(3, "Tom");
        Student st5 = new Student(5, "Jerry");
        Student st7 = new Student(7, "Lily");
        for (int i = 0; i < webPages.size(); i++) {
            if (i % st3.getId() == 0) {
                st3.visit(webPages.get(i));
            }
            if (i % st5.getId() == 0) {
                st5.visit(webPages.get(i));
            }
            if (i % st7.getId() == 0) {
                st7.visit(webPages.get(i));
            }
        }

        webPages.clear();
        System.gc();
    }

    public static void createWebPage() {
        for (int i = 0; i < 100; i++) {
            WebPage webPage = new WebPage();
            webPage.setUrl("http://www." + i + ".com");
            webPage.setContent(Integer.toString(i));
            webPages.add(webPage);
        }
    }

}

