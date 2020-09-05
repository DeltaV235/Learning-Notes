package com.wuyue.collection.myHashMap;

/**
 * 自定义一个HashMap
 *
 * @param <K>
 * @param <V>
 * @author DeltaV235
 */
public class MyHashMap<K, V> {
    private Node<K, V>[] table;         //bucket array
    private int size;

    public MyHashMap() {
        table = new Node[16];
    }

    public void put(K key, V value) {
        if (key == null)
            throw new NullPointerException("Key is null");
        Node<K, V> newNode = new Node<>(hash(key.hashCode(), table.length), key, value, null);
        Node<K, V> pointer = table[newNode.getHash()];
        if (pointer == null) {
            table[newNode.getHash()] = newNode;
            size++;
        } else {
            while (pointer != null) {
                if (pointer.getKey().equals(key)) {
                    pointer.setValue(value);
                    break;
                } else {
                    if (pointer.getNext() == null) {
                        pointer.setNext(newNode);
                        size++;
                        break;
                    }
                }
                pointer = pointer.getNext();
            }
        }
        // TODO 数组扩容
    }

    public V get(K key) {
        int bucketIndex = hash(key.hashCode(), table.length);
        Node<K, V> pointer = table[bucketIndex];
        V value = null;
        while (pointer != null) {
            if (pointer.getKey().equals(key)) {
                value = pointer.getValue();
                break;
            } else
                pointer = pointer.getNext();
        }
        return value;
    }

    public boolean remove(K key) {
        int hash = hash(key.hashCode(), table.length);
        Node<K, V> pointer = table[hash];
        Node<K, V> prevNode = null;
        int bucketIndex = 0;
        if (pointer == null)
            return false;
        while (pointer != null) {
            if (pointer.getKey().equals(key)) {
                if (bucketIndex == 0) {            //链表首元素，直接将该bucket数组指向pointer.getNext();
                    table[hash] = pointer.getNext();
                } else {                            //非链表首元素
                    prevNode.setNext(pointer.getNext());
                }
                size--;
                return true;
            }
            bucketIndex++;
            prevNode = pointer;
            pointer = prevNode.getNext();
        }
        return false;
    }

    private int hash(int v, int length) {
//        System.out.println("hash is " + (v & (length - 1)));
        return v & (length - 1);
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Node<K, V> node : table) {
            while (node != null) {
                sb.append(" ").append(node.getKey()).append(" = ").append(node.getValue()).append(" ,");
                node = node.getNext();
            }
        }
        if (sb.length() != 1)
            sb.setCharAt(sb.length() - 1, '}');
        else
            sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(0, "wuyue");
        myHashMap.put(16, "TEST");
        myHashMap.put(32, "BANGDREAM");
        System.out.println(myHashMap);
        System.out.println(myHashMap.remove(12));
        System.out.println(myHashMap.remove(32));
        System.out.println(myHashMap.remove(0));
        System.out.println(myHashMap.remove(16));
        myHashMap.put(48, "KSM");
        System.out.println(myHashMap);
        System.out.println("Map size:" + myHashMap.size());
    }
}
