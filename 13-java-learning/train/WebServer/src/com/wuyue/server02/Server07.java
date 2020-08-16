package com.wuyue.server02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 整合配置文件
 */
public class Server07 {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server07 server07 = new Server07();
        server07.start();
        server07.receive();
    }

    // 启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(10240);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    // 接收连接
    public void receive() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("\n一个客户端建立了连接");

                Request request = new Request(client);
                Response response = new Response(client);
                Servlet servlet = WebApp.getServletFromUrl(request.getUrl());
                if (null != servlet) {
                    servlet.service(request, response);
                    response.push(200);
                } else
                    response.push(404);

                // 写出到客户端
            }
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }

    }

    // 停止服务
    public void stop() {
    }
}
