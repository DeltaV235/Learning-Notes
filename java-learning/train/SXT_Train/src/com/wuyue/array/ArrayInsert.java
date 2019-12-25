package com.wuyue.array;

public class ArrayInsert {
    public static void main(String[] args) {
        String[] strings = {"Shanghai", "Peking", "Chendu", null};
        insert(strings, "HangKong", 3);
        for (String s : strings) {
            System.out.println(s);
        }
    }

    public static void insert(String[] s, String newString, int index) {
        System.arraycopy(s, index, s, index + 1, s.length - index - 1);
        s[index] = newString;
    }
}
