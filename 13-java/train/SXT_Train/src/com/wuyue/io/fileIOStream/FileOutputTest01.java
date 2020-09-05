package com.wuyue.io.fileIOStream;

import java.io.*;

/**
 * 测试文件输出流的使用
 *
 * @author DeltaV235
 */
public class FileOutputTest01 {
    public static void main(String[] args) {
        File dest = new File("testDir/dest.test");
        OutputStream os = null;
        try {
            os = new FileOutputStream(dest, true);
            String destString = "I\'m Here!\n";
            byte[] output = destString.getBytes();
//            os.write(output, 0, output.length);
            os.write(output);
            os.flush();
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
        }
    }
}
