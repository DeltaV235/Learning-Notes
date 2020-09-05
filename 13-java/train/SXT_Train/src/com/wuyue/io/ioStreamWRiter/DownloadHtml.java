package com.wuyue.io.ioStreamWRiter;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 使用转换流处理字节流，使其能以字符形式处理
 */
public class DownloadHtml {
    public static void main(String[] args) {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new URL("http://www.baidu.com").openStream(), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(
                             new FileOutputStream("Test/baidu.html"), StandardCharsets.UTF_8))
        ) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                writer.write(temp);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
