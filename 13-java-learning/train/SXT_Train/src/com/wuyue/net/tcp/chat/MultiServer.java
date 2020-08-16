package com.wuyue.net.tcp.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * TCP服务端
 */
public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10240);
        CopyOnWriteArraySet<Channel> allUser = new CopyOnWriteArraySet<>();
        System.out.println("--------Server is Running--------");
        while (true) {
            Socket socket = server.accept();
            Channel currentChannel = new Channel(socket, allUser);
            allUser.add(currentChannel);
            new Thread(currentChannel).start();
        }
    }
}
