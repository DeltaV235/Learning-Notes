package com.wuyue.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        System.out.println(collection.isEmpty());
        collection.add("TestString");
        System.out.println(collection.size());
        collection.remove("testString");
        System.out.println(collection.size());
    }
}
