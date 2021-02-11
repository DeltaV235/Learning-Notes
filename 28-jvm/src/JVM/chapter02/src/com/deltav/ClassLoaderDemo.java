package com.deltav;

/**
 * 打印 System ClassLoader
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/2/11 22:57
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {

        // sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);

        // sun.misc.Launcher$ExtClassLoader@1b6d3586
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("extClassLoader = " + extClassLoader);

        // null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("parent = " + bootstrapClassLoader);

        // sun.misc.Launcher$AppClassLoader@18b4aac2 <=> appClassLoader
        ClassLoader thisClassLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("thisClassLoader = " + thisClassLoader);

        // null
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println("stringClassLoader = " + stringClassLoader);

    }
}
