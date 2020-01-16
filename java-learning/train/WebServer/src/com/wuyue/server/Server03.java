package com.wuyue.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 封装响应信息
 * 1、内容可以动态添加
 * 2、关注状态码
 */
public class Server03 {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server03 server03 = new Server03();
        server03.start();
        server03.receive();
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
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接");
            // 获取请求协议
            InputStream is = client.getInputStream();
            byte[] datas = new byte[1024 * 1024];
            int len = is.read(datas);
            String requestInfo = new String(datas, 0, len);
            System.out.println(requestInfo);

            /**
             *
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
            response.print("嘿嘿嘿");
            response.print("</body>");
            response.print("</html>");
            // 写出到客户端
            response.push(200);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }

    // 停止服务
    public void stop() {
    }
}
