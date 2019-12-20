package com.wuyue.oop;

public class StringTest {
    public static void main(String[] args) {
        String s1 = "core Java";
        String s2 = "Core Java";
        System.out.println(s1.charAt(3));
        System.out.println(s2.length());
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));
        System.out.println(s1.indexOf("Java"));
        System.out.println(s1.indexOf("apple"));
        System.out.println(s1.replace(" ", "&"));

        String s3 = "How are you?";
        System.out.println(s3.startsWith("How"));
        System.out.println(s3.endsWith("you"));
        System.out.println(s3.substring(4));
        System.out.println(s3.substring(4, 7));
        System.out.println(s3.toUpperCase());
        System.out.println(s3.toLowerCase());
        String s4 = new String("  How old are you !!  ");
        System.out.println(s4);
        System.out.println(s4.trim());
    }
}
