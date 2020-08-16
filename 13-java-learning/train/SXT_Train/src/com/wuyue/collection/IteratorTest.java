package com.wuyue.collection;

import java.util.*;

public class IteratorTest {
    public static void main(String[] args) {
//        iterList();
//        iterMap1();
        iterMap2();
    }

    public static void iterList() {
        List<String> list = new ArrayList<>();
        list.add("WY");
        list.add("KWY");
        list.add("LZC");
        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            String temp = iter.next();
            System.out.println(temp);
        }
    }

    public static void iterMap1() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "WY");
        map.put(2000, "KKR");
        map.put(3000, "LZC");
        map.put(4000, "ZLF");
        Set<Integer> keySet = map.keySet();
        for (Iterator<Integer> iter = keySet.iterator(); iter.hasNext(); ) {
            Integer key = iter.next();
            System.out.println(key + " = " + map.get(key));
        }
    }

    public static void iterMap2() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "WY");
        map.put(2000, "KKR");
        map.put(3000, "LZC");
        map.put(4000, "ZLF");
        Set<Map.Entry<Integer, String>> entry = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iter = entry.iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, String> temp = iter.next();
            System.out.println(temp.getKey() + " = " + temp.getValue());
        }
    }
}
