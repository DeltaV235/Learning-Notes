package com.wuyue.server02;

/**
 * 服务器小脚本接口
 */
public interface Servlet {
    void service(Request request, Response response);
}
