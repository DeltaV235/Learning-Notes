package com.wuyue.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 获取method uri 及 请求参数
 */
public class Request {
    private String requestInfo;
    private String method;
    private String url;
    private String parameters;

    public Request(Socket client) {
        // 获取请求协议
        InputStream is = null;
        try {
            is = client.getInputStream();
            byte[] datas = new byte[1024 * 1024];
            int len = is.read(datas);
            requestInfo = new String(datas, 0, len);
            System.out.println(requestInfo);
            parseRequest();
        } catch (IOException e) {
            System.out.println("未获得请求信息");
        }
    }

    private void parseRequest() {
        final String CRLF = "\r\n";
        method = requestInfo.substring(0, requestInfo.indexOf("/")).trim().toLowerCase();
        url = requestInfo.substring(requestInfo.indexOf("/") + 1, requestInfo.indexOf("HTTP/")).trim();
        if (url.contains("?")) {
            String[] temp = url.split("\\?");
            url = temp[0];
            parameters = temp[1];
        }
        if (method.equals("post")) {
            String body = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
            if (parameters == null)
                parameters = body;
            else
                parameters += "&" + body;
        }
        System.out.println("method:" + method);
        System.out.println("url:" + url);
        System.out.println("parameters:" + (parameters == null ? "" : parameters));
    }
}
