package com.deltav.lock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * create Consumer thread and Producer thread to test custom {@link com.deltav.lock.NonReentrantLock}.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/10 15:01
 */
public class ConsumerAndProducer {
    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {

                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }

                    queue.add("element");
//                    System.out.println("element put into queue");
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lock.lock();
                try {
                    while (0 == queue.size()) {
                        notFull.await();
                    }
                    String element = queue.poll();
//                    System.out.println("element = " + element);
                    notEmpty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread monitor = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("queue size: " + queue.size());
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        monitor.start();
        producer.start();
        consumer.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }

        monitor.interrupt();
        producer.interrupt();
        consumer.interrupt();
    }
}
