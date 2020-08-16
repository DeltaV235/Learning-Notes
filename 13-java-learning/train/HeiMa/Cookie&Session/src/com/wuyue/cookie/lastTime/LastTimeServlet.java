package com.wuyue.cookie.lastTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LastTimeServlet
 * @description 提示用户上次访问的时间，若首次访问则显示提示语
 * @date 2020/2/16 17:31
 */
@WebServlet("/LastTimeServlet")
public class LastTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String lastTime = "";

        boolean isFirst = true;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTime")) {
                    isFirst = false;
                    lastTime = cookie.getValue();
                    break;
                }
            }
        }
        Date now = new Date();
        Cookie cookie = new Cookie("lastTime", "" + now.getTime());
        cookie.setMaxAge(60 * 60 * 24 * 14);
        response.addCookie(cookie);
        if (isFirst) {
            writer.write("您好，欢迎您首次访问");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            writer.write("欢迎回来，您上次访问时间为:" + dateFormat.format(new Date(Long.parseLong(lastTime))));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
