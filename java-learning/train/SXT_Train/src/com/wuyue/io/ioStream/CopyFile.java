package com.wuyue.io.ioStream;

import java.io.*;

/**
 * 文件拷贝：文件字节输入、输出流
 */
public class CopyFile {
    public static void main(String[] args) {
        System.out.print("总共复制 ");
        System.out.print(copyFile(new File("testDir/test01/1.flv"), new File("testDir/test02/2.flv")));
        System.out.print(" Byte");
    }

    public static int copyFile(File src, File dest) {
        if (null == src || !src.exists())
            return -1;
        byte[] buffer = new byte[1024];
        InputStream is = null;
        OutputStream os = null;
        int size = 0;
        int length = -1;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
                size += length;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }
}
