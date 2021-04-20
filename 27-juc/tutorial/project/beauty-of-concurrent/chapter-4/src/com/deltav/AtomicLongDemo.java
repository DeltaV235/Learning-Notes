package com.deltav;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong test.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/20 20:48
 */
public class AtomicLongDemo {
    private static final AtomicLong atomic = new AtomicLong(0);
    private static final int THREAD_COUNT = 1000;
    private static Long unThreadSafe = Long.valueOf(0);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<FutureTask<Long>> futureTasks = new ArrayList<>(THREAD_COUNT);
        List<Thread> threads = new ArrayList<>(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            FutureTask<Long> futureTask = new FutureTask<>(new Task());
            futureTasks.add(futureTask);

            Thread thread = new Thread(futureTask);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        int count = 0;
        for (FutureTask<Long> futureTask : futureTasks) {
            Long result = futureTask.get();
            System.out.println(++count + " : " + result);
        }

        System.out.println("atomic.get() = " + atomic.get());
        System.out.println("unThreadSafe = " + unThreadSafe);
    }

    static class Task implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            unThreadSafe++;
            return atomic.incrementAndGet();
        }
    }
}
