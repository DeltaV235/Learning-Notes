package com.deltav.classloader;

import sun.security.ec.CurveDB;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class ClassloaderTest {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("java.lang.Object.class.getClassLoader() = " + Object.class.getClassLoader());
        System.out.println("sun.security.ec.CurveDB.class.getClassLoader() = " + CurveDB.class.getClassLoader());
        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.deltav.classloader.User");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int[] intArray = new int[10];
        System.out.println("intArray.getClass().getClassLoader() = " + intArray.getClass().getClassLoader());

        User[] users = new User[10];
        System.out.println("users.getClass().getClassLoader() = " + users.getClass().getClassLoader());

        Integer integer = Integer.valueOf(280);
        Integer integer1 = Integer.valueOf(280);
        System.out.println("integer.hashCode() = " + integer.hashCode());
        System.out.println("integer1.hashCode() = " + integer1.hashCode());
        System.out.println("integer = " + integer);
        System.out.println("integer1 = " + integer1);

        String str1 = new String("test");
        String str2 = new String("test");
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
    }
}
