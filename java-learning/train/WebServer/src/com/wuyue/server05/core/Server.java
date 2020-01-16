package com.wuyue.server05.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 处理404 505 和首页
 */
public class Server {
    private ServerSocket serverSocket;
    private boolean isRunning;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        server.receive();
    }

    // 启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(10240);
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
            stop();
        }
    }

    // 接收连接
    public void receive() {
        while (isRunning) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("\n一个客户端建立了连接");
                new Thread(new Dispatcher(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("客户端错误");
            }
        }

    }

    // 停止服务
    public void stop() {
        try {
            isRunning = false;
            if (null != serverSocket)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
