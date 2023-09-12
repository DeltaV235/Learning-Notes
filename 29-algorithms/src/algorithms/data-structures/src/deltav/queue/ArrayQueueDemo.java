package deltav.queue;

import java.util.Scanner;

/**
 * Use array to simulate queue.
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop = true;
        char input;
        while (loop) {
            System.out.println();
            System.out.println("s(show): show queue data");
            System.out.println("a(append): add new data into queue");
            System.out.println("g(get): get data from queue");
            System.out.println("p(peek): show the data at head of queue");
            System.out.println("e(exit): exit the program");

            Scanner scanner = new Scanner(System.in);
            input = scanner.next().charAt(0);
            switch (input) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    int element = scanner.nextInt();
                    queue.addQueue(element);
                    break;
                case 'g':
                    try {
                        int data = queue.getQueue();
                        System.out.printf("data = %d\n", data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int data = queue.peek();
                        System.out.printf("data = %d\n", data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("program exited..");
    }
}

class ArrayQueue {
    private final int maxSize;
    private final int[] data;
    private int front;
    private int rear;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        front = -1;
        rear = -1;
    }

    private boolean isEmpty() {
        return front == rear;
    }

    private boolean isFull() {
        return rear == maxSize - 1;
    }

    public void addQueue(int element) {
        if (isFull()) {
            System.out.println("Queue is full, can not add data!");
            return;
        }
        rear++;
        data[rear] = element;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, no data can be fetched!");
        }
        front++;
        return data[front];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, no data can be fetched!");
        }
        return data[front + 1];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, no data can be fetched!");
            return;
        }
        System.out.println();
        for (int i = 0; i < data.length; i++) {
            System.out.printf("data[%d] = %d\n", i, data[i]);
        }
    }
}
