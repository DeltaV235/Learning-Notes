package com.wuyue.io.fileIOStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试字节流
 * 批量读取
 *
 * @author DeltaV235
 */
public class FileInputTest03 {
    public static void main(String[] args) {
        File src = new File("LICENSE.test");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(src);
            byte[] flush = new byte[1024];
            int length = -1;            // 返回字节的长度
            int temp;
            while ((length = inputStream.read(flush)) != -1) {
                String outString = new String(flush, 0, length);
                System.out.println(outString);
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
