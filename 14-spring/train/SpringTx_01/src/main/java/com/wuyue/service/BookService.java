package com.wuyue.service;

import com.wuyue.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookService
 * @description
 * @date 2020/3/17 15:13
 */
@Service
public class BookService {
    private BookDao bookDao;

//    @Autowired
//    private BookService bookService;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkout(String userName, String isbn) {
        // 库存 - 1
        bookDao.updateStock(isbn);
        // 获取书籍价格
        Integer price = bookDao.getPrice(isbn);
        // 扣除账号余额
        bookDao.updateBalance(userName, price);
        throw new RuntimeException();
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updatePrice(String isbn, int price) {
        bookDao.updatePrice(isbn, price);
    }

//    @Transactional
//    public void mulTx() {
//        bookService.checkout("Tom", "ISBN-001");
//        bookService.updatePrice("ISBN-002", 2000);
//        int i = 10 / 0;
//    }
}














