package com.wuyue.io.ioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试字节流
 * 标准步骤
 *
 * @author DeltaV235
 */
public class FileInputTest02 {
    public static void main(String[] args) {
        File src = new File("LICENSE.test");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(src);
            int temp;
            while ((temp = inputStream.read()) != -1) {
                System.out.print((char) temp);
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
