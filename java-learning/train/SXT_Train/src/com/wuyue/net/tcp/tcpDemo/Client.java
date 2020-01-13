package com.wuyue.net.tcp.tcpDemo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10240);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("你好！");
        dos.close();
        socket.close();
    }
}
