package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static Lock testLock = new ReentrantLock();
    private static Condition finish = testLock.newCondition();
    private static Lock test = new ReentrantLock();
    private static Condition testCondition = test.newCondition();

    public static void main(String[] args) {
        // 创建一个顾客线程(消费者)
        new Thread() {
            @Override
            public void run() {
                //一直等着买包子
                while (true) {
                    testLock.lock();
                    //保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                    System.out.println("顾客1告知老板要的包子的种类和数量");
                    //调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
                    try {
                        finish.await();
//                        testCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒之后执行的代码
                    System.out.println("包子已经做好了,顾客1开吃!");
                    System.out.println("---------------------------------------");
                    testLock.unlock();
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        // 创建一个顾客线程(消费者)
        new Thread() {
            @Override
            public void run() {
                //一直等着买包子
                while (true) {
                    //保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                    testLock.lock();
                    System.out.println("顾客2告知老板要的包子的种类和数量");
                    //调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
                    try {
                        finish.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒之后执行的代码
                    System.out.println("包子已经做好了,顾客2开吃!");
                    System.out.println("---------------------------------------");
                    testLock.unlock();
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                //一直等着买包子
                while (true) {
                    //保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                    testLock.lock();
                    System.out.println("顾客3告知老板要的包子的种类和数量");
                    //调用wait方法,放弃cpu的执行,进入到WAITING状态(无限等待)
                    try {
                        finish.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒之后执行的代码
                    System.out.println("包子已经做好了,顾客3开吃!");
                    System.out.println("---------------------------------------");
                    testLock.unlock();
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //创建一个老板线程(生产者)
        new Thread() {
            @Override
            public void run() {
                //一直做包子
                while (true) {
                    //花了5秒做包子
                    try {
                        Thread.sleep(8000);//花5秒钟做包子
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //保证等待和唤醒的线程只能有一个执行,需要使用同步技术
                    testLock.lock();
                    System.out.println("老板5秒钟之后做好包子,告知顾客,可以吃包子了");
                    //做好包子之后,调用notify方法,唤醒顾客吃包子
                    //obj.notify();//如果有多个等待线程,随机唤醒一个
                    finish.signal();
                    testLock.unlock();
                }
            }
        }.start();
    }
}
