package com.wuyue.io.FileRWer;

import java.io.*;

/**
 * 测试字节流
 * 批量读取
 *
 * @author DeltaV235
 */
public class FileInput {
    public static void main(String[] args) {
        File src = new File("LICENSE.test");
        Reader inputStream = null;
        try {
            inputStream = new FileReader(src);
            char[] flush = new char[1024];
            int length = -1;            // 返回字节的长度
            while ((length = inputStream.read(flush)) != -1) {
                String outString = new String(flush, 0, length);
                System.out.print(outString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
