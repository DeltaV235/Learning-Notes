package com.deltav;

import java.util.concurrent.locks.ReentrantLock;

/**
 * test if lock method will throw InterruptedException if thread has been interrupt before lock method invoke.
 * <p>
 * result:
 * thread will not be interrupted before this call start method of this thread.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/7/10 20:52
 */
public class InterruptTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        thread.interrupt();
        thread.start();
    }
}
