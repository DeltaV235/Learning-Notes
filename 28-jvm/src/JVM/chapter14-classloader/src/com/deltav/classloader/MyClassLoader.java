package com.deltav.classloader;

import java.io.*;

/**
 * custom classloader
 *
 * @author DeltaV235
 * @version 1.0
 */
public class MyClassLoader extends ClassLoader {
    private String rootPath;

    public MyClassLoader(ClassLoader parent, String rootPath) {
        super(parent);
        this.rootPath = rootPath;
    }

    public MyClassLoader(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) {
        final String fileName = rootPath + File.separator + name + ".class";
        byte[] byteCode;
        try {
            ByteArrayOutputStream byteArrayOutputStream;
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName))) {
                byteArrayOutputStream = new ByteArrayOutputStream();

                int length;
                byte[] buffer = new byte[1024];

                while ((length = bufferedInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, length);
                }
            }

            byteCode = byteArrayOutputStream.toByteArray();
            return defineClass(null, byteCode, 0, byteCode.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
