package com.wuyue.server02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 加入了servlet
 */
public class Server06 {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server06 server06 = new Server06();
        server06.start();
        server06.receive();
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
                Servlet servlet = new LoginServlet();
                servlet.service(request, response);
                // 写出到客户端
                response.push(200);
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
