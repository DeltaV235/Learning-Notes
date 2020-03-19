package com.wuyue.test;

import com.wuyue.service.BookService;
import com.wuyue.service.MulTx;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookTest
 * @description
 * @date 2020/3/17 15:16
 */
public class BookTest {
    private ApplicationContext ioc;

    @Before
    public void init() {
        ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    @Test
    public void test01() throws FileNotFoundException {
        BookService service = ioc.getBean(BookService.class);

        service.checkout("Tom", "ISBN-001");
    }

    @Test
    public void test02() {
        MulTx bean = ioc.getBean(MulTx.class);
        bean.doTx();
    }

//    @Test
//    public void test03() {
//        BookService service = ioc.getBean(BookService.class);
//        service.mulTx();
//    }
}














