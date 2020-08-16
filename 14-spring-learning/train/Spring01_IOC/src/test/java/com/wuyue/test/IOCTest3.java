package com.wuyue.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className IOCTest3
 * @description
 * @date 2020/3/12 17:02
 */
public class IOCTest3 {

    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("applicationContext01.xml");
    }

    @Test
    public void test01() throws SQLException {
        DataSource bean = ioc.getBean(DataSource.class);
        System.out.println(bean.getConnection());
    }
}














