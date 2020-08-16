package com.wuyue.net.tcp.file;

import java.io.*;
import java.net.Socket;

public class FileClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("118.25.46.104", 10240);
        File file = new File("Test/testDir/test02/crm.mp4");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] flush = new byte[1024 * 4];
        int len = -1;
        long sendSize = 0;
        long fileSize = file.length();
        while ((len = bis.read(flush)) != -1) {
            bos.write(flush, 0, len);
            sendSize += len;
            System.out.print(showProgress(sendSize, fileSize));
        }
        bos.flush();
        System.out.println("\nCompleted! File Size is " + sendSize);
        bos.close();
        bis.close();
        socket.close();
    }

    public static String showProgress(long sendSize, long fileSize) {
        double percent = (double) sendSize / fileSize * 100.0;
        return "\r" + sendSize + " / " + fileSize + "\t" + Math.round(percent) + "%" +
                " ".repeat(20);
    }
}
