package com.wuyue.springboot04.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author DeltaV235
 * @version 1.0
 * @className myListener
 * @description
 * @date 2020/4/8 15:20
 */
public class myListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener:init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener:destroy");
    }
}
