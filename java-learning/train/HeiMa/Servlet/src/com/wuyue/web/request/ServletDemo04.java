package com.wuyue.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ServletDemo04
 * @description 测试request中获取请求体参数的通用方法，解决中文乱码问题
 * @date 2020/2/14 14:42
 */
@WebServlet("/ServletDemo04")
public class ServletDemo04 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        String characterEncoding = request.getCharacterEncoding();
//        System.out.println("characterEncoding = " + characterEncoding);
        // 获取指定key的value
        String username = request.getParameter("username");
//        username = new String(username.getBytes("iso8859-1"), StandardCharsets.UTF_8);
//        System.out.println("request.getParameter(\"username\") = " + Arrays.toString(decodeByISO(request.getParameter("username")).getBytes()));
        System.out.println("username = " + username);

        // 获取指定key的values[]
        System.out.println("-".repeat(60));
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("hobbies = " + Arrays.toString(hobbies));

        // 获取所有key名称
        System.out.println("-".repeat(60));
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements())
            System.out.println("parameterName = " + parameterNames.nextElement());

        // 获取key-value的map对象
        System.out.println("-".repeat(60));
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            String[] values = parameterMap.get(key);
            System.out.print("key = " + key + "\t");
            System.out.println("values = " + Arrays.toString(values));
        }
        System.out.println("\n".repeat(3));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String decodeByISO(String string) {
        return URLDecoder.decode(string, StandardCharsets.ISO_8859_1);
    }

    private String decodeByUTF(String string) {
        return URLDecoder.decode(string, StandardCharsets.UTF_8);
    }
}
