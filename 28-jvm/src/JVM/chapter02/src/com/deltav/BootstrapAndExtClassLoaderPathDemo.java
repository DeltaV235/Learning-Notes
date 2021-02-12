package com.deltav;

import com.sun.java.accessibility.util.EventID;
import sun.misc.Launcher;

import java.net.URL;

/**
 * 测试 Bootstrap 和 Ext ClassLoader 的加载路径
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/2/12 13:16
 */
public class BootstrapAndExtClassLoaderPathDemo {
    public static void main(String[] args) {
        System.out.println("Bootstrap ClassLoader load path:");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }

        System.out.println("\nExt ClassLoader load path:");
        String property = System.getProperty("java.ext.dirs");
        for (String path : property.split(";")) {
            System.out.println(path);
        }

        ClassLoader classLoader = EventID.class.getClassLoader();
        System.out.println("\nClass EventID ClassLoader: " + classLoader);
    }
}
