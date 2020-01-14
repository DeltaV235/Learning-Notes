package com.wuyue.net.tcp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSend extends Channel {
    BufferedReader br;

    public ClientSend(Socket socket) {
        super(socket);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void send() {
        try {
            String msg = br.readLine();
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            release();
            System.out.println("Send Fail");
        }
    }

    @Override
    public void run() {
        System.out.print("请输入姓名:");
        send();
        while (isRunning) {
            send();
        }
    }
}
