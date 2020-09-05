package com.wuyue.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest01 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        System.out.println(ClassLoader.getSystemClassLoader());
//        System.out.println(ClassLoader.getSystemClassLoader().getParent());
//        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
//
//        System.out.println("---------------------------------------------------------------------");
//        System.out.println(System.getProperty("java.class.path"));
//        System.out.println(String.class.getClassLoader());
        // 自定义类加载器
        FileSystemClassLoader loader = new FileSystemClassLoader(".");
        Class<?> clz = loader.load("com.test.loaderTest.HelloWorld");
        System.out.println(clz);
        System.out.println(clz.getClassLoader());
        Object obj = clz.getConstructor(null).newInstance(null);
        Method main = clz.getMethod("main", String[].class);
        main.invoke(obj, (Object) new String[]{});
        Class<?> hwClass = Class.forName("com.wuyue.classLoader.HelloWorld");
        System.out.println(clz.hashCode());
        System.out.println(hwClass.hashCode());
    }
}
