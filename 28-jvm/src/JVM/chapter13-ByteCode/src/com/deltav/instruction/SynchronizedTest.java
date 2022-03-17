package com.deltav.instruction;

/**
 * 同步控制指令
 *
 * @author DeltaV235
 * @version 1.0
 */
public class SynchronizedTest {

    private int i = 0;
    private Object obj = new Object();

    public synchronized void add() {
        i++;
    }

    public void subtract() {
        synchronized (obj) {
            i--;
        }
    }
}
