package com.wuyue.io.FileRWer;

import java.io.*;

/**
 * 测试文件输出流的使用
 *
 * @author DeltaV235
 */
public class FileOutput {
    public static void main(String[] args) {
        File dest = new File("Test/testDir/dest.test");
        Writer os = null;
        try {
            os = new FileWriter(dest, true);
            String destString = "不要打我啊啊啊啊啊\n";
            char[] output = destString.toCharArray();
//            os.write(output, 0, output.length);
//            os.write(destString);
            os.write(output);
            os.append("测试").append("\n测试2");
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
