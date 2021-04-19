package com.deltav;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Try to get Unsafe instance by using reflection.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/19 20:38
 */
public class UnsafeInstance {

    private final static Unsafe instance;

    static {
        try {
            Class<Unsafe> unsafeClass = Unsafe.class;

            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);

            instance = (Unsafe) theUnsafe.get(null);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Error();
        }
    }

    public static void main(String[] args) {
        System.out.println("unsafe = " + instance);
    }
}
