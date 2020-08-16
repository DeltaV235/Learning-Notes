package com.wuyue.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
    private StringBuilder content;
    private StringBuilder headInfo;
    private int contentLen;
    private BufferedWriter bw;
    private final String BLANK = " ";
    private final String CRLF = "\r\n";

    private Response() {
        content = new StringBuilder();
        headInfo = new StringBuilder();
        contentLen = 0;
    }

    public Response(Socket client) {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response(OutputStream outputStream) {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    private void createHead(int statusCode) {
        headInfo.append("HTTP/1.1").append(BLANK);
        headInfo.append(statusCode).append(BLANK);
        switch (statusCode) {
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("NOT FOUND").append(CRLF);
                break;
            case 505:
                headInfo.append("SERVER ERROR").append(CRLF);
                break;
        }
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("Wuyue Server/1000.0;charset=GBK").append(CRLF);
        headInfo.append("Content-type:").append("text/html").append(CRLF);
        headInfo.append("Content-length:").append(contentLen).append(CRLF);
        headInfo.append(CRLF);
    }

    public Response print(String string) {
        content.append(string);
        contentLen += string.getBytes().length;
        return this;
    }

    public void push(int statusCode) {
        createHead(200);
        try {
            bw.append(headInfo).append(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
