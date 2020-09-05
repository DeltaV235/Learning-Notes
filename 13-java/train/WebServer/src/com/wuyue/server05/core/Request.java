package com.wuyue.server05.core;

import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取method uri 及 请求参数
 */
public class Request {
    private String requestInfo;
    private String method;
    private String url;
    private String parameters;
    private Map<String, List<String>> parametersMap;

    public Request(Socket client) {
        // 获取请求协议
        InputStream is = null;
        try {
            is = client.getInputStream();
            byte[] datas = new byte[1024 * 1024 * 64];
            int len = is.read(datas);
            requestInfo = new String(datas, 0, len);
//            System.out.println(requestInfo);
            parseRequest();
        } catch (Exception e) {
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

        convertParaToMap();
    }

    private void convertParaToMap() {
        parametersMap = new HashMap<>();
        if (parameters == null)
            return;
        String[] kvs = parameters.split("&");
        for (String kv : kvs) {
            String key = kv.split("=")[0];
            String value = kv.split("=")[1] == null ? null : decode(kv.split("=")[1]);
            if (!parametersMap.containsKey(key))
                parametersMap.put(key, new ArrayList<>());
            parametersMap.get(key).add(value);
        }
    }

    /**
     * 通过name获取对应的多个值
     *
     * @param key
     * @return
     */
    public String[] getParameterValues(String key) {
        List<String> list = parametersMap.get(key);
        if (list == null || list.size() < 1)
            return null;
        return list.toArray(new String[0]);
    }

    public String getParaValue(String key) {
        String[] values = getParameterValues(key);
        return values == null ? null : values[0];
    }

    private String decode(String value) {
        return java.net.URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getParameters() {
        return parameters;
    }
}
