package com.wuyue.thread;

public class DoubleCheckTest {
    private static volatile DoubleCheckTest instance;

    private DoubleCheckTest() {
        try {
            Thread.sleep(9000); //增加出错概率
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static DoubleCheckTest getInstance() {
        // 如果instance已存在则直接返回，减少线程等待锁的时间，但可能存在指令重拍，导致返回一个未初始化完成的实例
        // instance引用先得到内存地址，而初始化未完成，下方判断直接返回instance
        // 使用volatile来及时更新个线程的副本
        if (instance != null)
            return instance;
        synchronized (DoubleCheckTest.class) {
            if (instance == null) {
                instance = new DoubleCheckTest();
            }
        }
        return instance;
    }

    public void testMethod() {
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(DoubleCheckTest.getInstance());
        }).start();

        Thread.sleep(5000);
        System.out.println(DoubleCheckTest.getInstance());
        DoubleCheckTest.getInstance().testMethod();
    }
}
