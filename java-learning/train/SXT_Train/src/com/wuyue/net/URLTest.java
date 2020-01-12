package com.wuyue.net;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.wuyue.com:2333/index.html?name=wuyue&sex=M#ht");
        System.out.println("protocol:" + url.getProtocol());
        System.out.println("domain or ip:" + url.getHost());
        System.out.println("port:" + url.getPort());
        System.out.println("file:" + url.getFile());
        System.out.println("path:" + url.getPath());
        System.out.println("parameter:" + url.getQuery());
        System.out.println("anchor:" + url.getRef());
        System.out.println(url.getAuthority());
    }
}
