package com.wuyue.service;

import com.wuyue.bean.Person;
import com.wuyue.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookService
 * @description
 * @date 2020/3/13 16:33
 */
@Service
public class BookService {
    @Autowired(required = false)
    @Qualifier("bookDaoExt")
    private BookDao bookDaoExt2;

    public void save() {
        bookDaoExt2.saveBook();
    }

    @Autowired(required = false)
    public void testAnno(Person bookDao2, @Qualifier("bookDaoExt") BookDao bookDaoExt2) {
//        System.out.println("方法注解...." + bookDao2 + "\t" + bookDaoExt2);
        System.out.println("方法注解");
    }
}














