package com.wuyue.collection.myHashSet;

import com.wuyue.collection.myHashMap.MyHashMap;

/**
 * 手动实现HashSet
 * @param <E>
 */
public class MyHashSet<E> {
    private MyHashMap<E, Object> hashMap;

    public MyHashSet() {
        hashMap = new MyHashMap<>();
    }

    public void add(E key) {
        hashMap.put(key, null);
    }

    public boolean remove(E key) {
        return hashMap.remove(key);
    }

    public String toString() {
        return hashMap.toString();
    }

    public static void main(String[] args) {
        MyHashSet<String> hashSet = new MyHashSet<>();
        hashSet.add("WY");
        hashSet.add("KWY");
        System.out.println(hashSet);
    }
}
