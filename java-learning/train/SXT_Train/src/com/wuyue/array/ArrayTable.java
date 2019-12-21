package com.wuyue.array;

import java.util.Arrays;

public class ArrayTable {
    public static void main(String[] args) {
        Object[] emp1 = {1000, "wuyue", 20};
        Object[] emp2 = {1002, "kkr", 17};
        Object[] emp3 = {1004, "ars", 16};

        Object[][] table = new Object[3][];
        table[0] = emp1;
        table[1] = emp2;
        table[2] = emp3;

        for (Object[] array:table) {
            System.out.println(Arrays.toString(array));
        }
    }
}
