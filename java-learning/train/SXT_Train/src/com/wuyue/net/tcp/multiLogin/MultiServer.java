package com.wuyue.net.tcp.multiLogin;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10240);
        System.out.println("--------Server is Running--------");
        while (true) {
            Socket socket = server.accept();
            new Thread(new Channel(socket)).start();
        }
    }
}
