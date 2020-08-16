package com.wuyue.server02;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApp {
    private static WebContext webContext;

    static {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            WebHandler webHandler = new WebHandler();
            parser.parse(Thread.currentThread().
                    getContextClassLoader().
                    getResourceAsStream("com/wuyue/server02/web.xml"), webHandler);
            webContext = new WebContext(webHandler.getEntities(), webHandler.getMappings());
        } catch (Exception e) {
            System.out.println("解析配置文件错误");
        }
    }

    /**
     * 通过URL获取配置文件对应的servlet
     *
     * @param url
     * @return
     */
    public static Servlet getServletFromUrl(String url) {
        String className = webContext.getClz("/" + url);
        Class clz = null;
        try {
            clz = Class.forName(className);
            Servlet servlet = (Servlet) clz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


