package com.wuyue.net.tcp.chat;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

public class Channel implements Runnable {
    protected DataInputStream dis;
    protected DataOutputStream dos;
    protected Socket socket;
    protected boolean isRunning;
    private CopyOnWriteArraySet<Channel> allUser;
    private String name;

    public Channel(Socket socket) {
        this.socket = socket;
        try {
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            isRunning = true;
        } catch (IOException e) {
            System.out.println("连接被关闭");
        }
    }

    public Channel(Socket socket, CopyOnWriteArraySet<Channel> allUser) {
        this(socket);
        this.allUser = allUser;
    }

    @Override
    public void run() {
        System.out.print(showTime());
        System.out.println("Client Connected");
        this.name = receiveMsg();
        sendMsgOther("我进来啦(系统信息)");
        while (isRunning) {
            String msg = receiveMsg();
            sendMsgOther(msg);
        }
        System.out.print(showTime());
        System.out.println("Client Connection Closed");
    }

    protected String showTime() {
        Date now = new Date();
        return new SimpleDateFormat("HH:mm:ss").format(now) + " ".repeat(5);
    }

    private String receiveMsg() {
        try {
            return dis.readUTF();
        } catch (IOException e) {
            release();
            removeContainer();
            return "我走了(系统信息)";
        }
    }

    private void sendMsg(String msg) {
        try {
            if (!msg.equals(this.name + ": ")) {
                dos.writeUTF(showTime() + msg);
                dos.flush();
            }
        } catch (IOException e) {
            release();
            removeContainer();
        }
    }

    private void sendMsgOther(String msg) {
        String atName = msg.split(" ")[0];
        if (atName.charAt(0) == '@') {
            atName = atName.substring(1);
            boolean hasFound = false;
            for (Channel other : allUser) {
                if (!other.equals(this) && other.name.equals(atName)) {
                    other.sendMsg(this.name + ": " + msg);
                    hasFound = true;
                    break;
                }
            }
            if (!hasFound)
                this.sendMsg("该用户不存在(系统信息)");
        } else {
            for (Channel other : allUser) {
                if (!other.equals(this) && other.name != null) {
                    other.sendMsg(this.name + ": " + msg);
                }
            }
        }
    }

    private void removeContainer() {
        allUser.remove(this);
    }

    protected void release() {
        isRunning = false;
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
