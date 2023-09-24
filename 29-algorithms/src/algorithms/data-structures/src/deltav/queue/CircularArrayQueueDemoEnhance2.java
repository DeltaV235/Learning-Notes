package deltav.queue;

import java.util.Scanner;

/**
 * 使用了所有数组位置的数组循环队列。
 * <p>
 * front: 第一个元素的索引，默认值为 0
 * rear: 最后一个元素的索引，默认值为 -1
 * enqueue -> (rear + 1) % maxSize -> set data
 * dequeue -> get data -> (front + 1) % maxSize
 * <p>
 * 由于无法通过索引来区分队列的满和空，故引入 queueSize 变量用于记录队列的大小
 * rear + 1 = front 的情况下，可能时队列空，也可能时队列满
 */
public class CircularArrayQueueDemoEnhance2 {
    public static void main(String[] args) {
        CircularArrayQueueEnhance2 queue = new CircularArrayQueueEnhance2(4);
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
                    queue.enqueue(element);
                    break;
                case 'g':
                    try {
                        int data = queue.dequeue();
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

class CircularArrayQueueEnhance2 {

    private final int maxSize;
    private final int[] data;
    private int front;
    private int rear;
    // 记录队列的长度
    private int queueSize;

    /**
     * default value of front and rear is zero as they are member variable
     *
     * @param maxSize max size of the queue
     */
    public CircularArrayQueueEnhance2(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        front = 0;
        rear = -1;
        queueSize = 0;
    }

    private boolean isFull() {
        return queueSize == maxSize;
    }

    private boolean isEmpty() {
        return queueSize == 0;
    }

    public void enqueue(int element) {
        if (isFull()) {
            System.out.println("Queue is full, can not add data!");
            return;
        }
        rear = (rear + 1) % maxSize;
        data[rear] = element;
        queueSize++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, no data can be fetched!");
        }
        int retVal = data[front];
        front = (front + 1) % maxSize;
        queueSize--;
        return retVal;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, no data can be fetched!");
        }
        return data[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, no data can be fetched!");
            return;
        }
        System.out.println();
        for (int i = front; i < front + size(); i++) {
            System.out.printf("data[%d] = %d\n", i % maxSize, data[i % maxSize]);
        }
//        int i = front;
//        while (i != rear) {
//            System.out.printf("data[%d] = %d\n", i, data[i]);
//            i = (i + 1) % maxSize;
//        }
    }

    /**
     * 计算队列中的有效的数据量
     */
    private int size() {
        return queueSize;
    }
}
