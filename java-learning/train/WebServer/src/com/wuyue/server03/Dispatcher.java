package com.wuyue.server03;

import java.io.IOException;
import java.net.Socket;

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
        Servlet servlet = WebApp.getServletFromUrl(request.getUrl() == null ? "" : request.getUrl());
        if (null != servlet) {
            servlet.service(request, response);
            response.push(200);
        } else
            response.push(404);
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
