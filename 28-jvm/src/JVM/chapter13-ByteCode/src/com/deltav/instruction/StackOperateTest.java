package com.deltav.instruction;

/**
 * 操作数栈管理指令
 *
 * @author DeltaV235
 * @version 1.0
 */

public class StackOperateTest {

    private long index = 0;

    public void print() {
        Object obj = new Object();
        String info = obj.toString();
    }

    public void foo() {
        bar();
    }

    public long bar() {
        return 0;
    }

    public long nextIndex() {
        return index++;
    }

}
