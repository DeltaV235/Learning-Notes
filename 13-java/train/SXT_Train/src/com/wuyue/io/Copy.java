package com.wuyue.io;

import java.io.*;

public class Copy {
    public static int copy(InputStream is, OutputStream os) {
        byte[] buffer = new byte[1024];
        int size = 0;
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
                size += length;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(os, is);
        }
        return size;
    }

    public static void close(Closeable... stream) {
        for (Closeable s : stream) {
            try {
                if (s != null)
                    s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            //file to file
            InputStream is = new FileInputStream("Test/testDir/test01/1.flv");
            OutputStream os = new FileOutputStream("Test/testDir/test01/0.flv");
            copy(is, os);

            //file to memory
            is = new FileInputStream("Test/testDir/test01/1.flv");
            os = new ByteArrayOutputStream();
            copy(is, os);

            //memory to file
            is = new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());
            os = new FileOutputStream("Test/testDir/test01/255.flv");
            copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
