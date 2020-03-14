package com.wuyue.dao;

import org.springframework.stereotype.Repository;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookDaoExt
 * @description
 * @date 2020/3/13 20:00
 */
@Repository
public class BookDaoExt extends BookDao {
    @Override
    public void saveBook() {
        System.out.println("BookDaoExt....");
    }
}














