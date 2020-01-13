package com.wuyue.net.tcp.tcpDemo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10240);
        System.out.println("--------Server is Running--------");
        Socket socket = server.accept();
        System.out.println("--------One client has get Connection--------");
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        System.out.println(dis.readUTF());
        dis.close();
        socket.close();
    }
}
