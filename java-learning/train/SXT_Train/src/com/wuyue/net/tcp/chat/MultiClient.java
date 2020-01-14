package com.wuyue.net.tcp.chat;

import java.io.IOException;
import java.net.Socket;

public class MultiClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10240);
        new Thread(new ClientReceive(socket)).start();
        new Thread(new ClientSend(socket)).start();
    }
}
