package com.wuyue.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BookDao
 * @description
 * @date 2020/3/17 15:03
 */
@Repository
public class BookDao {
    JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param userName
     * @param balance
     * @author DeltaV235
     * @date 2020/3/17 15:06
     * @description 减去指定账号指定余额
     */
    public void updateBalance(String userName, int balance) {
        String sql = "update account set balance = balance - ? where username = ?";
        jdbcTemplate.update(sql, balance, userName);
    }

    /**
     * @param isbn
     * @return
     * @author DeltaV235
     * @date 2020/3/17 15:08
     * @description 获取指定书籍的价格
     */
    public Integer getPrice(String isbn) {
        String sql = "select price from book where isbn = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    /**
     * @param isbn
     * @author DeltaV235
     * @date 2020/3/17 15:11
     * @description 减去指定书籍的库存数-1
     */
    public void updateStock(String isbn) {
        String sql = "update book_stock set stock = stock - 1 where isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }

    public void updatePrice(String isbn, int price) {
        String sql = "update book set price = ? where isbn = ?";
        jdbcTemplate.update(sql, price, isbn);
    }
}














