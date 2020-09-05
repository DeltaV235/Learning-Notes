package com.wuyue.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MulTx
 * @description
 * @date 2020/3/18 18:30
 */
@Service
public class MulTx {
    private BookService bookService;

    public MulTx(BookService bookService) {
        this.bookService = bookService;
    }

    @Transactional
    public void doTx() {
        bookService.checkout("Tom", "ISBN-001");
        bookService.updatePrice("ISBN-002", 233);
    }
}














