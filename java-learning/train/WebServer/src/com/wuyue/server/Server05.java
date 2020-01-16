package com.wuyue.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 将请求协议中的参数封装成Map
 */
public class Server05 {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server05 server05 = new Server05();
        server05.start();
        server05.receive();
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

                Request02 request = new Request02(client);

                /**
                 * 返回响应
                 * 1.响应行
                 * 2.响应头
                 * 3.正文
                 */
                Response response = new Response(client);
                response.print("<html>");
                response.print("<head>");
                response.print("<title>");
                response.print("服务器响应成功");
                response.print("</title>");
                response.print("</head>");
                response.print("<body>");
                response.print("嘿嘿嘿" + request.getParaValue("name"));
                response.print("</body>");
                response.print("</html>");
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
