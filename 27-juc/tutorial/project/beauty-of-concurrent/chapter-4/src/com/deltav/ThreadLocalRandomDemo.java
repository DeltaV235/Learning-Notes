package com.deltav;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demo code to test ThreadLocalRandom Util.
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/19 21:58
 */
public class ThreadLocalRandomDemo {
    private static ThreadLocalRandom random;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Thread currentThread = Thread.currentThread();
        Class<Thread> threadClass = Thread.class;
        Field threadLocalRandomSeed = threadClass.getDeclaredField("threadLocalRandomSeed");
        threadLocalRandomSeed.setAccessible(true);
        long randomSeed = (long) threadLocalRandomSeed.get(currentThread);
        System.out.println("randSeed initial = " + randomSeed);

        random = ThreadLocalRandom.current();
        randomSeed = (long) threadLocalRandomSeed.get(currentThread);
        System.out.println("randSeed initial = " + randomSeed);

        int randomNumber = random.nextInt(1000);

        System.out.println("randomNumber = " + randomNumber);
    }
}
