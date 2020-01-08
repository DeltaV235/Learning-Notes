package thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        Runnable task1 = new task();
        Runnable task2 = new task();
        Runnable task3 = new task();
        Runnable task4 = new task();
        Runnable task5 = new task();

        es.submit(task1);
        es.submit(task2);
        es.submit(task3);
        es.submit(task4);
        es.submit(task5);

        es.shutdown();
    }
}

class task implements Runnable {
    @Override
    public void run() {
        System.out.println(new Date().getTime() + "\t" + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
