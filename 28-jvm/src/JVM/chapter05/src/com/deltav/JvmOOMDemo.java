package com.deltav;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * JVM Out Of Memory Demo
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/17 17:03
 */
public class JvmOOMDemo {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>(10000);
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Picture picture = new Picture(new Random().nextInt(1024 * 1024));
            list.add(picture);
        }
    }

    static class Picture {
        private byte[] pixels;

        public Picture(int pixels) {
            this.pixels = new byte[pixels];
        }
    }
}
