package com.deltav;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/10 0:02
 */
public class RefCountGC {
    private byte[] bigSize = new byte[5 * 1024 * 1024];

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        System.gc();
    }
}
