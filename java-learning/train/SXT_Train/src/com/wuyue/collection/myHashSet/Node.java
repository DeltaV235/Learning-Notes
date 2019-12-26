package com.wuyue.collection.myHashSet;

public class Node<K, V> {
    private final int hash;
    private K key;
    private V value;
    private Node<K, V> next;

    public Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    int getHash() {
        return hash;
    }

    void setNext(Node<K, V> newNode) {
        this.next = newNode;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }
}
