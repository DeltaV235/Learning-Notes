package com.deltav;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>(512, 1f);
        Set<String> linkedHashSet = new LinkedHashSet<>(512, 1f);

        for (int i = 0; i < 300; i++) {
            if (i % 2 == 0)
                continue;
            hashSet.add(String.valueOf(i));
        }

        for (int i = 0; i < 300; i++) {
            if (i % 2 == 0)
                continue;
            linkedHashSet.add(String.valueOf(i));
        }

        System.out.println("HashMap: ");
        for (String i : hashSet) {
            System.out.println("i = " + i);
        }
        System.out.println("LinkedHashMap: ");
        for (String i : linkedHashSet) {
            System.out.println("i = " + i);
        }
    }
}
