package com.wuyue.classLoader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileSystemClassLoader extends ClassLoader {
    private String rootPath;

    public FileSystemClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }

    public Class<?> load(String className) {
        String fullName = rootPath + (rootPath.indexOf(rootPath.length() - 1) == '/' ? "" : "/") +
                className.replace('.', '/');
        Class<?> clz = null;
        try {
            clz = this.getParent().loadClass(fullName);
            if (clz != null)
                return clz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            byte[] classFileByteArray = FileUtils.readFileToByteArray(new File(fullName + ".class"));
            clz = defineClass(className, classFileByteArray, 0, classFileByteArray.length);
            if (clz != null)
                return clz;
            else throw new ClassNotFoundException();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
