package com.deltav;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String[] args) {
        // 使用自然顺序
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        System.out.println("TreeMap: " + treeMap);

        // 使用自定义比较器
        TreeMap<Integer, String> reverseTreeMap = new TreeMap<>(Comparator.reverseOrder());
        reverseTreeMap.put(3, "Three");
        reverseTreeMap.put(1, "One");
        reverseTreeMap.put(2, "Two");
        System.out.println("Reverse TreeMap: " + reverseTreeMap);

        // 获取子映射
        System.out.println("SubMap (1, 3): " + treeMap.subMap(1, 3));

        // 获取前缀映射
        System.out.println("HeadMap (2): " + treeMap.headMap(2));

        // 获取后缀映射
        System.out.println("TailMap (2): " + treeMap.tailMap(2));
    }
}
