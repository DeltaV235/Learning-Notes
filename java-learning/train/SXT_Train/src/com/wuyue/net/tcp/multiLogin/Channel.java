package com.wuyue.net.tcp.multiLogin;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Channel implements Runnable {
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;

    public Channel(Socket socket) {
        this.socket = socket;
        try {
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.print(showTime() + "\t");
        System.out.println("--------Client Connected--------");
        receive();
        release();
        System.out.print(showTime() + "\t");
        System.out.println("--------Client Connection Closed--------");
    }

    private String showTime() {
        Date now = new Date();
        return new SimpleDateFormat("HH:mm:ss").format(now);
    }

    private void receive() {
        try {
            String receiveString = dis.readUTF();
            String userName = receiveString.split("&")[0];
            String userPwd = receiveString.split("&")[1];
            if (isCorrect(userName, userPwd)) {
                sendMsg("认证成功!");
                System.out.print(showTime() + "\t");
                System.out.print("认证成功!\n");
            }
            else
                sendMsg("Fuck You");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCorrect(String userName, String userPwd) {
        return userName.equals("wuyue") && userPwd.equals("7861");
    }

    private void sendMsg(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void release() {
        try {
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
