package deltav.queue;

import java.util.Scanner;

/**
 * 留了一个空位的数组循环队列。
 * <p>
 * front: 第一个元素的索引，默认值为 0
 * rear: 最后一个元素的后一个索引，默认值为 0
 * <p>
 * 在这种定义下，无法判断数队列还是数组空，故留下了一个数组空位，实现队列空和满的判断。
 */
public class CircularArrayQueueDemo {
    public static void main(String[] args) {
        CircularArrayQueue queue = new CircularArrayQueue(4);
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

class CircularArrayQueue {

    private final int maxSize;
    private final int[] data;
    private int front;
    private int rear;

    /**
     * Default value of front and rear is zero as they are member variable.
     *
     * @param maxSize max size of the queue
     */
    public CircularArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    private boolean isEmpty() {
        return front == rear;
    }

    /**
     * 如果 rear 的后一个位置就是 front，那么表示队列满
     *
     * @return 队列是否满
     */
    private boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void addQueue(int element) {
        if (isFull()) {
            System.out.println("Queue is full, can not add data!");
            return;
        }
        data[rear] = element;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, no data can be fetched!");
        }
        int retVal = data[front];
        front = (front + 1) % maxSize;
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
//        for (int i = front; i < front + size(); i++) {
//            System.out.printf("data[%d] = %d\n", i % maxSize, data[i % maxSize]);
//        }
        int i = front;
        while (i != rear) {
            System.out.printf("data[%d] = %d\n", i, data[i]);
            i = (i + 1) % maxSize;
        }
    }

    /**
     * 计算队列中的有效的数据量。
     * <p>
     * case1: size = rear - front <==> (rear - front) % maxSize <==> (rear - front + maxSize) % maxSize
     * 在 front < rear 的情况下，size 就等于 rear - front
     * <p
     * case2: size = maxSize - (front - rear)
     * 在 front > rear 的情况下，size = maxSize - (front - rear) <==> (rear - front + maxSize) % maxSize
     *
     * @return queue size
     */
    private int size() {
        return (rear - front + maxSize) % maxSize;
    }
}
