package com.wuyue.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put(1000, "wuyue");
        map.put(1001, "KSM");
        map.put(1002, "TEST");
        System.out.println(map.size());
        System.out.println(map);
        map.remove(1000);
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1004, "bilibili");
        hashMap.put(1005, "BangDream");
        map.putAll(hashMap);
        System.out.println(map);
        map.clear();
        System.out.println(map);
    }
}
