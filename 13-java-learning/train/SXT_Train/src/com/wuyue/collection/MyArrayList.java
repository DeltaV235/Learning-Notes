package com.wuyue.collection;

import java.util.Arrays;

/**
 * 自己实现的ArrayList
 *
 * @author DeltaV235
 */
public class MyArrayList<E> {
    private Object[] elementData;
    private int size;

    private final static int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        elementData = new Object[capacity];
    }

    public void add(E element) {
        if (size == elementData.length) {
            Object[] newArray = new Object[size << 1];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
        elementData[size++] = element;
    }

    public E get(int index) {
        checkRange(index);
        return (E) elementData[index];
    }

    public void set(int index, E element) {
        checkRange(index);
        elementData[index] = element;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(size);
    }

    public void remove(int index) {
        checkRange(index);
        int moveCount = size - index - 1;
        if (moveCount > 0)
            System.arraycopy(elementData, index + 1, elementData, index, moveCount);
        elementData[--size] = null;
    }

    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]).append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>(20);
        for (int i = 0; i < 20; i++)
            arrayList.add(i);
        System.out.println(arrayList);
        arrayList.remove(Integer.valueOf(3));
        System.out.println(arrayList);
    }
}
