package com.deltav;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Test Random and ThreadLocalRandom efficient. Create 50 threads and every thread make 20,000 times loop to generate
 * random number.
 *
 * <p>
 * result:
 * Traditional Random cost 68 ms
 * ThreadLocalRandom cost 13 ms
 * </p>
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/19 22:04
 */
public class ThreadLocalRandomEfficientTest {

    private static final int THREAD_NUMBER = 50;
    private static final int LOOP_COUNT = 20000;
    private static final CountDownLatch latchWithTraditional = new CountDownLatch(THREAD_NUMBER);
    private static final CountDownLatch latchWithThreadLocal = new CountDownLatch(THREAD_NUMBER);

    public static void main(String[] args) throws InterruptedException {
        final List<Thread> traditionalThreads = new ArrayList<>(20);
        final List<Thread> threadLocalThreads = new ArrayList<>(20);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new Thread(new TaskWithTraditionalRandom());
            traditionalThreads.add(thread);
        }

        long startTime = System.currentTimeMillis();
        for (Thread thread : traditionalThreads) {
            thread.start();
        }

        latchWithTraditional.await();

        System.out.println("Traditional Random Time Cost: " + (System.currentTimeMillis() - startTime) + " ms ");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new Thread(new TaskWithThreadLocalRandom());
            threadLocalThreads.add(thread);
        }

        startTime = System.currentTimeMillis();
        for (Thread thread : threadLocalThreads) {
            thread.start();
        }

        latchWithThreadLocal.await();

        System.out.println("ThreadLocalRandom Time Cost: " + (System.currentTimeMillis() - startTime) + " ms ");
    }

    static class TaskWithTraditionalRandom implements Runnable {
        private static final Random random = new Random();

        @Override
        public void run() {
            for (int i = 0; i < LOOP_COUNT; i++) {
                random.nextInt(1000);
            }
            latchWithTraditional.countDown();
        }
    }

    static class TaskWithThreadLocalRandom implements Runnable {
        private static final ThreadLocalRandom random = ThreadLocalRandom.current();

        @Override
        public void run() {
            for (int i = 0; i < LOOP_COUNT; i++) {
                random.nextInt(1000);
            }
            latchWithThreadLocal.countDown();
        }
    }
}
