package com.deltav;

import java.lang.ref.SoftReference;

/**
 * JVM parameter:
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/11 11:35
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
//        SoftReference<User> userSoftReference = new SoftReference<>(new User(1, "deltav"));
        User user = new User(1, "deltav");
        SoftReference<User> userSoftReference = new SoftReference<>(user);
        user = null;
        System.out.println(userSoftReference.get());

        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftReference.get());
        try {
            byte[] bytes = new byte[1024 * (7168 - 620)];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(userSoftReference.get());
        }
    }

    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
