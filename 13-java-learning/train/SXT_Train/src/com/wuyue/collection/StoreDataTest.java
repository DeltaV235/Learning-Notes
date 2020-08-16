package com.wuyue.collection;

import java.util.*;

public class StoreDataTest {
    public static void main(String[] args) {
        Map<String, Object> row1 = new HashMap<>();
        row1.put("id", 1001);
        row1.put("name", "张三");
        row1.put("age", 20);
        row1.put("salary", 10000);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("id", 1002);
        row2.put("name", "李四");
        row2.put("age", 23);
        row2.put("salary", 15000);

        Map<String, Object> row3 = new HashMap<>();
        row3.put("id", 1003);
        row3.put("name", "王五");
        row3.put("age", 19);
        row3.put("salary", 5000);

        List<Map<String, Object>> table = new ArrayList<>();
        table.add(row1);
        table.add(row2);
        table.add(row3);

        for (Map<String, Object> row : table) {
            Set<String> keyset = row.keySet();
            for (String key : keyset) {
                System.out.print(key + " : " + row.get(key) + "\t");
            }
            System.out.println();
        }
    }
}
