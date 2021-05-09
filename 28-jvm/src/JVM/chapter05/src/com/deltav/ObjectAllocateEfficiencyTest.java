package com.deltav;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Test Object memory allocate efficiency with different TLAB setting in JVM parameter.
 * <p>
 * JVM Parameters:
 * -XX:-UseTLAB -XX:+PrintFlagsFinal -Xms512m -Xmx512m -XX:+PrintGC
 * <p>
 * without TLAB
 * sumTime = 3927 ms
 * avgTime = 7.854 ms
 * <p>
 * with TLAB
 * sumTime = 1799 ms
 * avgTime = 3.598 ms
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/5/4 13:54
 */
public class ObjectAllocateEfficiencyTest {

    private static final int OBJECT_COUNT = 10000;
    private static final int THREAD_COUNT = 500;
    private static final int LIST_CAPACITY = OBJECT_COUNT * THREAD_COUNT;
    private static final List<TestObject> container = new ArrayList<>(LIST_CAPACITY);
    private static final List<FutureTask<Long>> futureTaskList = new ArrayList<>(THREAD_COUNT);
    private static final ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(10,
            THREAD_COUNT,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2 * THREAD_COUNT));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < THREAD_COUNT; i++) {
            FutureTask<Long> longFutureTask = new FutureTask<>(new AllocateThread());
            futureTaskList.add(longFutureTask);
            threadExecutor.execute(longFutureTask);
        }

        long sumTime = 0;
        for (FutureTask<Long> futureTask : futureTaskList) {
            sumTime += futureTask.get();
        }

        threadExecutor.shutdown();

        System.out.println("sumTime = " + sumTime + " ms ");
        System.out.println("avgTime = " + (double) sumTime / THREAD_COUNT + " ms ");
    }

    private static class AllocateThread implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < OBJECT_COUNT; i++) {
                TestObject testObject = new TestObject();
                container.add(testObject);
            }

            return System.currentTimeMillis() - startTime;
        }
    }

    private static class TestObject {
        private static Object obj;
        private int number;
        private String characterArray;
    }
}
