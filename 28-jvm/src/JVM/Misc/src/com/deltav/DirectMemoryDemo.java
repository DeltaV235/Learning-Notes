package com.deltav;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * code of testing direct memory in heap area by using NIO.
 * <p>
 * JVM parameter:
 * -Xms100M -Xmx2G
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/6/14 13:42
 */
public class DirectMemoryDemo {
    // 1.5 GIGABYTE
    private static final int BUFFER = (int) (1.5 * 1024 * 1024 * 1024);

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("direct memory has been allocated. Press any key to continue.");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("Starting releasing direct memory");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
