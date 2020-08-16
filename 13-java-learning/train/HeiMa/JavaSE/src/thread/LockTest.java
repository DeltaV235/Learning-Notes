package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        BuyTicket bt = new BuyTicket(100);
        new Thread(bt, "wy").start();
        new Thread(bt, "kwy").start();
    }


}

class BuyTicket implements Runnable {
    private int tickets;
    private Lock lock = new ReentrantLock();

    public BuyTicket(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
                lock.lock();
                if (tickets <= 0)
                    break;
                System.out.println(Thread.currentThread().getName() + "买走了第" + this.tickets-- + "张票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
