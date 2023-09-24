package deltav.queue;

import java.util.Scanner;

/**
 * 使用了所有数组位置的数组循环队列。
 * <p>
 * front: 第一个元素的索引，默认值为 0
 * rear: 最后一个元素的后一个索引，默认值为 0
 */
public class CircularArrayQueueDemoEnhance {
    public static void main(String[] args) {
        CircularArrayQueueEnhance queue = new CircularArrayQueueEnhance(4);
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

class CircularArrayQueueEnhance {

    private final int maxSize;
    private final int[] data;
    private int front;
    private int rear;

    // 用于标记队列是否满
    private boolean fullFlag;

    /**
     * default value of front and rear is zero as they are member variable
     *
     * @param maxSize max size of the queue
     */
    public CircularArrayQueueEnhance(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    private boolean isSameIndex() {
        return front == rear;
    }

    /**
     * 如果 rear 的后一个位置就是 front，那么表示队列满
     *
     * @return 队列是否满
     */
    private boolean isFull() {
        return isSameIndex() && fullFlag;
    }

    private boolean isEmpty() {
        return isSameIndex() && !fullFlag;
    }

    public void addQueue(int element) {
        if (isFull()) {
            System.out.println("Queue is full, can not add data!");
            return;
        }
        data[rear] = element;
        rear = (rear + 1) % maxSize;
        if (isSameIndex()) {
            fullFlag = true;
        }
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, no data can be fetched!");
        }
        int retVal = data[front];
        front = (front + 1) % maxSize;
        fullFlag = false;
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
     * <p>
     * case1: size = rear - front <==> (rear - front) % maxSize <==> (rear - front + maxSize) % maxSize
     * 在 front < rear 的情况下，size 就等于 rear - front
     * <p
     * case2: size = maxSize - (front - rear)
     * 在 front > rear 的情况下，size = maxSize - (front - rear) <==> (rear - front + maxSize) % maxSize
     * <p>
     * case3: 当队列满时，front = rear，直接返回队列的大小 maxSize
     *
     * @return queue size
     */
    private int size() {
        if (isFull()) {
            return maxSize;
        } else {
            return (rear - front + maxSize) % maxSize;
        }
    }
}
