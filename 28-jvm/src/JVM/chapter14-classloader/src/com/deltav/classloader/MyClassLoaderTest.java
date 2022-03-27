package com.deltav.classloader;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("chapter14-classloader\\src\\com\\deltav\\classloader");
        try {
            // 由于在此没有使用全类名，所以自定义类加载器的父类认为该类未被加载，且该类的路径无法被父类加载器加载
            Class<?> user = myClassLoader.loadClass("User");
            System.out.println(user.getClassLoader().getClass().getName());
            System.out.println(user.getClassLoader().getParent().getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
