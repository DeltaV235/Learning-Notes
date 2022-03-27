package com.deltav.classloader;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("chapter14-classloader\\src\\com\\deltav\\classloader");
        try {
            Class<?> user = myClassLoader.loadClass("User");
            System.out.println(user.getClassLoader().getClass().getName());
            System.out.println(user.getClassLoader().getParent().getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
