package com.wuyue;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/21 11:17
 */
public class ChainDemo {
    public static void main(String[] args) {
        Book book = new Book();
        book.setAuthor("deltaV").setBookName("fuck the world").setPrice(998);
        System.out.println("book.toString() = " + book.toString());
    }
}
