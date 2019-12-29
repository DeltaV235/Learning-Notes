package com.wuyue.io.byteArray;

import java.io.*;

public class FileToByte {
    public static byte[] fileToByte(String path) {
        File src = new File(path);
        InputStream is = null;
        ByteArrayOutputStream os = null;
        int length = -1;
        byte[] flush = new byte[1024];
        try {
            is = new FileInputStream(src);
            os = new ByteArrayOutputStream();
            while ((length = is.read(flush)) != -1) {
                os.write(flush, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return os.toByteArray();
    }

    public static void byteToFile(byte[] bytes, String path) {
        File dest = new File(path);
        ByteArrayInputStream is = null;
        FileOutputStream os = null;
        byte[] flush = new byte[1024];
        int length = -1;
        try {
            is = new ByteArrayInputStream(bytes);
            os = new FileOutputStream(dest);
            while ((length = is.read(flush)) != -1) {
                os.write(flush, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null)
                    os.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        byte[] data = fileToByte("Test/testDir/test01/1.flv");
        System.out.println(data.length);
        byteToFile(data, "Test/testDir/test01/Byte.flv");
    }
}
