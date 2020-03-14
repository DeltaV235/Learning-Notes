package com.wuyue.dao;

import org.springframework.stereotype.Repository;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookDao
 * @description
 * @date 2020/3/13 16:33
 */
@Repository
//@Scope("prototype")
public class BookDao {
    public void saveBook() {
        System.out.println("saved a book");
    }
}














