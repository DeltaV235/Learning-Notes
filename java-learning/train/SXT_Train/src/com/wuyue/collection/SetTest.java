package com.wuyue.collection;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.add("KWY");
        set1.add("WY");
        set1.add(null);
        System.out.println("set1 = " + set1);

        Set<String> set2 = new HashSet<>();
        set2.add("LYH");
        set2.add("LZC");
        set2.addAll(set1);
        System.out.println("set2.addALL(set1) = " + set2);

        set1.remove("WY");
        System.out.println("set1.remove(\"WY\") = " + set1);
        set2.add("WY");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("set1 = " + set1 + "\nset2 = " + set2);
        set1.retainAll(set2);
        System.out.println("set1 = " + set1);
        set1.removeAll(set2);
        System.out.println("set1 = " + set1);

    }
}
