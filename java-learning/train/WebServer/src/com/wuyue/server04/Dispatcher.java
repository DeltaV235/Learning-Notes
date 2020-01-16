package com.wuyue.server04;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 加入状态处理
 */
public class Dispatcher implements Runnable {
    private Socket client;
    private Request request;
    private Response response;

    public Dispatcher(Socket client) {
        this.client = client;
        request = new Request(client);
        response = new Response(client);
    }

    @Override
    public void run() {
        if (request.getUrl().equals("")) {
            try {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/wuyue/server04/index.html");
                response.print(new String(is.readAllBytes()));
                response.push(200);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Servlet servlet = WebApp.getServletFromUrl(request.getUrl());
        if (null != servlet) {
            servlet.service(request, response);
            response.push(200);
        } else {
            response.print("滚啊，URL都写错了");
            response.push(404);
        }
        release();
    }

    private void release() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
