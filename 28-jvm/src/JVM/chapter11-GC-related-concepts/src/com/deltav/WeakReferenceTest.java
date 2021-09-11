package com.deltav;

import java.lang.ref.WeakReference;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/9/11 13:18
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<User> userWeakReference = new WeakReference<>(new User(1, "deltav"));
        System.out.println(userWeakReference.get());

        System.gc();
        System.out.println("After GC:");
        System.out.println(userWeakReference.get());
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
