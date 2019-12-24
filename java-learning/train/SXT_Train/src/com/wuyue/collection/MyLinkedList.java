package com.wuyue.collection;


public class MyLinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public void add(E data) {
        Node<E> newNode = new Node<>(last, data, null);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public void checkRange(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(size);
    }

    public E get(int index) {
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        checkRange(index);
        Node<E> temp;
        if (index < (size >> 1)) {
            temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }
        else {
            temp = last;
            for (int i = size - 1; i > index + 1; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    public void remove(int index) {
        Node<E> temp = getNode(index);
        Node<E> forward = temp.prev;
        Node<E> behind = temp.next;
        if (forward != null)
            forward.next = behind;
        else
            first = behind;
        if (behind != null)
            behind.prev = forward;
        else
            last = forward;
        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node<E> temp = first;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(temp.data).append(",");
            temp = temp.next;
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public void insert(int index, E data) {
        checkRange(index);

        Node<E> temp = getNode(index);
        Node<E> forward = temp.prev;
        Node<E> newNode = new Node<>(forward, data, temp);
        if (forward != null)
            forward.next = newNode;
        else
            first = newNode;
        temp.prev = newNode;
        size++;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 20; i++)
            myLinkedList.add(i);
        System.out.println(myLinkedList.size());
        myLinkedList.add(500);
        System.out.println(myLinkedList);
        myLinkedList.insert(19, -20);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(20));
        myLinkedList.remove(0);
        System.out.println(myLinkedList);
    }
}
