package com.wuyue.io.buffer;

import com.wuyue.oop.InnerClassTest;

import java.io.*;

public class BufferTest {
    public static int copy(InputStream is, OutputStream os) {
        byte[] buffer = new byte[1024];
        int size = 0;
        int length = -1;
        try (is; os) {
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
                size += length;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
//            InputStream is = new FileInputStream("Test/testDir/test02/crm.mp4");
//            OutputStream os = new FileOutputStream("C:/Users/DeltaV/Desktop/crm-copy.mp4");
            InputStream is = new BufferedInputStream(new FileInputStream("Test/testDir/test02/crm.mp4"));
            OutputStream os = new BufferedOutputStream(new FileOutputStream("C:/Users/DeltaV/Desktop/crm-copy.mp4"));
            long t1 = System.currentTimeMillis();
            System.out.println(copy(is, os) + " Bytes");
            long t2 = System.currentTimeMillis();
            System.out.println((t2 - t1) + "ms");

//            //file to memory
//            is = new FileInputStream("Test/testDir/test01/1.flv");
//            os = new ByteArrayOutputStream();
//            System.out.println(copy(is, os));
//
//            //memory to file
//            is = new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());
//            os = new FileOutputStream("Test/testDir/test01/255.flv");
//            System.out.println(copy(is, os));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
