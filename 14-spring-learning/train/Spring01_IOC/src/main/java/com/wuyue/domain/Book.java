package com.wuyue.domain;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Book
 * @description
 * @date 2020/3/11 18:39
 */
public class Book {
    private String bookName;
    private String author;

    public Book() {
        System.out.println("默认构造器 " + super.toString());
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String superToString() {
        return super.toString();
    }
}














