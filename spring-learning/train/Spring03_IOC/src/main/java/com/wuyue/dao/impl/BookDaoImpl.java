package com.wuyue.dao.impl;

import com.wuyue.dao.BaseDao;
import com.wuyue.domain.Book;
import org.springframework.stereotype.Repository;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookDaoImpl
 * @description
 * @date 2020/3/14 2:05
 */
@Repository
public class BookDaoImpl extends BaseDao<Book> {
    @Override
    public void save() {
        System.out.println("BookDaoImpl...save()");
    }
}














