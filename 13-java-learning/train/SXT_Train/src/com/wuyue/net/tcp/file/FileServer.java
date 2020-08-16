package com.wuyue.net.tcp.file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端
 */
public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10240);
        System.out.println("--------Server is Running--------");
        Socket socket = server.accept();
        System.out.println("--------One client has get Connection--------");
        InputStream is = new BufferedInputStream(socket.getInputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream("/home/DeltaV/copy.mp4"));
        byte[] flush = new byte[1024 * 4];
        int len = -1;
        long sendSize = 0;
        while ((len = is.read(flush)) != -1) {
            os.write(flush, 0, len);
            sendSize += len;
            System.out.print(showProgress(sendSize, 0));
        }
        os.flush();
        System.out.println("\nCompleted! File Size is " + sendSize);
        os.close();
        is.close();
        socket.close();
    }

    public static String showProgress(long sendSize, long fileSize) {
        StringBuilder stringBuilder = new StringBuilder();
        double percent = 0;
        if (fileSize != 0)
            percent = (double) sendSize / fileSize;
        stringBuilder.append("\r").append(sendSize).append(" / ").append(fileSize).append("\t").append(percent).append("%");
        stringBuilder.append(" ".repeat(20));
        return stringBuilder.toString();
    }
}
