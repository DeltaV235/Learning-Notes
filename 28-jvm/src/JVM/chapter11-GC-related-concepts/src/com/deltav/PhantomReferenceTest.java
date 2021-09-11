package com.deltav;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/11 13:58
 */
public class PhantomReferenceTest {
    public static PhantomReferenceTest obj;
    public static ReferenceQueue<PhantomReferenceTest> phantomQueue;

    public static void main(String[] args) {
        CheckRefQueue checkRefQueueThread = new CheckRefQueue();
        checkRefQueueThread.setDaemon(true);
        checkRefQueueThread.start();

        phantomQueue = new ReferenceQueue<>();
        PhantomReference<PhantomReferenceTest> reference = new PhantomReference<>(new PhantomReferenceTest(), phantomQueue);

        try {
            System.out.println(reference.get());
            System.out.println("first GC");
            System.gc();

            TimeUnit.SECONDS.sleep(1);
            if (obj == null) {
                System.out.println("obj is null");
            } else {
                System.out.println("obj is available");
            }

            obj = null;
            System.out.println("2nd GC");
            System.gc();
            TimeUnit.SECONDS.sleep(1);

            if (obj == null) {
                System.out.println("obj is null");
            } else {
                System.out.println("obj is available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method has been called");
        obj = this;
    }

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<PhantomReferenceTest> objt = null;
                    try {
                        objt = (PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objt != null) {
                        System.out.println("object has been removed!");
                    }
                }
            }
        }
    }
}
