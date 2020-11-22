package com.wuyue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并框架 Demo
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/22 12:42
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        forkMethod();
//        long result = 0;
//        for (int i = 0; i <= 60000; i++) {
//            result += i;
//        }
//        System.out.println("result = " + result);
    }

    private static void forkMethod() throws InterruptedException, ExecutionException {
        MyTask myTask = new MyTask(0, 60000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(myTask);
        Integer result = task.get();
        System.out.println("result = " + result);
        forkJoinPool.shutdown();
    }

    static class MyTask extends RecursiveTask<Integer> {

        private static final Integer COMPUTE_THRESHOLD = 10;
        private final int begin;
        private final int end;
        private int retVal;

        public MyTask(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - begin <= COMPUTE_THRESHOLD) {
                for (int i = begin; i <= end; i++) {
                    retVal += i;
                }
            } else {
                int middle = (end + begin) / 2;
                MyTask myTask1 = new MyTask(begin, middle);
                MyTask myTask2 = new MyTask(middle + 1, end);
                myTask1.fork();
                myTask2.fork();
                retVal = myTask1.join() + myTask2.join();
            }
            return retVal;
        }
    }
}
