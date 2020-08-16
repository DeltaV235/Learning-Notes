package com.wuyue.net.tcp.chat;

import java.io.IOException;
import java.net.Socket;

public class ClientReceive extends Channel {
    public ClientReceive(Socket socket) {
        super(socket);
    }

    private String receive() {
        try {
            return dis.readUTF();
        } catch (IOException e) {
            release();
            System.out.println("Receive Fail");
        }
        return "";
    }

    @Override
    public void run() {
        while (isRunning)
            System.out.println(receive());
    }
}
